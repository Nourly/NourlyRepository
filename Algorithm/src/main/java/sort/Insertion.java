package sort;

public class Insertion {

    public static void sort(Comparable[] a){
        for(int i=0;i<a.length-1;i++){
            for(int j=i;j>0;j--){
                if(greater(a[j-1],a[j])){
                    exch(a,j-1,j);
                }else{
                    break;
                }
            }
        }
    }

    public static boolean greater(Comparable v,Comparable w){
        return v.compareTo(w)>0;
    }

    public static void exch(Comparable[] a, int i,int j){
        Comparable temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
}
