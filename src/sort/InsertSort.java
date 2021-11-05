package sort;

import java.util.Arrays;

public class InsertSort {

    // 插入排序法比选择排序法的优点是，会提前终止。所以如果不是最差情况，复杂度小于O(n^2)
    // 尤其对于有序数组来说，复杂的是O（n）级别的
    public static <E extends Comparable<E>> void sort (E[] arr){

        for(int i = 0; i<arr.length; i++){
            E temp = arr[i];
            for (int j=i; j-1>=0 ; j--){
                if (arr[j-1].compareTo(temp) > 0){
                    arr[j] = arr[j-1];
                }
                else{
                    arr[j] = temp;
                    break;
                }
               if (j==1){
                   arr[0] = temp;
               }
            }
        }
    }

    // 对指定区间进行排序
    public static <E extends Comparable<E>> void sort (E[] arr, int l, int r){

        for(int i = l; i<= r; i++){
            E temp = arr[i];
            for (int j=i; j-1>=l ; j--){
                if (arr[j-1].compareTo(temp) > 0){
                    arr[j] = arr[j-1];
                }
                else{
                    arr[j] = temp;
                    break;
                }
                if (j==l+1){
                    arr[l] = temp;
                }
            }
        }
    }

    public static <E> void swap (E[] arr, int i, int j){
        E temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args){
//        Integer [] test = new Integer[]{0,0,0,9,8,7,5,3,2,0,0,0,0};
//        sort(test,3,8);
//        for (int x: test){
//            System.out.println(x);
//        }

        int[] dataSize= {10000,100000};
        for (int n: dataSize){

            System.out.println("Random Array:");
            Integer[] arr = ArrayGenerator.generateRandomArray(n,n);
            Integer[] arr2 = Arrays.copyOf(arr, arr.length);
            SortingHelper.sortTest("InsertSort",arr);
            SortingHelper.sortTest("SelectionSort",arr2);
            System.out.println();
            System.out.println("Ordered Array: ");
            arr = ArrayGenerator.generateOredredArray(n);
            arr2 = Arrays.copyOf(arr,arr.length);
            SortingHelper.sortTest("InsertSort",arr);
            SortingHelper.sortTest("SelectionSort",arr2);
            System.out.println();
        }
    }

}
