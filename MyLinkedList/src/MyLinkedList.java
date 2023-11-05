/**
 * 带头双向循环链表
 * @author Caijg
 * @create 2023/10/25 21:43
 */
public class MyLinkedList<E> {
    private static class Node<E> {
        Node<E> prev;  //前一个节点
        E item;  //数据
        Node<E> next;  //后一个节点

        public Node() {
        }

        public Node(E item) {
            this.item = item;
        }
    }

    private final Node<E> head = new Node<E>();  //哨兵位的头节点,不存储数据,只是为了方便操作

    public MyLinkedList() {
        head.next = head.prev = head;  //头节点的前后指针都指向自己,方便后续操作
    }

    /**
     * 尾插
     * @param x
     */
    void push_back(E x) {
        //方式1
//        Node<E> newNode = new Node<>(x);
//
//        Node<E> last = head.prev;
//
//        last.next = newNode;
//        newNode.next = head;
//        newNode.prev = last;
//        head.prev = newNode;

        //方式2
        insert(head, x);
    }

    /**
     * 头插
     * @param x
     */
    void push_front(E x) {
        //方式1
//        Node<E> newNode = new Node<>(x);
//
//        Node<E> first = head.next;
//
//        head.next = newNode;
//        newNode.prev = head;
//        newNode.next = first;
//        first.prev = newNode;

        //方式2
        insert(head.next, x);
    }

    /**
     * 尾删
     * @throws Exception
     */
    void pop_back() throws Exception {
        //方式1
//        if (head.next == head) throw new Exception("链表为空");
//
//        Node<E> last = head.prev;
//        Node<E> prev = last.prev;
//
//        prev.next = head;
//        head.prev = prev;

        //方式2
        erase(head.prev);
    }

    /**
     * 头删
     * @throws Exception
     */
    void pop_front() throws Exception {
        //方式1
//        if (head.next == head) throw new Exception("链表为空");
//
//        Node<E> first = head.next;
//        Node<E> next = first.next;
//
//        head.next = next;
//        next.prev = head;

        //方式2
        erase(head.next);
    }

    /**
     * 指定位置插入, 默认target一定存在
     * @param target
     * @param x
     */
    void insert(Node<E> target, E x) {
        Node<E> newNode = new Node<>(x);

        Node<E> prev = target.prev;

        prev.next = newNode;
        newNode.next = target;
        newNode.prev = prev;
        target.prev = newNode;

        }

    /**
     * 指定位置删除,默认target 一定存在
     * @param target
     */
    void erase(Node<E> target) {
        Node<E> prev = target.prev;
        Node<E> next = target.next;

        prev.next = next;
        next.prev = prev;
    }

    /**
     * 按照下标插入,超过取值范围抛出异常
     * @param index [0, size]
     * @param x
     * @throws Exception
     */
    void insert(int index, E x) throws Exception {
        if (index < 0 || index > size()) throw new Exception("未找到位置");

        Node<E> newNode = new Node<>(x);
        Node<E> node = head.next;

        while (index > 0) {
            index--;
            node = node.next;
        }

        Node<E> prev = node.prev;

        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = node;
        node.prev = newNode;
    }

    /**
     * 按照下标删除,链表为空抛出异常
     * @param index [0, index - 1]
     * @throws Exception
     */
    void erase(int index) throws Exception {
        if (head.next == head) throw new Exception("链表为空");

        if (index < 0 || index >= size()) throw new Exception("未找到位置");

        Node<E> node = head.next;

        while (index > 0) {
            index--;
            node = node.next;
        }

        Node<E> prev = node.prev;
        Node<E> next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    /**
     * 按照item值查找,找到返回指定对象,没找到返回null
     * @param x
     * @return
     */
    Node<E> find(E x) {
        Node<E> node = head.next;

        while (node != head) {
            if (node.item == x) return node;
            node = node.next;
        }

        return null;
    }

    int size() {
        int size = 0;
        Node<E> node = head.next;

        while(node != head) {
            size++;
            node = node.next;
        }

        return size;
    }

    boolean empty() {
        return head.next == head;
    }


    void print() {
        Node<E> node = head.next;

        while(node != head) {
            System.out.println(node.item);
            node = node.next;
        }
    }
}
