/**
 * 单链表
 * @author Caijg
 * @create 2023/10/24 20:27
 */

public class SingleLinkedList<E> {

    /**
     * 内部类, 表示单链表的节点,每new一个对象,就有一个节点
     * @param <E> 节点的数据类型
     */
    private static class Node<E> {
        E item;  //数据
        Node<E> next;  //下一个节点对象

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    private Node<E> first;  //头节点

    SingleLinkedList() {
        first = null;
    }

    /**
     * 尾插
     * @param x
     */
    void push_back(E x) {

        Node<E> newNode = new Node<>(x, null);

        if (first == null) {
            first = newNode;
            return;
        }

        Node<E> node = first;
        while (node.next != null) {
            node = node.next;
        }

        node.next = newNode;
    }

    /**
     * 头插
     * @param x
     */
    void push_front(E x) {
        Node<E> newNode = new Node<>(x, null);
        if (first == null) {
            first = newNode;
            return;
        }

        newNode.next = first;
        first = newNode;
    }

    /**
     * 尾删
     * @throws Exception
     */
    void pop_back() throws Exception {

        //如果链表为空,抛出异常
        if (first == null) {
            throw new Exception("链表为空");
        }

        //当只有一个节点时
        if (first.next == null) {
            first = null;
            return;
        }

        //找到尾节点的前一个节点 记作prev
        Node<E> node = first.next;
        Node<E> prev = first;

        while (node.next != null) {
            node = node.next;
            prev = prev.next;
        }

        prev.next = null;
    }

    /**
     * 头删
     * @throws Exception
     */
    void pop_front() throws Exception {
        if (first == null) {
            throw new Exception("链表为空");
        }

        if (first.next == null) {
            first = null;
            return;
        }

        first = first.next;
    }

    /**
     * 根据传入的节点进行插入,默认target一定在链表中
     * @param target
     * @param x
     */
    void insert(Node<E> target, E x) {
        Node<E> newNode = new Node<>(x, target);

        //当target为first
        if (first == target) {
            first = newNode;
            return;
        }

        Node<E> node = first;
        //node最终为target前一个
        while (node.next != target) {
            node = node.next;
        }

        node.next = newNode;
    }

    /**
     * 根据传入的下标进行插入, 超出范围抛出异常
     * @param index 下标 [0, size]
     * @param x
     * @throws Exception
     */
    void insert(int index, E x) throws Exception {
        Node<E> node = first;

        //当index为0
        if (index == 0) {
            first = new Node<E>(x, first);
            return;
        }

        int n = 0;
        while (node != null) {
            if (n == index - 1) {
                node.next = new Node<E>(x, node.next);
                return;
            }
            node = node.next;
            n++;
        }

        throw new Exception("未找到位置");
    }

    /**
     * 根据传入的值进行删除,默认链表中一定存在target
     * @param target
     * @throws Exception
     */
    void erase(Node<E> target) throws Exception {
        //target为first
        if (target == first) {
            first = first.next;
            return;
        }

        //使node为target的前一个节点
        Node<E> node = first;
        while (node.next != target) {
            node = node.next;
        }

        node.next = target.next;
    }

    /**
     * 根据下标进行删除,链表为空或者下标超出范围抛出异常
     * @param index [0, size - 1]
     */
    void erase(int index) throws Exception {
        //链表为空
        if (first == null) {
            throw new Exception("链表为空");
        }

        //当index为0,需要操作first指向
        if (index == 0) {
            first = first.next;
            return;
        }

        int n = 0;
        Node<E> node = first.next;
        while (node.next != null) {
            if (n == index - 2) {
                node.next = node.next.next;
                return;
            }
            node = node.next;
            n++;
        }

        throw new Exception("未找到该位置");
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

    /**
     * 根据item值进行查找
     * @param x
     * @return
     */
    Node<E> find(E x) {
        Node<E> node = first;

        while (node != null) {
            if (node.item == x) return node;
            node = node.next;
        }

        return null;
    }

    void print() {
        Node<E> node = first;

        while (node != null) {
            System.out.print(node.item + " ");
            node = node.next;
        }
    }
}
