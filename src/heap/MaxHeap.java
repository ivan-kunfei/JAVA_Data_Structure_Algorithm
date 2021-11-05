package heap;

import array.Array;
import javafx.scene.control.RadioMenuItem;
import sort.SortingHelper;

import java.util.Random;

public class MaxHeap <E extends Comparable<E>>{

    private Array<E> data;

    public MaxHeap(int capacity){
        data = new Array<>(capacity);
    }

    public MaxHeap(){
        data = new Array<>();
    }

    // Heapify构造函数
    // 将一个数组整理成堆的形状
    // 时间复杂度为 o（n）
    public MaxHeap(E[] arr){
        data = new Array<E>(arr);
        // 得到最后一个非叶子节点, 朝前遍历下沉
        int index = parent(data.getSize()-1);
        while(index>=0){
            siftDown(index);
            index = index - 1;
        }
    }

    public int size(){
        return data.getSize();
    }

    public boolean isEmpty(){
        return data.isEmpty();
    }

    // 返回父节点的索引
    private int parent(int index){
        if (index == 0){
            throw new IllegalArgumentException("index-0 doesn't have parent.");
        }
        return (index-1) / 2;
    }

    // 返回左孩子节点索引
    private int leftChild(int index){
        return index * 2 + 1;
    }

    private int rightChild(int index){
        return index * 2 + 2;
    }

    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }

    public void swap(int i, int j){
        if (i<0 || i >= this.size() || j<0 || j >= this.size()){
            throw new IllegalArgumentException("Index is illegal");
        }
        E t = data.get(i);
        data.set(i, data.get(j));
        data.set(j,t);
    }

    private void siftUp(int k){
        while (k >0 && data.get(parent(k)).compareTo(data.get(k))<0){
            swap(k, parent(k));
            k = parent(k);
        }
    }

    public E findMax(){
        if (data.getSize()==0){
            throw new IllegalArgumentException("Heap is empty!");
        }
        return data.get(0);
    }

    public E extractMax(){
        E maximum = findMax();
        swap(0, data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return maximum;
    }

    public void siftDown(int index){
        while (leftChild(index) < size()){
            int leftIndex = leftChild(index);
            int rightIndex = leftIndex + 1;
            int maxIndex = leftIndex;
            if(rightIndex < size() && data.get(rightIndex).compareTo(data.get(leftIndex))>0){
                maxIndex = rightIndex;
            }
            // 得到左右孩子节点中最大的节点的索引
            if (data.get(maxIndex).compareTo(data.get(index))>0){
                swap(maxIndex,index);
                index = maxIndex;
            }
            else{
                break;
            }
        }
    }

    // 把堆顶元素替换成当前元素 然后siftDown
    public E replace(E e){
        E ret = findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }



    public static void main(String[] args){
        int n = 1000000;
        MaxHeap<Integer> maxHeap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i<n; i++){
            maxHeap.add(random.nextInt(Integer.MAX_VALUE));
        }
        int[] arr = new int[n];
        for(int i = 0; i<n; i++){
            arr[i] = maxHeap.extractMax();
        }

        for(int i = 1; i<n; i++){
            if(arr[i-1] < arr[i]){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");


        Integer [] arr2 = {10,3,4,5,6,1,3,6,19,5,23};
        MaxHeap<Integer> maxHeap2 = new MaxHeap<>(arr2);
        Integer[]re = new Integer[arr2.length];
        for(int i = 0; i<arr2.length; i++){
            re[i] = maxHeap2.extractMax();
        }

        for(int i = 1; i< arr2.length; i++){
            if(re[i-1] < re[i]){
                throw new IllegalArgumentException("Error");
            }
        }
        System.out.println("Test MaxHeap completed.");
    }
}
