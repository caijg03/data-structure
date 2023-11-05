/**
 * @author Caijg
 * @create 2023/10/26 8:24
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.push_front(0);
        list.push_front(1);
        list.push_front(2);
        list.push_front(3);

        list.pop_back();
        list.pop_front();


        list.print();
    }

}
