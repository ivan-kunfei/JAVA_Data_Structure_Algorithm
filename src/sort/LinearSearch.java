package sort;


public class LinearSearch {
    //将构造函数设置为私有 从而使得LinearSearch这个类无法实例化
    private LinearSearch() {
    }

    // E是一个泛型
    public static <E> int search(E[] data, E target) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(target)) {
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

        // Integer[] data = {24, 18, 12, 9, 16, 66, 32, 4};
        int[] dataSize = {1000000,10000000};
        for (int n: dataSize) {

            Integer[] data = ArrayGenerator.generateOredredArray(n);

            // java里面泛型只能接收类对象 不能接受基本数据类型
            //int res = LinearSearch.search(data, 16);
            //System.out.println(res);

            long startTime = System.nanoTime();

            for (int k = 0; k < 100; k++) {
                LinearSearch.search(data, n);
            }
            long endTime = System.nanoTime();
            double time = (endTime - startTime) / 1000000000.0;
            System.out.println(time);
            System.out.println("n=" + n + ", 100 runs: " + time + " s");
        }

        Student [] students= {new Student("Alice",1),
                new Student("Bobo",1),
                new Student("Charles",1)};
        Student bobo = new Student("Bobo",1);
        int re3 = LinearSearch.search(students,bobo);
        System.out.println(re3);
    }
}