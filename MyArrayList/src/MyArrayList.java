import java.util.Arrays;

/**
 * @author Caijg
 * @create 2023/10/24 19:24
 */
public class MyArrayList<E> {
    private Object[] array;  //存储数据的数组
    private int capacity;  //容量
    private int size;  //线性表的大小

    MyArrayList() {
        this.array = new Object[]{};  //初始化为空
        this.capacity = this.size = 0;
    }

    /**
     * 每次插入时都要检查容量是否已满,已满则需要扩容
     */
    public void grow() {
        if (size == capacity) {
            int newCapacity = (capacity == 0 ? 10 : capacity * 2);

            //扩容数组
            array = Arrays.copyOf(array, newCapacity);
            capacity = newCapacity;
        }
    }

    /**
     * 尾插
     * @param x
     */
    void push_back(E x) {
        grow();

        array[size++] = x;
    }

    /**
     * 头插
     * @param x
     */
    void push_front(E x) {
        grow();

        for (int end = size - 1; end >= 0; end--) {
            array[end + 1] = array[end];
        }

        array[0] = x;
        size++;
    }

    /**
     * 尾删
     * @throws Exception
     */
    void pop_back() throws Exception {
        if (size <= 0) {
            throw new Exception("容器为空");
        }

        size--;
    }

    /**
     * 头删
     * @throws Exception
     */
    void pop_front() throws Exception {
        if (size <= 0) {
            throw new Exception("容器为空");
        }

        for (int start = 1; start < size; start++) {
            array[start - 1] = array[start];
        }

        size--;
    }

    /**
     * 指定位置插入
     * @param pos
     * @param x
     * @throws Exception
     */
    void insert(int pos, E x) throws Exception {
        if (pos > size || pos < 0) {
            throw new Exception("未找到位置");
        }

        for (int end = size - 1; end >= pos; end--) {
            array[end + 1] = array[end];
        }

        array[pos] = x;
        size++;
    }

    /**
     * 指定位置删除
     * @param pos
     * @throws Exception
     */
    void Erase(int pos) throws Exception {
        if (pos >= size || pos < 0) {
            throw new Exception("未找到位置");
        }

        for (int start = pos + 1; start < size; start++) {
            array[start - 1] = array[start];
        }

        size--;
    }

    /**
     * 查找
     * @param x
     * @return
     */
    int find(E x) {
        for (int i = 0; i < size; i++) {
            if (x.equals(array[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 索引
     * @param pos
     * @return
     * @throws Exception
     */
    E get(int pos) throws Exception {
        if (pos >= size || pos < 0) {
            throw new Exception("未找到该位置");
        }

        return (E) array[pos - 1];
    }

    boolean empty() {
        return size == 0;
    }

    int size() {
        return size;
    }

    void print() {
        System.out.println(Arrays.toString(array));
    }
}
