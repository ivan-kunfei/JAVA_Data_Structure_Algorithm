package sort;
import java.util.ArrayList;
import java.util.List;

public class Factors {
    // 复杂度为 O(根号n)
    public static List<Integer> get_yueshu(int num){

        List<Integer> yueshu = new ArrayList<Integer>();
        for (int i =1; i*i<=num; i++){
            if (num % i == 0){
                int another_yue = num / i;
                yueshu.add(i);
                yueshu.add(another_yue);
            }
        }
        return yueshu;
    }

    public static void main(String[] args){
        List<Integer> re = Factors.get_yueshu(10);
        System.out.println(re);
    }

}
