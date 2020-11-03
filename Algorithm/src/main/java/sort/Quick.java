package sort;

/**
 * @author Nour
 */
public class Quick {

    public static void sort(Comparable a[]) {
        sort(a, 0, a.length - 1);
    }

    private static void sort(Comparable a[], int lo, int hi) {
        if (hi <= lo) {
            return;
        }

        //需要对 lo到hi处的元素进行分组（左子组和右子组）
        int partition = partition(a, lo, hi);
        //让左子组有序
        sort(a, lo, partition);
        //让右子组有序
        sort(a, partition + 1, hi);
    }

    private static int partition(Comparable a[], int lo, int hi) {
        Comparable key = a[lo];
        int left = lo;
        int right = hi + 1;

        //切分
        while (true) {
            while (less(key, a[--right])) {
                if (right == lo) {
                    break;
                }
            }
            while (less(a[++left], key)) {
                if (left == hi) {
                    break;
                }
            }
            if (left >= right) {
                break;
            } else {
                exch(a, left, right);
            }
        }
        exch(a, lo, right);
        return right;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable a[], int i, int h) {
        Comparable temp = a[i];
        a[i] = a[h];
        a[h] = temp;
    }
}
