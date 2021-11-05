package sort;

public class SelectionSort {

    // 选择排序法 每次选择剩下的数字里最小的数字
    // 复杂度为 O（n^2）
    // 使用原地排序  不需要申请额外空间
    // 泛型extends comparable 接口
    public static <E extends Comparable<E>> void sort(E[] list){
        for (int i=0; i<list.length; i++){
            E origin = list[i];
            E minimum = list[i];
            int index = i;
            for (int j= i; j<list.length; j++){
                if (list[j].compareTo(minimum) < 0 ){
                    minimum = list[j];
                    index = j;
                }
            }
            list[i] = minimum;
            list[index] = origin;
        }
    }

    public static void main(String[] args){
        Student[] array =  {new Student("a",93),
                            new Student("b",92),
                            new Student("c",100)};
        SelectionSort.sort(array);
        for (Student s: array){
            System.out.println(s);
        }

        Integer [] dataSize = {1000,100000};
        for (int n:dataSize) {
            Integer[] arr = ArrayGenerator.generateRandomArray(n, n);
            SortingHelper.sortTest("SelectionSort",arr);
        }
    }


}
