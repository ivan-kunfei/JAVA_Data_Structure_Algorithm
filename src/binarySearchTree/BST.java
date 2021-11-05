package binarySearchTree;

import queue.LinkedListQueue;
import queue.Queue;
import stack.LinkedListStack;
import stack.Stack;

import java.util.LinkedList;

public class BST <E extends Comparable<E>>{
    // 二分搜索树内部**私有**节点类
    private class Node{
        public E e;
        public Node left, right;
        public Node(E e){
            this.e = e;
            left = null;
            right = null;
        }
    }

    private Node root;
    private int size;

    public BST(){
        root = null;
        size=0;
    }

    public int getSize(){
        return this.size;
    }

    public boolean isEmpty(){
        return this.size==0;
    }

    public void add(E e){
        if (this.root == null){
            this.root = new Node(e);
            size ++;
        }
        else{
            this.root = add (this.root, e);
        }

    }

    private Node add (Node node, E e){
        if (node == null){
            size ++;
            return new Node(e);
        }
        else if (e.compareTo(node.e) <0 ){
            node.left = add(node.left,e);
        }
        else if (e.compareTo(node.e) >0){
            node.right = add(node.right, e);
        }
        return node;
    }

    public boolean contains(E e){
        if (root.e.equals(e)){
            return true;
        }
        else {
            return contains(root,e);
        }
    }

    private boolean contains(Node node, E e){
        if (node == null){
            return false;
        }
        else if(node.e.equals(e)){
            return true;
        }
        else if(e.compareTo(node.e)<0){
            return contains(node.left,e);
        }
        else {
            return contains(node.right,e);
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
        System.out.println(node.e);
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
        System.out.println(node.e);
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
        System.out.println(node.e);
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
                System.out.println(currentCommand.node.e);
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
                System.out.println(currentCommand.node.e);
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
                System.out.println(currentCommand.node.e);
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
            System.out.println(currentNode.e);
            if (currentNode.left != null){
                queue.enqueue(currentNode.left);
            }
            if (currentNode.right != null){
                queue.enqueue(currentNode.right);
            }
        }
    }

    // 寻找二分搜索树最小元素
    public E minimum(){
        if (size==0){
            throw new IllegalArgumentException("BST is empty");
        }
        return minimum(root);
    }

    private E minimum(Node node){
        if (node.left == null){
            return node.e;
        }
        else{
            return minimum(node.left);
        }
    }

    // 删除二分搜索树最小元素
    public E removeMin(){
        E ret = minimum();
        size--;
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

    public E removeMax(){
        E ret = maximum();
        size --;
        removeMax(root);
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

    public E maximum(){
        if (size==0){
            throw new IllegalArgumentException("BST is empty");
        }
        return maximum(root);
    }

    private E maximum(Node node){
        if (node.right == null){
            return node.e;
        }
        else{
            return maximum(node.right);
        }
    }

    public void remove(E e){
        root = remove(root, e);
    }

    private Node remove(Node node, E e){
        if(node==null){
            return null;
        }
        if (e.compareTo(node.e)<0){
            node.left = remove(node.left,e);
            return node;
        }
        else if (e.compareTo(node.e) >0 ){
            node.right = remove(node.right,e);
            return node;
        }
        else{ // e == node.e
            size --;
            if(node.left==null){
                return removeMin(node);
            }
            else if (node.right==null){
                return removeMax(node);
            }
            else{ // left right 都不为null
                // 找到当前点 右子树的最小值，右子树去掉这个最小值，最小值的右子树等于这个新子树，
                // 最小值的左子树等于当前点的左子树
                // 返回这个最小值来代替当前点
                Node rightMinimum = new Node(minimum(node.right));
                removeMin(node.right);
                rightMinimum.right = removeMin(node.right);
                rightMinimum.left = node.left;
                node.right = null;
                node.left = null;
                return rightMinimum;
            }
        }
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
        res.append(generateDepthString(depth) + node.e + "\n");
        generateBSTString(node.left, depth+1,res);
        generateBSTString(node.right, depth+1, res);
    }

    private String generateDepthString(int depth){
        StringBuilder res = new StringBuilder();
        for (int i=0; i<depth; i++) {
            res.append("--");
        }
            return res.toString();

    }


    public static void main (String[] args){
        BST<Integer> bst = new BST();
        bst.add(3);
        bst.add(9);
        bst.add(1);
        bst.add(1);
        bst.add(2);
        bst.add(0);

        System.out.println(bst.contains(4));
        System.out.println(bst.contains(1));
        System.out.println(bst);

        System.out.println("前序遍历");
        bst.preOrder();
        System.out.println("====================");
        bst.preOrderNR();
        System.out.println("中序遍历");
        bst.inOrder();
        System.out.println("====================");
        bst.inOrderNR();
        System.out.println("后序遍历");
        bst.postOrder();
        System.out.println("====================");
        bst.postOrderNR();
        System.out.println("层序遍历");
        bst.levelOrder();

        System.out.println("maximum: "+bst.removeMax());
        System.out.println("minimum: "+bst.removeMin());
        bst.levelOrder();
        bst.add(0);
        bst.add(9);
        bst.add(7);
        bst.add(12);
        bst.add(6);
        bst.add(8);
        bst.add(11);
        bst.add(13);
        System.out.println("====================");
        bst.levelOrder();
        bst.remove(9);
        System.out.println("====================");
        bst.levelOrder();
        System.out.println("size: " + bst.size);
    }
}
