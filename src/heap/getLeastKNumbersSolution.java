package heap;

public class getLeastKNumbersSolution {
    // 用优先队列（最大堆）来取得最小的K个元素（top k 问题）
    private static int[] getLeastNumbers(int[] arr, int k){
        PriorityQueue<Integer> pq= new PriorityQueue<>();
        for (int i=0; i<k; i++){
            pq.enqueue(arr[i]);
        }

        for(int i=k; i<arr.length; i++){
            if(pq.getFront().compareTo(arr[i])>0){
                pq.dequeue();
                pq.enqueue(arr[i]);
            }
        }
        int[] res = new int[k];
        for(int i=0; i<k; i++){
            res[i] = pq.dequeue();
        }
        return res;
    }

    public static void main(String[] args){
        int[] test = {1,10,9,6,4,2,7,5,0};
        int[] res = getLeastNumbers(test,5);
        for(int re: res){
            System.out.println(re);
        }
    }

}
