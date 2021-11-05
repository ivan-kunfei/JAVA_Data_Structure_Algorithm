package sort;
public class BinaryConversion {


    //  10进制转2进制的算法的复杂度为 O(logn)
    public static int get_n (int num){
        int[] byte_arrays = {};
        int i = 0;
        while (num != 0){
            int x = num % 2;  // 二进制的某位数
            num /= 2;
            i += 1;
        }
        return i;
    }

    public static void main(String[] args){
        int re = BinaryConversion.get_n(5);
        System.out.println(re);
    }

}
