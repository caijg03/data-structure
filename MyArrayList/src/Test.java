/**
 * @author Caijg
 * @create 2023/10/24 20:00
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MyArrayList<Integer> myArrayList = new MyArrayList<>();

        myArrayList.push_back(0);
        myArrayList.push_back(1);
        myArrayList.push_back(2);
        myArrayList.push_back(3);

        myArrayList.push_front(4);
        myArrayList.push_front(5);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);
        myArrayList.push_front(6);

        myArrayList.insert(7, 7);
        myArrayList.pop_front();
        myArrayList.pop_back();

        System.out.println(myArrayList.size());
        System.out.println(myArrayList.empty());
        System.out.println(myArrayList.find(0));




        myArrayList.print();
    }
}
