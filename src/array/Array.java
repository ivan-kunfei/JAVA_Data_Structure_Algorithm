package array;

public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity){
        // 不能直接 new E[]；需要先创建object类 然后强制类型转换
        data = (E[]) new Object[capacity];
        size = 0;
    }
    // 不传参的默认长度
    public Array(){
        this(1);
    }

    public Array(E[] arr){
        data = (E[]) new Object[arr.length];
        for (int i=0; i<arr.length; i++){
            data[i] = arr[i];
        }
        size = arr.length;
    }

    // 返回数组中的元素个数
    public int getSize(){
        return size;
    }

    // 返回数组容量
    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    // 向所有元素后添加一个新元素
    public void addLast(E e) {
        // 如果满了 就不能加了
        add(size,e);
    }

    // 向所有元素后添加一个新元素
    public void addFirst(E e){
        add(0,e);
    }

    public void add(int index, E e) {

        if (index<0 || index>size){
            throw new IllegalArgumentException("AddLast failed. Array is full");
        }
        // 如果满了就扩容
        if (size == data.length){
            resize (2*data.length);
        }
        for (int i = size -1; i>= index; i--){
            data [i+1] = data[i];
        }
        data[index] = e;
        size ++;
    }

    // 获取index索引位置的元素
    public E get (int index){
        if (index<0 || index>=size){
            throw new IllegalArgumentException("AddLast failed. Array is full");
        }
        return data[index];
    }

    // 获取位置第一个元素
    public E getFirst(){
        return data[0];
    }


    // 查找数组中是否有元素e
    public boolean contains(E e){
        for (int i =0; i<size; i++){
            if (data[i].equals(e)){
                return true;
            }
        }
        return false;
    }

    // 查找数组中元素e所在的索引， 如果不存在元素e, 则返回-1
    public int find(E e){
        for (int i=0; i<size; i++){
            if(data[i]==e){
                return i;
            }
        }
        return -1;
    }

    // 删除index位置的元素 返回删除的元素
    public E remove(int index){
        if (index < 0 || index >= size){
            throw new IllegalArgumentException("Remove failed. index = " + index);
        }
        E ret = data[index];
        for (int i=index; i<size-1; i++){
            data[i] = data[i+1];
        }
        size -= 1;
        data[size] = null;

        // 如果缩减为4/1长度再缩容
        if (size == data.length /4 && data.length/2 != 0){
           resize(data.length/2);
        }
        return ret;
    }

    // 删除第一个元素 返回被删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 删除最后一个元素 返回被删除的元素
    public E removeLast(){
        return remove(size-1);
    }

    // 从数组中删除元素e
    public void removeElement(E e){
        int index = find(e);
        if (index != -1){
            remove (index);
        }
    }

    // 修改index索引位置的元素为e
    public void set(int index, E e){
        if (index <0 || index >= size){
            throw new IllegalArgumentException("AddLast failed. Array is full");
        }
        data[index] = e;
    }


    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d, capacity= %d\n", size, data.length));
        res.append('[');
        for (int i=0; i<size; i++){
            res.append(data[i]);
            if (i != size-1){
                res.append(", ");
            }
        }
        res.append(']');
        return res.toString();
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i=0; i<size; i++){
            newData[i] = data[i];
        }
        data = newData;
    }

}
