package dataStructures.list;

import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 * @author Xubo
 * @date 2021/1/22 19:15
 */
public class MyArrayList<T> implements Iterable<T> {

    /**
     * 默认长度
     */
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * 数组长度
     */
    private int size;
    /**
     * 数组
     */
    private T[] items;

    /**
     * 无参构造
     */
    public MyArrayList() {
        doClear();
    }

    /**
     * 对外接口，清理数据
     */
    public void clear() {
        doClear();
    }

    /**
     * 长度
     *
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 数组设置成size大小
     */
    public void trimToSize() {
        ensureCapacity(size());
    }

    /**
     * 获取
     *
     * @param idx
     * @return
     */
    public T get(int idx) {
        if (idx < 0 || idx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return items[idx];
    }

    /**
     * 将id设置成新值且返回
     *
     * @param idx
     * @param newVal
     * @return
     */
    public T set(int idx, T newVal) {
        if (idx < 0 || idx >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T old = items[idx];
        items[idx] = newVal;
        return old;
    }

    /**
     * 扩容
     *
     * @param newCapacity
     */
    public void ensureCapacity(int newCapacity) {
        if (newCapacity < size) {
            return;
        }
        T[] old = items;
        items = (T[]) new Objects[newCapacity];
        if (size >= 0) {
            System.arraycopy(old, 0, items, 0, size);
        }
    }

    /**
     * 在尾部添加一个元素
     *
     * @param val
     * @return
     */
    public boolean add(T val) {
        add(size(), val);
        return true;
    }

    /**
     * 在指定索引添加一个元素
     *
     * @param idx
     * @param t
     */
    public void add(int idx, T t) {
        if (items.length == size) {
            //扩容
            ensureCapacity(size() * 2 + 1);
        }
        for (int i = size; i > idx; i--) {
            items[i] = items[i - 1];
        }
        items[idx] = t;
        size++;
    }

    /**
     * 移除一个元素
     *
     * @param idx
     * @return
     */
    public T remove(int idx) {
        T removeItem = items[idx];
        for (int i = idx; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        size--;
        return removeItem;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIteator();
    }

    private class ArrayListIteator implements java.util.Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            return items[current++];
        }

        @Override
        public void remove() {
            MyArrayList.this.remove(--current);
        }
    }

    /**
     * 清理
     */
    private void doClear() {
        size = 0;
        ensureCapacity(DEFAULT_CAPACITY);
    }
}
