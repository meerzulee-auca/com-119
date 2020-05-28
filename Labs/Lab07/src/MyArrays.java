
 interface MyComparator {
    int compare(Object o1, Object o2);
}
 interface MyComparable {
     int compareTo(Object o);
 }

public class MyArrays {
    public static void insertSort(double[] a){
        for(int i = 1; i < a.length; i++){
            double k = a[i];
            int j = i - 1;
            while(j > -1 && a[j] > k){
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = k;
        }
    }
    public static void insertSort(MyComparable[] comparables){
        for(int i = 1; i < comparables.length; i++){
            MyComparable k = comparables[i];
            int j = i - 1;
            while(j > -1 && comparables[j].compareTo(k) > 0){
                comparables[j + 1] = comparables[j];
                j--;
            }
            comparables[j + 1] = k;
        }
    }

    public static void insertSort(Object[] a, MyComparator comparator){
        for(int i = 1; i < a.length; i++){
            Object k = a[i];
            int j = i - 1;
            while(j > -1 && comparator.compare(a[j], k) > 0){
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = k;
        }
    }
}
