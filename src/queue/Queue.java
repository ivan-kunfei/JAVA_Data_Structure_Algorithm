package queue;

// 先进先出 队列
public interface Queue <E>{
    int getSize();
    boolean isEmpty();
    void enqueue(E e);
    E dequeue();
    E getFront();
}
