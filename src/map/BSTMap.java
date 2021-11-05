package map;

import avlTree.AVLTree;

public class BSTMap <K extends Comparable<K>,V> implements Map<K,V>{

    private class Node{
        public K key;
        public V value;
        public Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        @Override
        public String toString(){
            return this.key + ": " + this.value;
        }
    }

    private Node root;
    private int size;

    public BSTMap(){
        root = null;
        size = 0;
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size == 0;
    }

    public void add(K key, V value){
        root = add(key, value, root);
    }

    private Node add(K key, V value, Node node){
        if (node == null){
            size ++;
            return new Node(key,value);
        }
        if (key.compareTo(node.key)==0){
            node.value = value;
            return node;
        }
        else if (key.compareTo(node.key) >0){
            node.right = add(key,value,node.right);
        }
        else{
            node.left = add(key,value,node.left);
        }
        return node;
    }

    private Node getNode(Node node, K key){
        if (node == null){
            return null;
        }
        else if (node.key.equals(key)){
            return node;
        }
        else if (key.compareTo(node.key)>0){
            return getNode(node.right,key);
        }
        else{
            return getNode(node.left,key);
        }
    }

    public boolean contains(K key){
        return getNode(root, key) != null;
    }

    public V get(K key){
        Node node = getNode(root, key);
        if (node == null){
            return null;
        }
        else{
            return node.value;
        }
    }

    public void set(K key, V value){
        Node node = getNode(root, key);
        if (node == null){
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
        node.value = value;
    }

    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        return minimum(node.left);
    }

//    private Node maximum(Node node){
//        if (node.right == null){
//            return node;
//        }
//        return maximum(node);
//    }

    public Node removeMin(Node node){
        if (node.left==null){
            return node.right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public V remove(K key){
        V value = get(key);
        root = remove (root, key);
        return value;
    }

    private Node remove(Node node, K key){
        if (node == null){
            return node;
        }
        if (key.compareTo(node.key) < 0){
            node.left = remove(node.left, key);
        }
        else if (key.compareTo(node.key) > 0){
            node.right = remove(node.right, key);
        }
        else{
            size --;
            if (node.left == null){
                return node.right;
            }
            if (node.right == null){
                return node.left;
            }
            Node minimum = minimum(node.right);
            node.right = removeMin(node.right);
            minimum.left = node.left;
            minimum.right = node.right;
            node = minimum;
        }
        return node;
    }

    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node);
        inOrder(node.right);
    }


    public static void main(String[] args){
        BSTMap<String,Integer> map = new BSTMap<>();
        String[] wordsArray = {"I","I","believe","that","you","that","love","me"};
        for( String s: wordsArray){
            if (map.contains(s)){
                map.set(s, map.get(s)+1);
            }
            else{
                map.add(s,1);
            }
        }
        map.inOrder();
        System.out.println("total different words: "+map.getSize());
        map.remove("that");
        map.inOrder();
        System.out.println("total different words: "+map.getSize());

        System.out.println("======================");
        AVLMap<String,Integer> avlMap = new AVLMap<>();

        for( String s: wordsArray){
            if (avlMap.contains(s)){
                avlMap.set(s, avlMap.get(s)+1);
            }
            else{
                avlMap.add(s,1);
            }
        }

        System.out.println("total different words: "+avlMap.getSize());
        avlMap.remove("that");
        avlMap.avl.inOrder();
        System.out.println("total different words: "+avlMap.getSize());
    }

}
