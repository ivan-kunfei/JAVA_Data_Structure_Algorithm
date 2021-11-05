package linkedList;

public class LinkedList <E>{

    // 私有Node类，只有在LinkedList内部才能使用Node类
    private class Node{
        public E e;
        public Node next;

        public Node(E e, Node next){
            this.e = e;
            this.next = next;
        }

        public Node(E e){
            this(e, null);
        }

        public Node(){
            this(null,null);
        }

        @Override
        public String toString(){
            return e.toString();
        }
    }

    private Node head;
    int size;

    public LinkedList(){
        head = null;
        size=0;
    }

    // 获取链表中的元素个数
    public int getSize(){
        return size;
    }

    // 返回连表为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 表头添加元素e
    public void addFirst(E e){
        head = new Node(e,head);
        size += 1;
    }

    // 链表中间添加元素
    // 根据索引插入元素在链表中不是一个常用的操作
    public void add(E e, int index){
        if (index <0 || index>size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        if (index == 0){
            addFirst(e);
        }
        else {
            Node previous = head;
            for (int i=0; i < index-1; i++){
                previous = previous.next;
            }
            previous.next = new Node(e,previous.next);
            size += 1;
        }
    }


    public void addLast(E e){
        add(e,size);
    }
}
