package segmentTree;

public class SegmentTree <E>{

    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger){
        this.merger = merger;

        data = (E[]) new Object[arr.length];
        for(int i=0; i<arr.length; i++){
            data[i] = arr[i];
        }
        tree = (E[]) new Object[4*arr.length];
        buildSegmentTree(0,0,data.length-1);
    }

    private void buildSegmentTree(int treeIndex, int l, int r){
        if (l == r){
            tree[treeIndex] = data[l];
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l+ (r-l) / 2;
        buildSegmentTree(leftTreeIndex, l, mid);
        buildSegmentTree(rightTreeIndex, mid+1, r);
        // 根据具体业务逻辑 合并两个线段树
        tree[treeIndex] = merger.merge(tree[leftTreeIndex],tree[rightTreeIndex]);
    }

    // 查询
    public E query(int queryL, int queryR){
        if(queryL<0 || queryR >= data.length || queryR<0 || queryR>=data.length){
            throw new IllegalArgumentException("Index is illegal.");
        }

        return query(0,0, data.length-1, queryL, queryR);
    }

    private E query(int treeIndex, int l, int r, int queryL, int queryR){
        if (l==queryL && r == queryR){
            return tree[treeIndex];
        }

        int mid = l + (r-l)/2;
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        if (queryL > mid){
            return query(rightTreeIndex,mid+1,r,queryL,queryR);
        }
        else if (queryR <= mid){
            return query(leftTreeIndex,l,mid,queryL,queryR);
        }
        else{
            E leftResult = query(leftTreeIndex,l,mid,queryL,mid);
            E rightResult = query(rightTreeIndex,mid+1,r,mid+1,queryR);
            return merger.merge(leftResult,rightResult);
        }
    }


    public int getSize(){
        return data.length;
    }

    public E get(int index){
        if(index <0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        return data[index];
    }

    private int leftChild(int index){
        return 2 * index + 1;
    }

    private int rightChild(int index){
        return 2 * index + 2;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(("["));
        for(int i=0; i<tree.length; i++){
            if(tree[i] != null){
                res.append(tree[i]);
            }
            else{
                res.append("null");
            }
            if (i!= tree.length-1){
                res.append(", ");
            }
        }
        res.append("]");
        return res.toString();
    }

    // 更新值
    public void set(int index, E e){
        if(index <0 || index >= data.length){
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0,0,data.length-1,index,e);
    }

    private void set(int treeIndex, int l,int r,int index,E e){
        if (l==r){
            tree[treeIndex] = e;
            return;
        }
        int leftTreeIndex = leftChild(treeIndex);
        int rightTreeIndex = rightChild(treeIndex);
        int mid = l + (r - l) / 2;
        if (index>mid){
            set(rightTreeIndex,mid+1, r, index, e);
        }
        else{
            set(leftTreeIndex, l, mid, index, e);
        }
        tree[treeIndex] = merger.merge(tree[leftTreeIndex], tree[rightTreeIndex]);
    }

    public static void main(String[] args){
        Integer[] nums = {-2,0,3,-5,2,-1};
        SegmentTree<Integer> segTree = new SegmentTree<>(nums, new Merger<Integer>() {
            @Override
            public Integer merge(Integer a, Integer b) {
                return a + b;
            }
        });
        System.out.println(segTree);


        System.out.println(segTree.query(0,2));
        System.out.println(segTree.query(2,5));
        System.out.println(segTree.query(0,5));

    }
}
