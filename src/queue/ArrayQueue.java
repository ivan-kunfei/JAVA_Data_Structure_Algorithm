package queue;

import array.Array;

/**
 * 这种普通队列的出队dequeue的时间复杂度为O（n），如果队列太长，运行量就很大，循环队列可以解决这个问题
 * @param <E>
 */
public class ArrayQueue<E> implements Queue<E>{

    private Array<E> array;

    public ArrayQueue(int capacity){
        array = new Array<E>(capacity);
    }

    public ArrayQueue(){
        array = new Array<E>();
    }

    @Override
    public int getSize(){
        return array.getSize();
    }

    @Override
    public boolean isEmpty(){
        return array.isEmpty();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public E getFront(){
        return  array.getFirst();
    }

    @Override
    public void enqueue(E e){
        array.addLast(e);
    }

    @Override
    public E dequeue(){
        return array.removeFirst();
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append("Qeue");
        res.append("front [");
        for (int i=0; i< array.getSize(); i++){
            res.append(array.get(i));
            if (i != array.getSize() -1){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){
        ArrayQueue<Integer> queue = new ArrayQueue<>();
        for(int i=0; i<10; i++) {
            queue.enqueue(i);
            System.out.println(queue);

            if (i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }

        }
    }
}
