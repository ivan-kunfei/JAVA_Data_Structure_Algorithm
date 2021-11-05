package set;

import binarySearchTree.BST;

public class BSTSet<E extends Comparable<E>> implements Set<E>{

    private BST<E> bst;
    public BSTSet(){
        bst = new BST<>();
    }

    @Override
    public void add(E e) {
        bst.add(e);
    }

    @Override
    public boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public int getSize() {
        return bst.getSize();
    }

    @Override
    public boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public void remove(E e) {
        bst.remove(e);
    }


    public static void main (String[] args){

        BSTSet<String> bstSet = new BSTSet<>();
        String[] arr = {"a","good","job","I","am","a","good","student"};
        for (String s:arr){
            bstSet.add(s);
        }

        System.out.println("集合的元素个数： "+bstSet.getSize());
        System.out.println("是否含有good: " + bstSet.contains("good"));
        bstSet.remove("good");
        System.out.println("集合的元素个数： "+bstSet.getSize());
        System.out.println("是否含有good: " +bstSet.contains("good"));

        System.out.println("=================================");

        AVLSet<String> avlSet = new AVLSet<>();
        for (String s:arr){
            avlSet.add(s);
        }

        System.out.println("集合的元素个数： "+avlSet.getSize());
        System.out.println("是否含有good: " + avlSet.contains("good"));
        avlSet.remove("good");
        System.out.println("集合的元素个数： "+avlSet.getSize());
        System.out.println("是否含有good: " +avlSet.contains("good"));
    }

}
