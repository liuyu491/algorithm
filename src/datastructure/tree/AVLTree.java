package datastructure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;
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
        }
        insert(root,v);


    }

    //递归地向树
    private AVLTreeNode insert(AVLTreeNode node,Comparable v){
       if(node==null){
           node = new AVLTreeNode(v);
       }
       int cmp = v.compareTo(node.v);
       if(cmp<0){
           node.left = insert(node.left,v);
       }else if(cmp>0){
           node.right = insert(node.right,v);
       }
       //插入新节点后，树节点的高度可能会改变，回溯更新树节点高度。
        node.height = 1+ Math.max(getHeight(node.left),getHeight(node.right))+1;
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
        AVLTreeNode node = root;
        while(!stack.isEmpty()&&node!=null){
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


    public static void main(String[] args) {
        AVLTree<Integer> avlTree = new AVLTree<>();
        Random random = new Random();
        for (int i = 0; i <5000 ; i++) {
            avlTree.insert(random.nextInt(100000));
        }

        System.out.println(avlTree.isBST());
        System.out.println(avlTree.isBalanced());
    }

}
