package linkedList;

public class LinkedListDummyHead <E>{

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

    private Node dummyHead;
    int size;

    public LinkedListDummyHead(){
        // 虚拟头结点
        dummyHead = new Node(null,null);
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
        add(e,0);
    }

    // 链表中间添加元素
    // 根据索引插入元素在链表中不是一个常用的操作
    public void add(E e, int index){
        if (index <0 || index>size){
            throw new IllegalArgumentException("Add failed. Illegal index.");
        }

        Node previous = dummyHead;
        for (int i=0; i < index; i++){
            previous = previous.next;
        }
        previous.next = new Node(e,previous.next);
        size += 1;
    }

    public void addLast(E e){
        add(e,size);
    }

    // 获得立案标的第index（0-based）个位置的元素
    // 在链表中不是一个常用操作，练习用
    public E get(int index){
        if (index<0 || index >= size){
            throw new IllegalArgumentException("Get ailed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i=0; i<index; i++){
            cur = cur.next;
        }
        return cur.e;
    }

    public E getFirst(){
        return get(0);
    }
    // 获得链表的最后一个元素
    public E getLast(){
        return get(size-1);
    }

    // 获得立案标的第index（0-based）个位置的元素
    // 在链表中不是一个常用操作，练习用
    public void set(int index, E e){
        if (index<0 || index >= size){
            throw new IllegalArgumentException("Get ailed. Illegal index.");
        }
        Node cur = dummyHead.next;
        for (int i=0; i<index; i++){
            cur = cur.next;
        }
        cur.e = e;
    }

    // 查找链表中是否有元素e
    public boolean contains(E e){
        Node cur = dummyHead.next;
        while(cur != null){
            if(cur.e.equals(e)){
                return true;
            }
            cur = cur.next;
        }
        return false;
    }

    // 从链表中删除index（0-based)位置的元素，返回删除的元素
    // 在链表中不是一个常用操作，练习用
    public E remove(int index){
        if (index<0 || index >= size){
            throw new IllegalArgumentException("Remove ailed. Illegal index.");
        }

        Node prev = dummyHead;
        for (int i=0; i<index; i++){
            prev = prev.next;
        }

        Node retNode = prev.next;
        prev.next = retNode.next;
        retNode.next = null;
        size -= 1;
        return retNode.e;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size-1);
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }




}
