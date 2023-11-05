/**
 * @author Caijg
 * @create 2023/10/24 20:51
 */
public class Test {
    public static void main(String[] args) throws Exception {
        SingleLinkedList<Integer> sl = new SingleLinkedList<>();

//        sl.push_back(0);
//        sl.push_back(1);
//        sl.push_back(2);
//        sl.push_back(3);
//        sl.push_back(4);
//        sl.push_back(5);

//        sl.pop_back();
//
//        sl.pop_front();

        sl.insert(0, 0);
        sl.insert(1, 1);
        sl.insert(2, 2);
        sl.insert(3, 3);
        sl.insert(4, 4);
        sl.insert(5, 5);
        //sl.erase(sl.find(5));

//        sl.erase(3);

        //sl.print();
        System.out.println(sl.empty());
    }
}
