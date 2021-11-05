package map;

import array.Array;

import java.util.ArrayList;

public class LinkedListMap<K,V> implements Map<K, V>{

    private class Node{
        public K key;
        public V value;
        public Node next;

        public Node (K key, V value, Node next){
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public Node(K key){
            this(key, null, null);
        }

        public Node(){
            this(null, null, null);
        }

        @Override
        public String toString(){
            return key.toString() + ": " + value.toString();
        }

    }

    private Node dummyHead;
    private int size;

    public LinkedListMap(){
        dummyHead = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    private Node getNode(K key){
        Node current = dummyHead;
        while (!key.equals(current.key)){
            current = current.next;
            if (current == null){
                break;
            }
        }
        return current;
    }

    public boolean contains(K key){
        return getNode(key) != null;
    }

    public V get(K key){
        Node node =  getNode(key);
        if (node != null){
            return node.value;
        }
        else{
            return null;
        }
    }

    // 如果key已经存在 就更新value
    public void add(K key, V value){
        Node node = getNode(key);
        if (node == null){
            Node nextNode = dummyHead.next;
            dummyHead.next = new Node(key,value,nextNode);
            size ++;
        }
        else{
            node.value = value;
        }
    }

    public void set(K key, V value){
        Node node = getNode(key);
        if (node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    public V remove(K key){
        Node preNode = dummyHead;
        Node curNode = preNode.next;
        while (curNode!= null){
            if (curNode.key.equals(key)){
                V value = curNode.value;
                preNode.next = curNode.next;
                curNode.next = null;
                size --;
                return value;
            }
            preNode = curNode;
            curNode = curNode.next;
        }
        throw new IllegalArgumentException(key + " doesn't exist!");
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            res.append(cur.toString()+"     ");
            cur = cur.next;
        }
        return res.toString();
    }

    public static void main(String[] args){
        LinkedListMap<String,Integer> map = new LinkedListMap<>();
        String[] wordsArray = {"I","I","believe","that","you","that","love","me"};
        for( String s: wordsArray){
            if (map.contains(s)){
                map.set(s, map.get(s)+1);
            }
            else{
                map.add(s,1);
            }
        }
        System.out.println("total different words: "+map.getSize());
        System.out.println(map);
        }
}

