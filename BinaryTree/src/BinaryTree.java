import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Caijg
 * @create 2023/11/1 15:34
 */
public class BinaryTree<E> {

    /**
     * 二叉树的节点
     */
    private static class TreeNode<E> {
        E data; //数据
        TreeNode<E> left;  //左孩子
        TreeNode<E> right; //右孩子

        /**
         * 构造函数，用于创建节点
         *
         * @param x
         */
        TreeNode(E x) {
            data = x;
            left = right = null;
        }
    }

    /**
     * 非递归前序遍历
     * @param node
     */
    static void prevOrderNR(TreeNode node) {
        ArrayList<TreeNode> stack = new ArrayList<>();

        stack.add(node);
        while (!stack.isEmpty()) {
            TreeNode last = stack.get(stack.size() - 1);
            stack.remove(last);
            if (last == null) {
                continue;
            }
            System.out.print(last.data + " ");
            stack.add(last.right);
            stack.add(last.left);
        }
    }

    /**
     * 非递归后序遍历
     * @param node
     */
    static void postOrderNR(TreeNode node) {
        ArrayDeque<TreeNode> stack1 = new ArrayDeque<>();
        ArrayDeque<TreeNode> stack2 = new ArrayDeque<>();

        stack1.push(node);

        while (!stack1.isEmpty()) {

            TreeNode cur = stack1.pop();
            stack2.push(cur);

            if (cur.left != null) {
                stack1.push(cur.left);
            }

            if (cur.right != null) {
                stack1.push(cur.right);
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().data + " ");
        }
    }

    /**
     * 非递归中序遍历
     * @param node
     */
    static void inOrderNR(TreeNode node) {
        ArrayDeque<TreeNode> stack = new ArrayDeque<>();

        TreeNode cur = node;

        while (!stack.isEmpty() || cur != null) {

            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            System.out.print(cur.data + " ");
            cur = cur.right;
        }
    }

    /**
     * 前序遍历二叉树
     * @param node
     */
    static void prevOrder(TreeNode node) {
        //当前节点为空，返回到上一层
        if (node == null) {
            return;
        }

        System.out.print(node.data + " ");
        prevOrder(node.left);
        prevOrder(node.right);
    }

    /**
     * 中序遍历二叉树
     * @param node
     */
    static void inOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        inOrder(node.left);
        System.out.print(node.data + " ");
        inOrder(node.right);
    }

    /**
     * 后序遍历二叉树
     * @param node
     */
    static void postOrder(TreeNode node) {
        if (node == null) {
            return;
        }

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(node.data + " ");
    }

    /**
     * 层序遍历
     * @param node
     */
    static void levelOrder(TreeNode node) {
        //存储节点的队列
        LinkedList<TreeNode> queue = new LinkedList<>();

        //如果是空树，直接返回
        if (node == null) return;
        queue.push(node);

        while (!queue.isEmpty()) {
            System.out.print(queue.peek().data + " ");
            if (queue.peek().left != null) queue.offer(queue.peek().left);
            if (queue.peek().right != null) queue.offer(queue.peek().right);
            queue.poll();
        }
    }

    /**
     * 求树节点个数
     * @param node
     * @return
     */
    static int treeSize(TreeNode node) {
        if(node == null) return 0;
        return 1 + treeSize(node.left) + treeSize(node.right);
    }

    /**
     * 求树的叶子节点数
     * @param node
     * @return
     */
    static int treeLeafSize(TreeNode node) {
        if (node == null) return 0;

        if (node.right == null && node.left == null) return 1;

        return treeLeafSize(node.right) + treeLeafSize(node.left);
    }

    /**
     * 求树的深度
     * @param node
     * @return
     */
    static int treeDepth(TreeNode node) {
        if (node == null) return 0;
        if (node.right == null && node.left == null) return 1;
        return 1 + Math.max(treeDepth(node.right), treeDepth(node.left));
    }

    public static void main(String[] args) {
        TreeNode<Character> A = new TreeNode<>('A');
        TreeNode<Character> B = new TreeNode<>('B');
        TreeNode<Character> C = new TreeNode<>('C');
        TreeNode<Character> D = new TreeNode<>('D');
        TreeNode<Character> E = new TreeNode<>('E');

        A.left = B;
        A.right = C;
        B.left = D;
        B.right = null;
        C.left = null;
        C.right = E;

        //四种遍历方法
        System.out.print("前序遍历：");
        prevOrder(A);
        System.out.println();
        System.out.print("非递归前序遍历：");
        prevOrderNR(A);
        System.out.println();
        System.out.print("中序遍历：");
        inOrder(A);
        System.out.println();
        System.out.print("非递归中序遍历：");
        inOrderNR(A);
        System.out.println();
        System.out.print("后序遍历：");
        postOrder(A);
        System.out.println();
        System.out.print("非递归后序遍历：");
        postOrderNR(A);
        System.out.println();
        System.out.print("层序遍历遍历：");
        levelOrder(A);
        System.out.println();
        System.out.println("节点的个数为：" + treeSize(A));
        System.out.println("叶子节点个数：" + treeLeafSize(A));
        System.out.println("树的深度为：" + treeDepth(A));
    }
}
