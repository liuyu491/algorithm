package datastructure.tree;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.*;
import java.util.regex.Matcher;

/*AVL树，自平衡的二叉搜索树*/
public class AVLTree<E extends Comparable> {
    private class AVLTreeNode{
        Comparable v;
        AVLTreeNode left;
        AVLTreeNode right;
        //节点默认高度为1，因为在二叉搜索树中插入新节点，新节点都是叶子节点，叶子节点的高度是1
        int height = 1;

        AVLTreeNode(Comparable v){
            this.v = v;
        }
    }
    private AVLTreeNode root = null;

    //插入节点
    public void insert(Comparable v){

        if(root==null){
            root = new AVLTreeNode(v);
        }else{
            root = insert(root,v);
        }


    }

    //递归地向树中插入节点
    private AVLTreeNode insert(AVLTreeNode node,Comparable v){
       if(node==null){
           return node =  new AVLTreeNode(v);
       }
       int cmp = v.compareTo(node.v);
       if(cmp<0){
           node.left = insert(node.left,v);
       }else if(cmp>0){
           node.right = insert(node.right,v);
       }else{
           return node;
       }

        node.height = 1+ Math.max(getHeight(node.left),getHeight(node.right));

        //LL
        if(getBalanceFactor(node)>1&&getBalanceFactor(node.left)>0){
            return rightRotate(node);
        }
        //LR
        if(getBalanceFactor(node)>1&&getBalanceFactor(node.left)<0){
            return LR(node);
        }
        //RR
        if(getBalanceFactor(node)<-1&&getBalanceFactor(node.right)<0){
            return leftRotate(node);
        }

        //RL
        if (getBalanceFactor(node)<-1&&getBalanceFactor(node.left)>0) {
            return RL(node);
        }

        //插入新节点后，树节点的高度可能会改变，回溯更新树节点高度。
        System.out.println(String.format("平衡因子：%d",getBalanceFactor(node)));
        return node;
    }

    //计算节点的平衡因子
    private int getBalanceFactor(AVLTreeNode node){

        if(node==null){
            return 0;
        }
        return getHeight(node.left)-getHeight(node.right);
    }


    //获取节点高度
    private int getHeight(AVLTreeNode node){
        if(node==null){
            return 0;
        }
        return node.height;
    }

    //判断树是否是二分搜索树
    public boolean isBST(){
        List<Comparable> list = new ArrayList<>();
        Stack<AVLTreeNode> stack = new Stack<>();
        //二分搜索树的中序遍历
        AVLTreeNode node = root;
        while(!stack.isEmpty()||node!=null){
            if(node!=null){
                stack.push(node);
                node = node.left;
            }else{
                node = stack.pop();
                list.add(node.v);
                node = node.right;
            }
        }

        for (int i = 1; i <list.size() ; i++) {
            if(list.get(i).compareTo(list.get(i-1))<0){
                return false;
            }
        }

        return true;
    }

    public boolean isBalanced(){
        return isBalanced(root);
    }

    private boolean isBalanced(AVLTreeNode node){
        if(node==null)
            return true;

        if(Math.abs(getBalanceFactor(node))>1){
            return false;
        }

        return isBalanced(node.left)&&isBalanced(node.right);

    }

    /*
    * 
    * 对y进行右旋转操作，旋转后原来指向y的引用要指向x
    *           y                     x
    *          / \                   /  \
    *         x   T4                z    y
    *        / \         ->        / \  / \
    *       z   T3                T1 T2 T3 T4
    *      / \
    *     T1  T2
    *     由于在不平衡节点的左孩子的左侧插入节点导致的不平衡，使用右旋操作来平衡二叉树
    * */
    
    
    private AVLTreeNode rightRotate(AVLTreeNode y){
        AVLTreeNode x = y.left;
        AVLTreeNode T3 = x.right;
        x.right = y;
        y.left = T3;
        //跟新旋转后的x,和y的高度，先跟新y的再更新x的。
        y.height = 1+Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1+Math.max(getHeight(x.left),getHeight(x.right));
        return x;
    }

    /*左旋操作，对于在不平衡节点的右孩子的右侧插入新节点后导致不平衡采取的措施*/
    private AVLTreeNode leftRotate(AVLTreeNode y){
        AVLTreeNode x = y.right;
        AVLTreeNode T3 = x.left;
        x.left  = y;
        y.right = T3;
        //跟新旋转后的x,和y的高度，先跟新y的再更新x的。
        y.height = 1+Math.max(getHeight(y.left),getHeight(y.right));
        x.height = 1+Math.max(getHeight(x.left),getHeight(x.right));
        return x;
    }

    /*LR:在不平衡节点的左孩子的右侧插入新节点使二叉树不平衡*/
    private AVLTreeNode LR(AVLTreeNode y){

        //1.先对y的左孩子做左旋操作，之后不平衡的情况变为LL，对y做右旋转操作后树将平衡。
        y.left = leftRotate(y.left);
        return rightRotate(y);
    }

    /*LR:在不平衡节点的右孩子的左侧插入新节点使二叉树不平衡*/
    private AVLTreeNode RL(AVLTreeNode y){

        //1.先对y的右孩子做右旋操作，之后不平衡的情况变为RR，对y做左旋转操作后树将平衡。
        y.right = leftRotate(y.right);
        return rightRotate(y);
    }



    public  String serialize(AVLTreeNode root) {
        if(root==null)
            return "[]";
        Queue<AVLTreeNode> que = new LinkedList<>();

        que.add(root);
        String res = "["+root.v;
        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0;i<size;i++){
                AVLTreeNode node = que.poll();
                if(node.left!=null ){
                    que.add(node.left);
                    res+=","+node.left.v;
                }

                else
                    res+=",null";

                if(node.right!=null ){
                    que.add(node.right);
                    res+=","+node.right.v;
                }

                else
                    res+=",null";
            }
        }

        res+="]";
        // System.out.println(res);
        return res;
    }

    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        Random random = new Random();
        for (int i = 0; i <10 ; i++) {
            avlTree.insert(random.nextInt(100));
        }


        System.out.println(avlTree.isBST());
        System.out.println(avlTree.isBalanced());
        System.out.println(avlTree.serialize(avlTree.root));
    }

}
