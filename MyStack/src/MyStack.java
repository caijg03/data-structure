import java.util.Arrays;

/**
 * 数组实现栈
 * @author Caijg
 * @create 2023/10/29 16:07
 */
public class MyStack<E> {
    private Object[] arr; //储存元素的数组
    private int size;  //栈的长度, 也表示栈顶下标
    private int capacity;  //数组的最大长度

    MyStack() {
        arr = new Object[]{}; //数组初始化为空
        size = capacity = 0;
    }

    /**
     * 入栈操作
     * @param x
     */
    void push(E x) {
        //首先检查数组是否已满,已满则扩容
        if (size == capacity) {
            capacity = capacity == 0 ? 4 : capacity * 2;
            //数组扩容
            arr = Arrays.copyOf(arr,capacity);
        }
        arr[size++] = x;
    }

    /**
     * 出栈操作
     * @throws Exception
     */
    void pop() throws Exception {
        if (size <= 0) throw new Exception("栈为空");

        size--;
    }

    /**
     * 返回栈顶元素
     * @return
     * @throws Exception
     */
    E top() throws Exception {
        if (size <= 0) throw new Exception("栈为空");
        return (E)arr[size - 1];
    }

    int size() {
        return size;
    }

    boolean empty() {
        return size == 0;
    }

}
