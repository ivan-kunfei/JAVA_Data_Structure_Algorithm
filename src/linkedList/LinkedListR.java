package linkedList;

import javafx.util.Pair;

public class LinkedListR<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
            this.next = null;
        }

        @Override
        public String toString(){
            return e.toString();
        }

    }

    private Node head;
    private int size;

    public LinkedListR(){
        this.head = null;
        this.size = 0;
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void add(int index, E e){
        head = add (head, index, e);
        size ++;
    }


    // 在以node为头结点的链表的index位置插入元素e，递归算法
    private Node add(Node node, int index, E e){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        if (index == 0) {
            return new Node(e, node);
        }
        node.next = add(node.next, index-1, e);

        return node;
    }

    // 在链表头添加新元素
    public void addFirst(E e){
        add(0, e);
    }

    // 在链表尾添加新元素
    public void addLast(E e){
        add(size, e);
    }

    // 获取链表的第index (0-based)个位置的元素
    public E get(int index) {
        return get(head,index);
    }

    // 在以node为头结点的链表中，找到第index个元素， 递归算法
    private E get(Node node, int index){
        if (index == 0){
            return node.e;
        }
        return get(node.next, index -1);
    }

    public E getFirst(){
        return get(0);
    }

    public E getLast(){
        return get(size-1);
    }

    public void set(int index, E e){
        set(head, index, e);
    }

    // 修改以node为头结点的链表中，第index个位置的元素为e,递归算法
    private void set(Node node, int index, E e){
        if (index == 0){
            node.e = e;
        }
        set(node.next, index-1, e);
    }

    public boolean contains(E e){
        return contains(head, e);
    }

    // 查抄是否存在元素e， 递归算发
    private Boolean contains(Node node, E e){
        if (node == null){
            return false;
        }
        if (node.e == e){
            return true;
        }
        return contains(node.next,e);
    }

    public E remove(int index){

        Pair<Node, E> res = remove(head, index);
        head = res.getKey();
        size --;
        return res.getValue();
    }

    private Pair<Node,E> remove(Node node, int index){
        if (index == 0){
            return new Pair<>(node.next,node.e);
        }
        Pair<Node, E> res = remove(node.next, index-1);
        node.next = res.getKey();
        return new Pair<>(node, res.getValue());
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size -1);
    }

    public void removeElement(E e){
        head = removeElement(head, e);
    }

    private Node removeElement(Node node, E e){
        if (node==null){
            return null;
        }
        if (node.e.equals(e)){
            size --;
            return removeElement(node.next, e);
        }

        node.next = removeElement(node.next, e);
        return node;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();

        Node cur = head;
        while(cur != null){
            res.append(cur + "->");
            cur = cur.next;
        }
        res.append("NULL");
        res.append("  Size "+ size);
        return res.toString();
    }



    public static void main (String []args){
        LinkedListR<Integer> linkedList = new LinkedListR<>();
        for (int i=0; i<5; i++){
            linkedList.addFirst(i);
            System.out.println(linkedList);
        }

        linkedList.add(3,2);
        System.out.println(linkedList);

        linkedList.remove(4);
        System.out.println(linkedList);

        linkedList.removeElement(2);
        System.out.println(linkedList);


        linkedList.removeFirst();
        System.out.println(linkedList);

        linkedList.removeLast();
        System.out.println(linkedList);
    }

}