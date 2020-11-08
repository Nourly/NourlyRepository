package linear;

import java.util.Iterator;

/**
 * @param <T>
 * @author Nour
 */
public class LinkList<T> implements Iterable<T> {

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

    public int length(){
        return this.N;
    }
    public void clear() {
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

    /**
     * 指定位置插入
     *
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        //找到i位置前一节点
        Node pre = head;
        for (int index = 0; index <= i - 1; index++) {
            pre = pre.next;
        }
        //找到i节点的位置
        Node curr = pre.next;
        //创建新节点，并且新节点需要指向原来i位置的节点
        Node newNode = new Node(t, curr);
        //原来i位置的前一节点指向新节点即可
        pre.next = newNode;
        //元素个数+1
        N++;
    }

    public T remove(int i) {
        //找到i位置的前一个节点
        Node pre = head;
        for (int index = 0; index <= i - 1; i++) {
            pre = pre.next;
        }
        //找到i位置的下一个节点
        Node curr = pre.next;
        //找到i位置的下一个节点
        Node nextNode = curr.next;
        //前一个节点指向下一个节点
        pre.next = nextNode;
        //元素个数减1
        N--;
        return curr.item;
    }

    public int indexOf(T t) {
        //从头节点开始，依次找到每一个节点，取出item和t比较，如果相同就找到了
        Node n = head;
        int i = 0;
        while (n.next != null) {
            n = n.next;
            if (n.item.equals(t)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new LIterator();
    }

    private class LIterator implements Iterator {
        private Node n;

        public LIterator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public Object next() {
            n = n.next;
            return n.item;
        }
    }

}
