/**
 * 链表实现队列
 * @author Caijg
 * @create 2023/10/29 16:33
 */
public class MyQueue<E> {
    //节点
    private static class Node<E> {
        E item;  //值
        Node<E> next;  //下一个节点

        Node(E item) {
            this.item = item;
            this.next = null;
        }
    }

    Node<E> first;  //首节点
    Node<E> last;  //尾节点

    MyQueue() {
        first = last = null;  //初始化为空
    }

    /**
     * 入队
     * @param x
     */
    void push(E x) {

        Node<E> node = new Node(x);

        //当队列为空时
        if (first == null) {
            first = last = node;
            return;
        }

        //更新尾节点
        last.next = node;
        last = node;
    }

    /**
     * 出队
     * @throws Exception
     */
    void pop() throws Exception {
        if (first == null) throw new Exception("队列为空");

        //当只有一个节点时,需要更新尾节点
        if (first == last) {
            first = last = null;
            return;
        }

        first = first.next;
    }

    /**
     * 取队头元素
     * @return
     * @throws Exception
     */
    E front() throws Exception {
        if (first == null) throw new Exception("队列为空");

        return first.item;
    }

    /**
     * 取队尾元素
     * @return
     * @throws Exception
     */
    E back() throws Exception {
        if (last == null) throw new Exception("队列为空");

        return last.item;
    }

    int size() {
        Node<E> node = first;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }

        return size;
    }

    boolean empty() {
        return first == null;
    }

    public static void main(String[] args) throws Exception {
        MyQueue<Integer> mq = new MyQueue<>();

        mq.push(1);
        mq.push(2);
        mq.push(3);
        mq.push(4);
        mq.push(5);

        while (!mq.empty()) {
            System.out.println(mq.front());
            mq.pop();
        }

        System.out.println(mq.size());
    }
}
