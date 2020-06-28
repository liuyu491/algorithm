package datastructure;

import utils.TreeNode;

public class BST {

    private class BSTNode extends TreeNode{
        private BSTNode left;
        private BSTNode right;
        private int val;
        private int N;

        public void setV(int v) {
            this.val = v;
        }

        public void setN(int n) {
            N = n;
        }

        public int getN() {
            return N;
        }

        public BSTNode(int val) {
            this.val = val;
        }

        public int getVal(){
            return this.val;
        }

        public BSTNode getLeft() {
            return this.left;
        }

        public BSTNode getRight() {
            return this.right;
        }

        @Override
        public String toString() {
            return "BSTNode{" +
                    "val=" + val +
                    ", N=" + N +
                    '}';
        }
    }

    private BSTNode root = null;
    public void put(int v){
        if(root==null){
            root = new BSTNode(v);
            root.setN(1);
            return;
        }
        insert(root,v);
    }
    private BSTNode insert(BSTNode node, int v){

        if(node==null){
            BSTNode newNode = new BSTNode(v);
            int size = size(newNode);
            newNode.setN(size);
            return newNode;
        }
        if(v<node.val){
            node.left = insert(node.left,v);
        }
        else{
            node.right = insert(node.right,v);
        }
        int size = size(node);
        node.setN(size);
        return node;
    }

    public int get(int v){
        BSTNode res = get(root,v);
        if (res==null)
            return -1;
        else
            return res.getVal();
    }

    private BSTNode get(BSTNode root,int v){
        if(root==null){
            return null;
        }
        if(root.getVal()==v) {
            return root;
        }
        else if(root.getVal()>v){
            return get(root.left,v);
        }
        else {
            return get(root.right,v);
        }

    }

    public int floor(int v){
        BSTNode res = floor(root,v);
        if(res!=null)
            return res.getVal();
        return -1;
    }

    private BSTNode floor(BSTNode root,int v){
        if(root==null)
            return null;
        if(root.getVal()==v)
            return root;
        else if(root.getVal()>v)
            return floor(root.left,v);
        /*用t来记录从root的右子树中是否找到了v的floor值*/
        BSTNode t = floor(root.right, v);
        /*如果右子树中没有floor值那么root就是floor值*/
        if(t==null)
            return root;
        else
            return t;
    }

    public int ceil(int v){
        BSTNode res = ceil(root,v);
        if(res!=null)
            return res.getVal();
        return -1;
    }

    private BSTNode ceil(BSTNode root,int v){
        if(root==null)
            return null;
        if(root.getVal()==v)
            return root;
        else if(root.getVal()<v)
            return ceil(root.right,v);
        /*用t来记录从root的左子树中是否找到了v的floor值*/
        BSTNode t = ceil(root.left, v);
        /*如果右子树中没有floor值那么root就是floor值*/
        if(t==null)
            return root;
        else
            return t;

    }


    public BSTNode select(int index){
        return select(root,index);
    }

    private BSTNode select(BSTNode root, int index){
        int size = 1;
        if(root.left!=null){
            size = root.left.getN()+1;
        }
        if(size==index){
            return root;
        }
        if(size>index){
            return select(root.left,index);
        }
        else {
            return select(root.right,index-size);
        }
    }

    public int rank(int v){
        return rank(root,v);
    }

    private int rank(BSTNode root,int v){
       if(root==null){
           return 1;
       }
       if(v<root.getVal()){
           return rank(root.left,v);
       }
       else if(v>root.getVal()){
           return rank(root.right,v)+size(root.left)+1;
       }
       else {
           return size(root.left)+1;
       }
    }

    private int size(BSTNode root){
        if(root==null)
            return 0;
        return size(root.left)+size(root.right)+1;
    }
    public void inOrder(){
        inOrder(root);
    }

    private void inOrder(BSTNode root){
        if(root==null)
            return;
        inOrder(root.left);
        System.out.println(root);
        inOrder(root.right);
    }

    public BSTNode deleteNode(int v){
        return deleteNode(root,v);
    }

    private BSTNode deleteNode(BSTNode node, int v){
        if(node==null){
            return null;
        }
        if(node.getVal()==v) {
            BSTNode x = getMin(node.right);
            if(x==null){
                return node.left;
            }
            BSTNode rightNode = deleteMin(node.right);
            x.right = rightNode;
            x.left = node.left;
            node = null;
            return x;
        }
        else if(node.getVal()>v){
            node.left = deleteNode(node.left,v);
        }
        else {
            node.right = deleteNode(node.right,v);
        }
            return node;

    }

    public BSTNode deleteMin(){
        return deleteMin(root);
    }

    private BSTNode deleteMin(BSTNode root){
        if(root==null){
            return null;
        }
        if(root.left==null){
            root.right = null;
            return root.right;
        }
        BSTNode rightNode = deleteMin(root.left);
        root.left = rightNode;
        return root;
    }

    private BSTNode getMin(BSTNode root){
        if(root==null){
            return null;
        }
        if(root.left==null){
            return root;
        }
        return getMin(root.left);
    }

    public static void main(String[] args) {
        int[] data = {19,5,24,1,18,3,8,13};
        BST tree = new BST();

        for(int num:data){
            tree.put(num);
        }
        int rank = tree.rank(8);
        System.out.println(rank);

    }
}
