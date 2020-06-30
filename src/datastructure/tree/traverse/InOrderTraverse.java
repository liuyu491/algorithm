package datastructure.tree.traverse;

import utils.TreeNode;
import utils.TreeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
*二叉树的中序遍历，递归和非递归实现
* */
public class InOrderTraverse {
    private List<Integer> res = new ArrayList<>();


    public List<Integer> inOrderTraverseByRecur(TreeNode root){
        inOrderTraverseByRecurHleper(root);
        return res;
    }

    //递归实现
    private void inOrderTraverseByRecurHleper(TreeNode root){
        if(root==null){
            return;
        }
        inOrderTraverseByRecur(root.left);
        res.add(root.val);
        inOrderTraverseByRecur(root.right);
    }

    //非递归实现
    public List<Integer> inOrderTraverse(TreeNode root){


        Stack<TreeNode> stack = new Stack<>();
        if(root==null)
            return res;

        //
        while(!stack.isEmpty()||root!=null){
            //如果root非空，那么把root入栈，继续访问root的左孩子
            if(root!=null){
                stack.push(root);
                root = root.left;
                //如果root为空，说明栈顶元素的左孩子访问结束，打印栈顶元素了
            }else {

                root = stack.pop();

                res.add(root.val);
                //root打印结束，访问root的右孩子，如果右孩子为空，
                // 说明栈顶元素的左子树访问结束，弹出并打印栈顶元素。
                root = root.right;
            }

        }
        return res;
    }

    public static void main(String[] args) {
        String data = "[1,null,3,4,5]";
        TreeNode root = TreeUtils.array2tree(data);
        InOrderTraverse inOrderTraverse = new InOrderTraverse();
//        List<Integer> res = inOrderTraverse.inOrderTraverseByReCur(root);
        List<Integer> res = inOrderTraverse.inOrderTraverse(root);
//        List<Integer> res1 = inOrderTraverse.inOrderTraverseByRecur(root);


        System.out.println(res);
//        System.out.println(res1);

    }
}
