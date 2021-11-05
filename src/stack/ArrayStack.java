package stack;

import array.Array;

public class ArrayStack <E> implements Stack<E> {
    Array<E> array;

    public ArrayStack(){
        array = new Array<>();
    }
    public ArrayStack (int capacity) {
        array = new Array<>(capacity);
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public void push(E e) {
        array.addLast(e);
    }

    @Override
    public boolean isEmpty() {
        if (array.getSize()>0){
            return false;
        }
        return true;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public E pop() {
        return array.removeLast();
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("Stack: ");
        res.append('[');
        for (int i = 0; i < array.getSize(); i++) {
            res.append(array.get(i));
            if (i != array.getSize() - 1) {
                res.append(", ");
            }
        }
        res.append("] top");
        return res.toString();
    }
}
