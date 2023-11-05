/**
 * @author Caijg
 * @create 2023/10/29 16:24
 */
public class Test {
    public static void main(String[] args) throws Exception {
        MyStack<Integer> st = new MyStack<>();

        st.push(10);
        st.push(11);
        st.push(12);
        st.push(13);
        st.push(14);
        st.push(15);
        st.push(16);
        st.push(17);
        st.push(18);

        while (!st.empty()) {
            System.out.println(st.top());
            st.pop();
        }

        System.out.println(st.size());
    }
}
