package sort;

/**
 * @author Nour
 */
public class Shell {

    public static void sort(Comparable a[]){
        //1.根据数组的长度获取增长量h的值
        int h=1;
        if (h<a.length){
            h = a.length/2;
        }
        while (h>=1){
            //排序
            //2.1 找到待插入的元素
            for(int i=h;i<a.length;i++){
                //2.2把待插入的元素插入到有序数列中
                for(int j=i;j>=h;j-=h){
                    //待插入的元素是a[j]，比较a[j]和a[j-h]，比较
                    if(greater(a[j-h],a[j])){
                        exch(a,j-h,j);
                    } else{
                        break;
                    }
                }
            }

            //减少h的值
            h = h/2;
        }
        //2.希尔排序
    }

    public static boolean greater(Comparable v,Comparable w){
        return v.compareTo(w)>0;
    }

    public static void exch(Comparable []a,int i,int j){
        Comparable temp =a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
