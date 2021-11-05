package stack;

import array.Array;

// 栈就想一个杯子 只有一个口 后进先出
// 在计算机的世界里，栈拥有不可思议的应用
// 程序调用的系统栈
public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E e);
    E pop();
    E peek();
}
