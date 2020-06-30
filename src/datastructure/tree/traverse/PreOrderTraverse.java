package datastructure.tree.traverse;



import utils.TreeNode;
import utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* 二叉树的前序遍历递归和非递归
* */
public class PreOrderTraverse {


    private List<Integer> res = new ArrayList<>();

    public List<Integer> preOrderTraverseByReCur(TreeNode root){
        preOrderTraverseByReCurHelper(root);
        return res;
    }


    public void preOrderTraverseByReCurHelper(TreeNode root){
        if(root==null){
            return;
        }
        res.add(root.val);
        preOrderTraverseByReCurHelper(root.left);
        preOrderTraverseByReCurHelper(root.right);
    }


    public List<Integer> preOrderTraverse(TreeNode root){
        if(root==null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()){
            //栈顶的元素是下一次循环要打印输出的元素
            TreeNode node = stack.pop();
            res.add(node.val);
            //因为栈是后进先出的数据结构，所以先入栈右孩子，在入栈左孩子
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }

        return res;
    }

    public static void main(String[] args) {
        String data = "[1,2,3,null,null,4,5]";
        TreeNode root = TreeUtils.array2tree(data);
        PreOrderTraverse preOrderTraverse = new PreOrderTraverse();
//        List<Integer> res = preOrderTraverse.preOrderTraverseByReCur(root);
        List<Integer> res = preOrderTraverse.preOrderTraverse(root);

        System.out.println(res);
    }

}
