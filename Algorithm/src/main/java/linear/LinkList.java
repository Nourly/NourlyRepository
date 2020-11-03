package linear;

/**
 * @author Nour
 * @param <T>
 */
public class LinkList<T> {

    private Node head;

    private int N;

    private class Node {
        //存储数据
        T item;
        //下一个节点
        Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    public LinkList() {
        //初始化头节点
        this.head = new Node(null, null);
        //初始化元素个数
        this.N = 0;
    }

    public void clean() {
        head.next = null;
        this.N = 0;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public T get(int i) {
        if (i >= this.N) {
            return null;
        }
        //通过循环从头节点开始往后找依次找i次就可以找到对应的元素
        Node n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }

    /**
     * 添加一个新节点
     *
     * @param t
     */
    public void insert(T t) {
        //找到当前元素最后一个节点
        Node n = head;
        while (n.next != null) {
            n = n.next;
        }
        Node newNode = new Node(t, null);
        n.next = newNode;
        N++;
    }
}
