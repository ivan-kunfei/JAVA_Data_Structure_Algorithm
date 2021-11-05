package hashTable;


import sun.reflect.generics.tree.Tree;

import java.util.TreeMap;

public class HashTable <K,V> {

    // 素数表
    private final int[] capacity
            = {53, 97, 193, 389, 769, 1543, 3079, 6151, 12289, 24593,
    49157, 98317, 196613, 393241, 786433, 1572869, 3145739, 6291469,
    12582917, 25165843, 50331653, 100663319, 201326611, 402653189, 805306457, 1610612741};

    private static final int upperTol = 10; //平均每个地址对哈希冲突的容忍上界
    private static final int lowerTol = 2; //平均每个地址对哈希冲突的容忍下界
    private int capacityIndex = 0;  // 哈希表的初始空间为53
    private TreeMap<K,V> t;
    private TreeMap<K,V>[] hashtable;
    private int M; // 选择一个合适的素数来取模
    private int size;

    public HashTable() {
        this.M = capacity[capacityIndex];
        size = 0;
        hashtable = new TreeMap[M];
        for (int i = 0; i < M; i++) {
            hashtable[i] = new TreeMap<>();
        }
    }


    private int hash(K key){
        return key.hashCode() & 0x7fffffff % M; //消除hashcode的符号 转化为一个索引
    }

    // 动态地扩容或者缩容
    public int getSize(){
        return size;
    }

    public void add(K key, V value){
        TreeMap<K,V> map = hashtable[hash(key)];
        if(map.containsKey(key)){
            map.put(key,value);
        }
        else{
            map.put(key,value);
            size ++;
            if(size >= M*upperTol && capacityIndex+1<capacity.length){
                capacityIndex ++;
                resize(capacity[capacityIndex]);
            }
        }
    }

    private void resize(int newM){
        TreeMap<K,V>[] newHashTable = new TreeMap[newM];
        for(int i=0; i<newM; i++){
            newHashTable[i] = new TreeMap<>();
        }
        int oldM = M;
        this.M = newM;
        for(int i=0; i<oldM; i++){
            TreeMap<K,V> map = hashtable[i];
            for(K key : map.keySet()){
                newHashTable[hash(key)].put(key,map.get(key));
            }
        }
        this.hashtable = newHashTable;
    }

    public V remove(K key){
        TreeMap<K,V> map = hashtable[hash(key)];
        V ret = null;
        if(map.containsKey(key)){
            ret = map.remove(key);
            size --;
            if (size<lowerTol*M && capacityIndex>0){
                capacityIndex--;
                resize(capacity[capacityIndex]);
            }
        }
        return ret;
    }

    public void set(K key, V value){
        TreeMap<K,V> map = hashtable[hash(key)];
        if (map.containsKey(key)){
            map.put(key, value);
        }
        else{
            throw new IllegalArgumentException(key + " doesn't exist!");
        }
    }

    public boolean contains(K key){
        return hashtable[hash(key)].containsKey(key);
    }

    public V get(K key){
        return hashtable[hash(key)].get(key);
    }

}
