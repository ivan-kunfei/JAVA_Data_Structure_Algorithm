package trie;

import map.BSTMap;

public class Trie {

    private class Node{
        public boolean isWord;
        public BSTMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new BSTMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    public int getSize(){
        return size;
    }

    public void add(String word){
        Node cur = root;
        for (int i=0; i<word.length(); i++){
            Character c = word.charAt(i);
            Node nextNode = cur.next.get(c);
            if (nextNode != null){
                cur = nextNode;
            }
            else{
                cur.next.add(c, new Node());
                cur = cur.next.get(c);
            }
        }
        if (! cur.isWord) {
            size += 1;
            cur.isWord = true;
        }
    }

    public boolean contains(String word){
        Node cur = root;
        for (int i=0; i<word.length(); i++) {
            Character c = word.charAt(i);
            Node nextNode = cur.next.get(c);
            if (nextNode == null) {
                return false;
            } else {
                cur = nextNode;
            }
        }
        return cur.isWord;
    }

    // 前缀搜索
    public boolean isPrefix(String prefix){
        Node cur = root;
        for (int i=0; i< prefix.length(); i++) {
            Character c = prefix.charAt(i);
            Node nextNode = cur.next.get(c);
            if (nextNode == null) {
                return false;
            } else {
                cur = nextNode;
            }
        }
        return true;
    }

}
