package queue;

/**
 *  Double Ended Queue  双端队列
 */
public class Deque<E> {

    private E[] data;
    private int size;
    private int front;
    private int tail;


    public Deque (int capacity){
        data= (E[]) new Object[capacity];
        front = 0;
        tail = 0;
        size = 0;
    }

    public Deque(){
        this(10);
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        return false;
    }

    public int getSize(){
        return size;
    }

    private void resize(int newCapacity){
        E[] newData = (E[]) new Object[newCapacity];
        for (int i=0; i<size; i++){
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    public void addFront(E e){
        if (size == data.length){
            resize(2*data.length);
        }
        front = (front -1 + data.length) % data.length;
        data[front] = e;
        size += 1;
    }

    public void addLast(E e){
        if (size == data.length){
            resize(2*data.length);
        }
        data[tail] = e;
        tail = (tail+1) % data.length;
        size += 1;
    }

    public E removeFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");


        E ret = data[front];
        data[front] = null;
        size -= 1;
        front = (front + 1) % data.length;

        if (size == data.length / 4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    public E removeLast(){
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[tail];
        data[(tail-1 + data.length)%data.length] = null;
        size -= 1;
        tail = (tail -1 + data.length) % data.length;
        if (size == data.length / 4 && data.length/2 != 0){
            resize(data.length/2);
        }
        return ret;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Deque: Size = %d, Capacity = %d ", size, data.length));
        res.append("front [");
        for (int i=0; i<size; i++){
            res.append(data[(front+i)%data.length]);
            if ((front + i +1) % data.length != tail){
                res.append(", ");
            }
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main (String[] args){
        Deque<Integer> queue = new Deque<>();
        for (int i = 0; i < 25; i++) {

            if (i==5){
                i=5;
            }
            if (i%2 ==0) {
                queue.addLast(i);
                System.out.println(queue);
            }
            else{
                queue.addFront(i);
                System.out.println(queue);
            }

            if (i % 3 == 2) {
                if (i % 6 == 2) {
                    queue.removeFront();
                    System.out.println(queue);
                }
                else {
                    queue.removeLast();
                    System.out.println(queue);
                }
            }
        }
    }


}


