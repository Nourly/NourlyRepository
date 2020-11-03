package sort;

/**
 * @author Nour
 */
public class Merge {

    public static Comparable[] assist;

    public static void sort(Comparable[] a) {
        assist = new Comparable[a.length];

        int lo = 0;
        int hi = a.length - 1;

        sort(a, lo, hi);
    }


    private static void sort(Comparable[] a, int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        int mid = lo + (hi - lo) / 2;

        sort(a, lo, mid);
        sort(a, mid + 1, hi);

        merge(a, lo, mid, hi);

    }

    private static void merge(Comparable[] a, int lo, int mid, int hi) {
        //定义三个指针
        int i = lo;
        int p1 = lo;
        int p2 = mid + 1;

        //遍历，移动指针p1和指针p2，比较对应的值，找出小的那一个，放到辅助数组对应的索引中
        while (p1 <= mid && p2 <= hi) {
            if (less(a, p1, p2)) {
                assist[i++] = a[p1++];
            } else {
                assist[i++] = a[p2++];
            }

        }

        //遍历，如果p1没有走完，那么顺序移动p1指针，把对应的元素放在辅助数组相应的地方
        while (p1 <= mid) {
            assist[i++] = a[p1++];
        }

        //遍历，如果p2没有走完，那么顺序移动p2指针，把对应的元素放在辅助数组相应的地方
        while (p2 <= hi) {
            assist[i++] = a[p2++];
        }
        //把辅助数组中的元素拷贝到原数组中
        for (int index = lo; index <= hi; index++) {
            a[index] = assist[index];
        }
    }

    private static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    public static boolean less(Comparable[] a, int i, int j) {
        return a[i].compareTo(a[j]) < 0;
    }


}
