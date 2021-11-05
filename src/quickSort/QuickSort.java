package quickSort;

import sort.ArrayGenerator;
import sort.SortingHelper;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    // 第一版的快速排序： 缺点，当数组完全有序的时候，复杂度为O（n*2)
    public static<E extends Comparable<E>> void sort(E [] arr){
        sort(arr, 0,arr.length-1);
    }

    private static<E extends Comparable<E>> void sort(E[] arr,int l, int r ){
        if (l>=r){
            return;
        }

        int p = partition(arr,l,r);
        sort(arr,l,p);
        sort(arr,p+1,r);
    }

    private static<E extends Comparable<E>> int partition(E[] arr, int l, int r){
        E e = arr[l];
        int cutIndex = l;
        for (int k=l+1; k <r+1; k++){
            E cur = arr[k];
            if (e.compareTo(cur)>0){
                swap(cutIndex+1,k,arr);
                cutIndex ++;
            }
        }
        swap(l, cutIndex,arr);
        return cutIndex;
    }

    public static<E extends Comparable<E>> void sort2(E [] arr){
        sort2(arr, 0,arr.length-1);
    }

    private static<E extends Comparable<E>> void sort2(E[] arr,int l, int r ){
        if (l>=r){
            return;
        }

        int p = partition2(arr,l,r);
        sort2(arr,l,p);
        sort2(arr,p+1,r);
    }
    private static<E extends Comparable<E>> int partition2(E[] arr, int l, int r){
        // 生成[l, r]随机索引
        int p = new Random().nextInt(r-l+1);
        p += l;
        swap(l,p,arr);

        E e = arr[l];
        int cutIndex = l;
        for (int k=l+1; k <r+1; k++){
            E cur = arr[k];
            if (e.compareTo(cur)>0){
                swap(cutIndex+1,k,arr);
                cutIndex ++;
            }
        }
        swap(l, cutIndex,arr);
        return cutIndex;
    }

    // 双路快速排序法
    public static<E extends Comparable<E>> void sort3(E [] arr){
        sort3(arr, 0,arr.length-1);
    }

    private static<E extends Comparable<E>> void sort3(E[] arr,int l, int r ){
        if (l>=r){
            return;
        }

        int p = partition3(arr,l,r);
        sort3(arr,l,p-1);
        sort3(arr,p+1,r);
    }
    private static<E extends Comparable<E>> int partition3(E[] arr, int l, int r){
        // 生成[l, r]随机索引
        int p = new Random().nextInt(r-l+1);
        p += l;
        swap(l,p,arr);
        E e = arr[l];
        // arr[l_1...i-1] <= v; arr[j+1...r] >= v
        int i = l+1, j= r;
        while(true){
            while(i<=j && arr[i].compareTo(e) <0){
                i ++;
            }
            while(j>=i && arr[j].compareTo(e) >0){
                j--;
            }
            if (i>=j){
                break;
            }
            swap(i,j,arr);
            i ++;
            j --;
        }
        swap(l,j,arr);
        return j;
    }

    public static<E> void swap(int a, int b, E[] arr){
        E temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public static void main(String [] args){
//        Integer [] arr = new Integer[]{7,5,1,3,4,2,8,9,9,9,0,0,0,11,32,11,11};
//        sort(arr);
//        System.out.println("====================");
//        for (int x : arr){
//            System.out.println(x);
//        }
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n,n);
        Integer[] arr2 = Arrays.copyOf(arr,arr.length);
        SortingHelper.sortTest("MergeSort2",arr);
        SortingHelper.sortTest("QuickSort",arr2);

        // 测试完全有序数组排序
        n = 10000;
        Integer[] arr3 = ArrayGenerator.generateOredredArray(n);
        SortingHelper.sortTest("MergeSort2",arr3);
        SortingHelper.sortTest("QuickSort",arr3);
        //SortingHelper.sortTest("QuickSort2",arr3);
        SortingHelper.sortTest("QuickSort3",arr3);

        // 全0数组
        arr = ArrayGenerator.generateRandomArray(n,1);
        SortingHelper.sortTest("MergeSort2",arr);
        SortingHelper.sortTest("QuickSort",arr);
        //SortingHelper.sortTest("QuickSort2",arr);
        SortingHelper.sortTest("QuickSort3",arr);

    }
}
