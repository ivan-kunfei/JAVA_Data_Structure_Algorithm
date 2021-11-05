package sort;

import java.util.Arrays;

public class ShellSort {

    private ShellSort(){}

    public static <E extends Comparable<E>> void sort(E[] data){

        int h = data.length/2;
        while(h>=1){
            for(int start=0; start<h; start++){
                // 对data[start, start+h. start + 2h。。。。]进行插入排序
                int cur = start;
                while (cur < data.length){
                    E curItem = data[cur];
                    int compare = cur - h;
                    while(compare>=start) {
                        if (curItem.compareTo(data[compare])>=0){
                            data[compare+h] = curItem;
                            break;
                        }else{
                            data[compare+h] = data[compare];
                        }
                        if (compare==start){
                            data[compare] = curItem;
                        }
                        compare = compare - h;
                    }
                    cur = cur + h;
                }
            }
            h /= 2;
        }
    }

    public static void main (String[] args){
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n,n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort",arr);
        SortingHelper.sortTest("QuickSort",arr2);
        SortingHelper.sortTest("HeapSort2",arr3);
        SortingHelper.sortTest("ShellSort",arr4);

    }

}
