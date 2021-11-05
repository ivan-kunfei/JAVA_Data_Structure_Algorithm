package unionFind;

// 相比起UF4来说 通过find 进行了路劲压缩
public class UnionFind5 implements UF{

    private int[] parent;
    private int[] rank;  // sz[i]表示以i为根的集合中元素的个数

    public UnionFind5(int size){
        parent = new int[size];
        rank = new int[size];
        for(int i = 0; i < size; i++){
            parent [i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public int getSize(){
        return parent.length;
    }


    private int find(int p){
        if (p <0 || p >= parent.length){
            throw new IllegalArgumentException("p is out of bound.");
        }
        while(parent[p] != p){
            // 路径压缩
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    // 查找过程，查找元素p所对应的集合编号
    // O(h)复杂度， h为树的高度
    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot){
            return;
        }

        // 根据两个元素所在树的元素个数不同判断合并方向
        // 将高度短的集合合并到高度长的集合上
        if(rank[pRoot] < rank[qRoot]){
            parent[pRoot] = qRoot;
        }
        else if(rank[pRoot] > rank[qRoot]){
            parent[qRoot] = pRoot;
        }
        else{
            parent[qRoot] = pRoot;
            rank[pRoot] += 1;
        }
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }
}
