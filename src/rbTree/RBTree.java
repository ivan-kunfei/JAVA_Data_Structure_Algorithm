package rbTree;

import avlTree.AVLTree;
import queue.LinkedListQueue;
import stack.LinkedListStack;

import java.util.ArrayList;

public class RBTree<K extends Comparable<K>,V>{
    // 二分搜索树内部**私有**节点类
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private class Node{
        public K key;
        public V value;
        public Node left, right;
        public boolean color;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
            left = null;
            right = null;
            color = RED; // 初始化节点默认是红色
        }
    }

    private Node root;
    private int size;

    public RBTree(){
        root = null;
        size=0;
    }

    private boolean isRed(Node node){
        if(node==null){
            return BLACK;
        }
        return node.color;
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public boolean isBST(){
        ArrayList<K> keys = new ArrayList<>();
        inOrderKey(root,keys);
        for(int i=0; i<keys.size()-1; i++){
            if (keys.get(i).compareTo(keys.get(i+1))>0){
                return false;
            }
        }
        return true;
    }


    private void inOrderKey(Node node, ArrayList<K> array){
        if(node!=null){
            inOrderKey(node.left,array);
            array.add(node.key);
            inOrderKey(node.right,array);
        }
    }

    public void add(K key, V value){
        this.root = add (this.root, key, value);
        root.color = BLACK;  // 保持最终根节点为黑色节点
    }

    // 向以node为根节点插入元素
    // 返回插入新节点后红黑树的根
    private Node add (Node node, K key, V value){
        if (node == null){
            size ++;
            return new Node(key, value);
        }
        else if (key.compareTo(node.key) <0 ){
            node.left = add(node.left,key,value);
        }
        else if (key.compareTo(node.key) >0){
            node.right = add(node.right, key,value);
        }
        else{
            node.value = value;
        }

        // 维护红黑树
        // 左黑右红  情况1: 往2叉点的右边插入了新节点  情况2: 插入了3节点的中间，当前节点为原三叉点的红节点
        if (!isRed(node.left) && isRed(node.right)){
            node = leftRotate(node);
        }
        if (isRed(node.left) && isRed(node.left.left)){
            node = rightRotate(node);
        }
        if (isRed(node.left) && isRed(node.right)){
            flipColor(node);
        }
        return node;
    }

    //    node                         x
    //    /  \         左旋转         /   \
    //   T1   x     ------------>  node  T3
    //       / \                   /  \
    //      T2 T3                 T1  T2
    private Node leftRotate(Node node){
        Node x = node.right;
        // 左旋转
        node.right = x.left;
        x.left = node;
        x.color = node.color;  // x的颜色等于node原本的颜色
        node.color = RED;    // node的颜色是红色 与x形成了一个三节点
        return x;
    }

    //    node                         x
    //    /  \         右旋转         /   \
    //    x  T2     ------------>   y   node
    //   / \                            /  \
    //  y  T1                          T1  T2
    private Node rightRotate(Node node){
        Node x = node.left;
        // 右旋转
        node.left = x.right;
        x.right = node;
        x.color = node.color;
        node.color = RED;
        return x;
    }

    // 颜色翻转
    private void flipColor(Node node){
        node.color = RED;
        node.left.color = BLACK;
        node.right.color = BLACK;
    }


    public boolean contains(K key){
        if (root == null){
            return false;
        }
        if (root.key.equals(key)){
            return true;
        }
        else {
            return contains(root,key);
        }
    }

    private boolean contains(Node node, K key){
        if (node == null){
            return false;
        }
        else if(node.key.equals(key)){
            return true;
        }
        else if(key.compareTo(node.key)<0){
            return contains(node.left,key);
        }
        else {
            return contains(node.right,key);
        }
    }

    // 二分搜索树的前序遍历
    public void preOrder(){
        preOrder(root);
    }

    private void preOrder(Node node){
        if (node == null){
            return;
        }
        System.out.println(node.key);
        preOrder(node.left);
        preOrder(node.right);
    }

    // 二分搜索树的中序遍历  中序遍历的结果是顺序的
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(Node node){
        if (node==null){
            return;
        }
        inOrder(node.left);
        System.out.println(node.key+": "+ node.value);
        inOrder(node.right);
    }

    // 后序遍历
    public void postOrder(){
        postOrder(root);
    }

    private void postOrder(Node node){
        if (node == null){
            return;
        }
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.key);
    }

    // 中序遍历非递归 创建私有的命令类
    private class Command{
        String s;
        Node node;
        Command(String s, Node node){
            this.s = s;
            this.node = node;
        }
    }

    // 中序遍历的非递归
    public void inOrderNR(){
        LinkedListStack<Command> stack = new LinkedListStack<>();
        stack.push(new Command("go",root));
        while (! stack.isEmpty()){
            Command currentCommand = stack.pop();
            if (currentCommand.s.equals("p")){
                System.out.println(currentCommand.node.key +": "+currentCommand.node.value);
            }
            else{
                if (currentCommand.node.right != null){
                    stack.push(new Command("go",currentCommand.node.right));
                }
                stack.push(new Command("p",currentCommand.node));
                if (currentCommand.node.left != null){
                    stack.push(new Command("go", currentCommand.node.left));
                }
            }
        }
    }

    // 前序遍历的非递归
    public void preOrderNR(){
        LinkedListStack<Command> stack = new LinkedListStack<>();
        stack.push(new Command("go",root));
        while (! stack.isEmpty()){
            Command currentCommand = stack.pop();
            if (currentCommand.s.equals("p")){
                System.out.println(currentCommand.node.key);
            }
            else{
                if (currentCommand.node.right != null){
                    stack.push(new Command("go",currentCommand.node.right));
                }
                if (currentCommand.node.left != null){
                    stack.push(new Command("go", currentCommand.node.left));
                }
                stack.push(new Command("p",currentCommand.node));
            }
        }
    }

    // 后续遍历的非递归
    public void postOrderNR(){
        LinkedListStack<Command> stack = new LinkedListStack<>();
        stack.push(new Command("go",root));
        while (! stack.isEmpty()){
            Command currentCommand = stack.pop();
            if (currentCommand.s.equals("p")){
                System.out.println(currentCommand.node.key);
            }
            else{
                stack.push(new Command("p",currentCommand.node));
                if (currentCommand.node.right != null){
                    stack.push(new Command("go",currentCommand.node.right));
                }
                if (currentCommand.node.left != null){
                    stack.push(new Command("go", currentCommand.node.left));
                }
            }
        }
    }

    // 层序遍历是利用对列实现的
    public void levelOrder(){
        LinkedListQueue<Node> queue = new LinkedListQueue<>();
        queue.enqueue(root);
        while (! queue.isEmpty()){
            Node currentNode = queue.dequeue();
            System.out.println(currentNode.key);
            if (currentNode.left != null){
                queue.enqueue(currentNode.left);
            }
            if (currentNode.right != null){
                queue.enqueue(currentNode.right);
            }
        }
    }

    // 寻找二分搜索树最小元素
    public K minimum(){
        if (size==0){
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root).key;
    }

    private Node minimum(Node node){
        if (node.left == null){
            return node;
        }
        else{
            return minimum(node.left);
        }
    }

    // 删除二分搜索树最小元素
    public K removeMin(){
        size--;
        K ret = minimum();
        removeMin(root);
        return ret;
    }

    private Node removeMin(Node node){
        if (node.left == null){
            Node rightNode = node.right;
            node.right = null;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public K removeMax(){
        K ret = maximum();
        removeMax(root);
        size --;
        return ret;
    }

    private Node removeMax(Node node){
        if (node.right==null){
            Node leftNode = node.left;
            node.left = null;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public K maximum(){
        if (size==0){
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root);
    }

    private K maximum(Node node){
        if (node.right == null){
            return node.key;
        }
        else{
            return maximum(node.right);
        }
    }

    private Node getNode(K key, Node node){
        if (node==null){
            return null;
        }
        if (key.compareTo(node.key)<0){
            return getNode(key,node.left);
        }
        else if(key.compareTo(node.key)>0){
            return getNode(key,node.right);
        }
        else {
            return node;
        }
    }

    public V remove(K key){
        Node node = getNode(key,root);
        if (node!=null){
            size--;
            root = remove(root, key);
            return node.value;
        }
        return null;
    }

    public V get(K key){
        Node node = getNode(key,root);
        if (node!=null){
            return node.value;
        }
        return null;
    }

    private Node remove(Node node, K key){
        if(node==null){
            return null;
        }
        Node returnNode;
        if (key.compareTo(node.key)<0){
            node.left = remove(node.left,key);
            returnNode = node;
        }
        else if (key.compareTo(node.key) >0 ){
            node.right = remove(node.right,key);
            returnNode = node;
        }
        else { // e == node.e
            if (node.left == null) {
                returnNode = node.right;
            } else if (node.right == null) {
                returnNode = node.left;
            } else { // left right 都不为null
                // 找到当前点 右子树的最小值，右子树去掉这个最小值，最小值的右子树等于这个新子树，
                // 最小值的左子树等于当前点的左子树
                // 返回这个最小值来代替当前点
                Node rightMinimum = minimum(node.right);
                rightMinimum.right = remove(node.right,rightMinimum.key);
                rightMinimum.left = node.left;
                node.right = null;
                node.left = null;
                returnNode = rightMinimum;
            }
        }
        // 防止空指针异常
        if (returnNode == null){
            return null;
        }

        return returnNode;
    }



    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        generateBSTString(root, 0,res);
        return res.toString();
    }

    private void generateBSTString(Node node, int depth, StringBuilder res){
        if (node == null){
            res.append(generateDepthString(depth) + "null\n");
            return;
        }
        String color;
        if (isRed(node)){
            color = "R";
        }
        else{
            color = "B";
        }
        res.append(color+" "+generateDepthString(depth) + node.key +"\n");
        generateBSTString(node.left, depth+1,res);
        generateBSTString(node.right, depth+1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i=0; i<depth; i++) {
            res.append("*");
        }
        return res.toString();

    }


    public static void main (String[] args){
        RBTree<Integer,Integer> rbt = new RBTree();
        rbt.add(3,0);
        rbt.add(9,0);
        rbt.add(1,0);
        rbt.add(1,0);
        rbt.add(2,0);
        rbt.add(0,0);
        rbt.add(-1,0);
        rbt.add(-2,0);
        rbt.add(-3,0);
        rbt.add(-4,0);

        System.out.println("contains 4: " + rbt.contains(4));
        System.out.println("contains 1: " + rbt.contains(1));
        System.out.println(rbt);System.out.println("is BST: "+ rbt.isBST());

        System.out.println(rbt.size);

        rbt.remove(-1);
        System.out.println(rbt);
        System.out.println(rbt.size);
    }
}
