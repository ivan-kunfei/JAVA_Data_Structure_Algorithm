package leetcodePractice;

import java.util.Arrays;

// 归并求解逆序数组个数
public class reversePairs {

    public static<E extends Comparable<E>> void sort(E[] arr){
        Integer [] reversPairsCount = new Integer[]{0};
        sort(arr, 0, arr.length-1, reversPairsCount);
        System.out.println("一共有 " + reversPairsCount[0]);
    }

    public static<E extends Comparable<E>> void sort(E[] arr, int l, int r, Integer[] rc){
        if (l>=r){
            return;
        }
        int mid = (l + r) / 2;
        sort(arr, l,mid,rc);
        sort(arr, mid+1, r,rc);
        merge(arr,l,mid,r,rc);
    }

    public static<E extends Comparable<E>> void merge(E[] arr, int l, int mid, int r, Integer[] rc){
        E [] temp = Arrays.copyOfRange(arr,l,r+1);
        int i = l;
        int j = mid + 1;
        E min = null;
        for (int k=l; k <= r; k++){
            if(i > mid){
                min = temp[j-l];
                arr[k] = temp[j-l];
                j ++;
            }
            else if( j > r){
                min = temp[i-l];
                arr[k] = temp[i-l];
                i ++;
            }
            else if (temp[i-l].compareTo(temp[j-l]) <= 0 ){
                arr[k] = temp[i-l];
                i ++;
            }
            else{
                arr[k] = temp[j-l];
                // 计算逆序对数
                int count = mid + 1 - i;
                System.out.println("有 "+count);
                rc[0] += count;
                j ++;
            }
        }
    }

    public static void main (String [] args){
        Integer[] arr = new Integer[]{1,0,0,3,0,-1};
        sort(arr);
        for (int x: arr){
            System.out.println(x);
        }
    }




}
