package mergeSort;

import sort.ArrayGenerator;
import sort.SortingHelper;

import java.util.Arrays;

public class MergeSort {

    private MergeSort(){}

    public static <E extends  Comparable<E>> void sort(E[] arr){
        sort(arr, 0, arr.length-1);
    }

    private static <E extends  Comparable<E>> void sort(E[] arr, int l, int r){
        if (l>=r){
            return;
        }
        else{
            int mid = l + (r-l) / 2;   // 防止大的整型相加溢出
            sort(arr, l, mid);
            sort(arr, mid+1, r);
            merge(arr, l, r, mid);
        }

    }

    public static <E extends  Comparable<E>> void sort2(E[] arr){
        sort2(arr, 0, arr.length-1);
    }

    private static <E extends  Comparable<E>> void sort2(E[] arr, int l, int r){
        if (l>=r){
            return;
        }
        else{
            int mid = l + (r-l) / 2;   // 防止大的整型相加溢出
            sort2(arr, l, mid);
            sort2(arr, mid+1, r);
            if (arr[mid].compareTo(arr[mid+1])>0) {
                merge(arr, l, r, mid);
            }
        }
    }

    public static <E extends  Comparable<E>> void sort3(E[] arr){
        E[] temp = Arrays.copyOf(arr, arr.length);
        sort3(arr, 0, arr.length-1,temp);
    }

    private static <E extends  Comparable<E>> void sort3(E[] arr, int l, int r, E[] temp){
        if (l>=r){
            return;
        }

//        if (r - l <= 20){
//            InsertSort.sort(arr,l,r);
//            return;
//        }

        else{
            int mid = l + (r-l) / 2;   // 防止大的整型相加溢出
            sort2(arr, l, mid);
            sort2(arr, mid+1, r);
            if (arr[mid].compareTo(arr[mid+1])>0) {  // 如果已经是顺序的就不用排序
                merge2(arr, l, r, mid, temp);
            }
        }
    }

    private static <E extends Comparable<E>> void merge2(E [] arr, int l, int r, int mid, E[] temp) {
        System.arraycopy(arr,l, temp, l, r+1-l);
        int i = l;
        int j = mid+1;
        for (int k=l; k<=r; k++ ){
            if(i>mid){
                arr[k] = temp[j];
                j ++;
            }
            else if (j>r){
                arr[k] = temp[i];
                i ++;
            }
            else if (temp[i].compareTo(temp[j]) <0){
                arr[k] = temp[i];
                i ++;
            }
            else{
                arr[k] = temp[j];
                j ++;
            }
        }

    }

    private static <E extends Comparable<E>> void merge(E [] arr, int l, int r, int mid) {
        E[] temp = Arrays.copyOfRange(arr, l, r + 1);
        int i = l;
        int j = mid+1;
        for (int k=l; k<=r; k++ ){
            if(i>mid){
                arr[k] = temp[j-l];
                j ++;
            }
            else if (j>r){
                arr[k] = temp[i-l];
                i ++;
            }
            else if (temp[i-l].compareTo(temp[j-l]) <0){
                arr[k] = temp[i-l];
                i ++;
            }
            else{
                arr[k] = temp[j-l];
                j ++;
            }
        }

    }


    // 自底向上归并排序
    public static <E extends Comparable<E>> void sort4(E [] arr){
        E [] temp = Arrays.copyOf(arr, arr.length);
        int n = arr.length;
        // 遍历合并的区间长度
        for (int sz = 1; sz <n; sz+=sz){
            // 遍历合并的两个区间的起始位置
            // 合并 [i, i+sz-1]  和 [i+size, Math.min(i+sz+sz-1, n-1)]
            for (int i = 0; i+sz < n; i+= sz + sz){
                if (arr[i + sz -1].compareTo(arr[i+sz]) > 0){
                    merge2(arr, i, Math.min(i+sz+sz-1, n-1),i+sz-1,temp);
                }
            }
        }
    }


    public static void main (String [] args){
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);
        SortingHelper.sortTest("MergeSort",arr);
        SortingHelper.sortTest("MergeSort2",arr2);
        SortingHelper.sortTest("MergeSort3",arr3);
        SortingHelper.sortTest("MergeSort4",arr4);
    }
}
