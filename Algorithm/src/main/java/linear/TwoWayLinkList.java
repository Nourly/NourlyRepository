package linear;

import java.util.Iterator;

/**
 * @author Nour
 */
public class TwoWayLinkList<T> implements Iterable<T> {

    private Node head;
    private Node last;
    private int N;

    private class Node {
        public Node(T item, Node pre, Node next) {
            this.item = item;
            this.pre = pre;
            this.next = next;
        }

        public T item;
        public Node pre;
        public Node next;
    }

    public TwoWayLinkList() {
        this.head = new Node(null, null, null);
        this.last = null;
        this.N = 0;
    }

    /**
     * 清空链表
     */
    public void clear() {
        this.head.next = null;
        this.last = null;
        this.N = 0;
    }

    public T getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.next.item;
    }

    public T getLast() {
        if (isEmpty()) {
            return null;
        }
        return last.item;
    }

    public int length() {
        return N;
    }

    public void insert(T t) {
        if (isEmpty()) {
            //如果链表为空：

            //创建新的节点
            Node newNode = new Node(t, head, null);
            //让新节点成为尾节点
            last = newNode;
            //让头节点指向尾节点
            head.next = last;
        } else {
            //如果链表不为空
            Node oldLast = last;
            //创建新的节点
            Node newNode = new Node(t, oldLast, null);
            //让当前的尾节点指向新节点
            oldLast.next = newNode;
            //让新节点成为尾节点
            last = newNode;
        }
        N++;
    }

    /**
     * i处插入节点
     *
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        //找到i位置的前一个节点
        Node pre = head;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        //找到i位置的节点
        Node curr = pre.next;
        //创建新节点
        Node newNode = new Node(t, pre, curr);
        //让i位置的前一个节点的下一个节点变为新节点
        pre.next = newNode;
        //让i位置的前一个节点变为新节点
        curr.pre = newNode;
        //元素个数+1
        N++;
    }

    public T get(int i) {
        Node n = head.next;
        for (int index = 0; index < i; index++) {
            n = n.next;
        }
        return n.item;
    }

    public int indexOf(T t) {
        Node n = head;
        for (int i = 0; n.next != null; i++) {
            n = n.next;
            if (n.next.equals(t)) {
                return i;
            }
        }
        return -1;
    }

    public T remove(int i) {
        //找到i位置的前一个节点
        Node pre = head.next;
        for (int index = 0; index < i; index++) {
            pre = pre.next;
        }
        //找到i位置的节点
        Node curr = pre.next;
        //找到i位置的下一个节点
        Node nextNode = curr.next;
        //让i位置的前一个节点和下一个节点变为i位置的下一个节点
        pre.next = nextNode;
        //让i位置的下一个节点的上一个节点变为i位置的前一个节点
        nextNode.pre = pre;
        return curr.item;
    }

    public boolean isEmpty() {
        return N == 0;
    }


    @Override
    public Iterator<T> iterator() {
        return new TIterator();
    }

    private class TIterator implements Iterator {
        private Node n;

        public TIterator() {
            this.n = head;
        }

        @Override
        public boolean hasNext() {
            return n.next != null;
        }

        @Override
        public T next() {
            n = n.next;
            return n.item;
        }
    }

}
