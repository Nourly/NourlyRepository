package linear;

import java.util.Iterator;

/**
 * @author Nour
 * @param <T>
 */
public class SequenceList<T> implements Iterable<T> {

    private T[] eles;

    private int N;

    /**
     * 扩容系数
     * 当数组大小为原数组的resizeAdd时便扩容
     */
    float resizeAdd = 0.75f;

    /**
     * 减容系数
     * 当数组大小为原数组的1/resizeSub时则减少容
     * 注：不可小于容量习俗resizeNum
     */
    float resizeSub = 4;

    /**
     * 构造方法
     * @param capacity
     */
    public SequenceList(int capacity) {
        this.eles = (T[]) new Object[capacity];

        this.N = 0;
    }

    /**
     * 清空线性表
     */
    public void clear() {
        this.N = 0;
    }

    /**
     * 判断当前线性表是否是空表
     * @return
     */
    public boolean isEmpty() {
        return N == 0;
    }

    /**
     * 获取当前线性表的长度
     * @return
     */
    public int length() {
        return N;
    }

    /**
     * 获取指定位置的元素
     * @param i
     * @return
     */
    public T get(int i) {
        return eles[i];
    }

    /**
     * 向线性表中添加元素T
     * @param t
     */
    public void insert(T t) {
        if (N >= eles.length * resizeAdd) {
            resize(2 * eles.length);
        }

        eles[N++] = t;
    }

    /**
     * 在i元素处插入元素t
     * @param i
     * @param t
     */
    public void insert(int i, T t) {
        if (N >= eles.length * resizeAdd) {
            resize(2 * eles.length);
        }

        //先把i索引处的元素以及其后面的元素依次向后移动一位
        //arraycopy可以学习一下
        if (N - i >= 0) {
            System.arraycopy(eles, i, eles, i + 1, N - i);
        }
        eles[i] = t;
        //再把t元素放到i索引处即可
        N++;
    }

    /**
     * 删除指定位置i处的元素，并返回该元素
     * @param i
     * @return
     */
    public T remove(int i) {
        //记录所有i处的值
        T current = eles[i];
        //arraycopy可以学习一下
        if (N - 1 - i >= 0) {
            System.arraycopy(eles, i + 1, eles, i, N - 1 - i);
        }
        // 元素个数减1
        N--;

        if (N < eles.length / resizeSub) {
            resize(eles.length / 2);
        }
        return current;
    }

    /**
     * 查找t元素第一次出现的位置
     * @param t
     * @return
     */
    public int indexOf(T t) {
        for (int i = 0; i < N; i++) {
            if (eles[i].equals(t)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据参数newSize，重置eles的大小
     *
     * @param newSize
     */
    public void resize(int newSize) {
        //定义一个临时数组，指向原数组
        T[] temp = eles;
        //创建新数组
        eles = (T[]) new Object[newSize];

        //把原数组的数据拷贝到新数组中即可
        for (int i = 0; i < N; i++) {
            eles[i] = temp[i];
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new SIterator();
    }

    private class SIterator implements Iterator {

        private int cusor;

        public SIterator() {
            this.cusor = 0;
        }

        @Override
        public boolean hasNext() {
            return cusor < N;
        }

        @Override
        public Object next() {
            return eles[cusor++];
        }
    }
}
