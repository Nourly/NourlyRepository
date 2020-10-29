package linear;

public class SequenceList<T> {

    private T[] eles;

    private int N;

    //构造方法
    public SequenceList(int capacity) {
        this.eles = (T[]) new Object[capacity];

        this.N = 0;
    }

    //清空线性表
    public void clear() {
        this.N = 0;
    }

    //判断当前线性表是否是空表
    public boolean isEmpty() {
        return N == 0;
    }

    //获取当前线性表的长度
    public int length() {
        return N;
    }

    //获取指定位置的元素
    public T get(int i) {
        return eles[i];
    }

    //向线性表中添加元素T
    public void insert(T t) {
        eles[N++] = t;
    }

    //在i元素处插入元素t
    public void insert(int i, T t) {
        //先把i索引处的元素以及其后面的元素依次向后移动一位
        //arraycopy可以学习一下
        if (N - 1 - i >= 0) System.arraycopy(eles, i, eles, i + 1, N - 1 - i);
        eles[i] = t;
        //再把t元素放到i索引处即可
    }

    //删除指定位置i处的元素，并返回该元素
    public T remove(int i) {
        //记录所有i处的值
        T current = eles[i];
        //arraycopy可以学习一下
        if (N - 1 - i >= 0) System.arraycopy(eles, i + 1, eles, i, N - 1 - i);
        // 元素个数减1
        N--;
        return current;
    }

    //查找t元素第一次出现的位置
    public int indexOf(T t) {
        for (int i = 0; i < N; i++) {
            if (eles[i].equals(t)) return i;
        }
        return -1;
    }
}
