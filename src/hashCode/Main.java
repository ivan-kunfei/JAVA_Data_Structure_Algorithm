package hashCode;

import java.util.HashMap;
import java.util.HashSet;
// java默认会为每一个创建的object创建一个hashcode, 这个hashcode是基于内存地址生成的
public class Main {
    public static void main(String[]args){
        int a = 42;
        System.out.println(((Integer)a).hashCode());
        int b = -42;
        System.out.println(((Integer)b).hashCode());

        double c = 3.1415925;
        System.out.println(((Double)c).hashCode());

        String d = "imooc";
        System.out.println(d.hashCode());

        Student student1 = new Student(3,2,"kunfei","chen");
        System.out.println(student1.hashCode());
        Student student2 = new Student(3,2,"KUNFEI","Chen");
        System.out.println(student2.hashCode());

        HashSet<Student> set = new HashSet<>();
        set.add(student1);

        HashMap<Student,Integer> scores = new HashMap<>();
        scores.put(student1,100);
        scores.put(student2,200);
        // 当 hashcode相等 并且 equals也相等的时候  hashmap或hashset才会认为是相等的
        System.out.println(scores.get(student1));

    }
}
