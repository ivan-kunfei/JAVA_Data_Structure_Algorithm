package heap;

import sort.ArrayGenerator;
import sort.SortingHelper;

import java.util.Arrays;

public class HeapSort {
    private HeapSort(){}

    public static<E extends Comparable<E>> void sort(E[] data){
        MaxHeap<E> maxHeap = new MaxHeap<>();
        for(E e: data){
            maxHeap.add(e);
        }

        for(int i = data.length-1; i>=0; i--){
            data[i] = maxHeap.extractMax();
        }
    }

    // 原地堆排序
    public static<E extends Comparable<E>> void sort2(E[] data){
        if(data.length <= 1){
            return;
        }
        // 从最后一个非叶子节点向前遍历下沉 形成堆
        for (int i = (data.length-2)/2; i>=0; i--){
            int cur = i;
            int leftIndex = cur*2+1;
            // 下沉
            while (leftIndex<data.length){
                int maxIndex = leftIndex;
                int rightIndex = leftIndex+1;
                if (rightIndex<data.length && data[rightIndex].compareTo(data[leftIndex])>0){
                    maxIndex = rightIndex;
                }
                if (data[maxIndex].compareTo(data[cur])>0){
                    E temp = data[maxIndex];
                    data[maxIndex] = data[cur];
                    data[cur] = temp;
                    cur = maxIndex;
                    leftIndex = cur*2+1;
                }
                else{
                    break;
                }

            }
        }
        // 原地排序
        for(int i=0; i<data.length; i++){
            int length = data.length;
            int replaceIndex = length-i-1;
            E max = data[0];
            data[0] = data[replaceIndex];
            data[replaceIndex] = max;
            // 对第一个元素下沉
            int cur = 0;
            int leftIndex = cur*2+1;
            while (leftIndex< replaceIndex){
                int maxIndex = leftIndex;
                int rightIndex = leftIndex+1;
                if (rightIndex<replaceIndex && data[rightIndex].compareTo(data[leftIndex])>0){
                    maxIndex = rightIndex;
                }
                if (data[maxIndex].compareTo(data[cur])>0){
                    E temp = data[maxIndex];
                    data[maxIndex] = data[cur];
                    data[cur] = temp;
                    cur = maxIndex;
                    leftIndex = cur*2+1;
                }else{
                    break;
                }
            }
        }






    }

    public static void main(String[] args){
        int n = 1000000;
        Integer[] arr = ArrayGenerator.generateRandomArray(n,n);
        Integer[] arr2 = Arrays.copyOf(arr, arr.length);
        Integer[] arr3 = Arrays.copyOf(arr, arr.length);
        Integer[] arr4 = Arrays.copyOf(arr, arr.length);

        SortingHelper.sortTest("MergeSort",arr);
        SortingHelper.sortTest("QuickSort",arr2);
        SortingHelper.sortTest("HeapSort",arr3);
        SortingHelper.sortTest("HeapSort2",arr4);

    }

}
