package leetcodePractice;

public class Sum{

    public static int sum(int[] arr){
        return sum(arr, 0);
    }

    private static int sum(int[] arr, int l){
        if(l== arr.length){
            return 0;
        }
        return arr[l] + sum(arr,l+1);
    }

    public static int sum_2(int[] arr){
        return sum_2(arr, 0);
    }

    private static int sum_2(int[] arr, int l){
        if(l== arr.length -1 ){
            return arr[l];
        }
        return arr[l] + sum(arr,l+1);
    }

    public static void main (String[]args){
        int[] arr = {1,2,3,4,3};
        System.out.println(sum(arr));
        System.out.println(sum_2(arr));
    }



}
