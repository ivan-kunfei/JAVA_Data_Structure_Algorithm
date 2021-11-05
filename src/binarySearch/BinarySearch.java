package binarySearch;

import mergeSort.MergeSort;

public class BinarySearch {

    private BinarySearch(){}

    public static<E extends Comparable<E>> int search(E[] data, E target){
        return search(data,0,data.length-1,target);
    }

    private static <E extends  Comparable<E>> int search(E[] data, int l, int r, E target){
        if (l>r){
            return -1;
        }
        int mid = l + (r-l)/2;
        if (data[mid].compareTo(target)==0){
            return mid;
        }
        if (data[mid].compareTo(target)<0){
            return search(data,mid+1,r,target);
        }
        else{
            return search(data,l,mid-1,target);
        }
    }

    // 大于target 的最小值索引
    public static <E extends  Comparable<E>> int upper(E[] data, E target,int l, int r){
        // 在 data[l,r]中寻找解
        if (l==r){
            return l;
        }
        int mid = l + (r-l)/2;
        if (data[mid].compareTo(target)<=0){
            return upper(data, target,mid+1,r);
        }
        else{
            return upper(data, target, l, mid);
        }
    }


    public static <E extends  Comparable<E>> int lowerCeil(E[] data, E target, int l, int r){
        if (l==r){
            return l;
        }
        int mid = l + (r-l)/2;
        if (data[mid].compareTo(target)>=0){
            return lowerCeil(data, target,l,mid);
        }
        else{
            return lowerCeil(data,target,mid+1,r);
        }
    }

    public static <E extends Comparable<E>> int lower(E[] data,E target,int l, int r){
        if (l==r){
            return l;
        }
        int mid = l + (r-l)/2+1;
        if (data[mid].compareTo(target)>=0){
            return lower(data, target, l,mid-1);
        }
        else{
            return lower(data,target,mid,r);
        }
    }
    public static void main (String[] args){
        Integer[] arr = {1,1,3,3,5,5,7,7};
        int result = BinarySearch.search(arr,6);
        System.out.println(result);

        result = BinarySearch.upper(arr,6,0,arr.length);
        System.out.println(result);

        result = BinarySearch.lowerCeil(arr,5,0,arr.length);
        System.out.println(result);

        result = BinarySearch.lower(arr,8,-1,arr.length-1);
        System.out.println(result);

        result = BinarySearch.lower(arr,1,-1,arr.length-1);
        System.out.println(result);

        result = BinarySearch.lower(arr,3,-1,arr.length-1);
        System.out.println(result);
    }
}
