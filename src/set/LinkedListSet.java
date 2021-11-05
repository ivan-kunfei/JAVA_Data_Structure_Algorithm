package set;

import linkedList.LinkedListR;

public class LinkedListSet<E> implements Set<E>{

    private LinkedListR<E> linkedListR;

    public LinkedListSet(){
        linkedListR = new LinkedListR<>();
    }

    @Override
    public void remove(E e) {
        linkedListR.removeElement(e);
    }

    @Override
    public boolean contains(E e) {
        return linkedListR.contains(e);
    }

    @Override
    public int getSize() {
        return linkedListR.getSize();
    }

    @Override
    public void add(E e) {
        if (! linkedListR.contains(e)){
            linkedListR.addFirst(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return linkedListR.isEmpty();
    }

    public static void main (String[] args){

        LinkedListSet<String> linkedListSet = new LinkedListSet<>();
        String[] arr = {"a","good","job","I","am","a","good","student"};
        for (String s:arr){
            linkedListSet.add(s);
        }

        System.out.println("集合的元素个数： "+ linkedListSet.getSize());
        System.out.println("是否含有good: " + linkedListSet.contains("good"));
        linkedListSet.remove("good");
        System.out.println("集合的元素个数： "+ linkedListSet.getSize());
        System.out.println("是否含有good: " + linkedListSet.contains("good"));
    }

}
