package sort;

public class Selection {

    public static void sort(Comparable[] a) {
        for (int i = 0; i <= a.length - 2; i++) {
            //定义一个变量记录最小元素的索引，默认为参与排序的第一个元素所在的位置
            int minIndex = i;
            for (int j = i + 1; j < a.length; j++) {
                //需要对比min处的值和j的值
                if (greater(a[minIndex], a[j])) {
                    minIndex = j;
                }
            }
            //交换最小索引处的值和第一个的值
            exch(a, i, minIndex);
        }
    }

    public static boolean greater(Comparable v, Comparable w) {
        return v.compareTo(w) > 0;
    }

    public static void exch(Comparable[] a, int i, int j) {
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
