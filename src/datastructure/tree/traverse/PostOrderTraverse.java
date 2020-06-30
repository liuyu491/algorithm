package datastructure.tree.traverse;

import utils.TreeNode;
import utils.TreeUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
* 二叉树的后序遍历
* */
public class PostOrderTraverse {
    List<Integer> res = new ArrayList<>();

    public List<Integer> postOrderTraverseByReCur(TreeNode root){
        postOrderTraverseByReCurHelper(root);
        return res;
    }


    //二叉树后序遍历递归实现
    private void postOrderTraverseByReCurHelper(TreeNode root){
        if(root==null){
            return;
        }
        postOrderTraverseByReCurHelper(root.left);
        postOrderTraverseByReCurHelper(root.right);
        res.add(root.val);
    }

    //二叉树后续遍历非递归实现
    public List<Integer> postOrderTraverse(TreeNode root){
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        if(root==null){
            return res;
        }

        while (!stack.isEmpty()||root!=null){
            //通过节点是否为空控制出栈还是入栈。
            if(root!=null){
                stack.push(root);
                root = root.left;
            }else{
                //节点为空，表示栈顶节点的左子树为空
                root = stack.peek();
                //通过上一次出栈的节点pre与栈顶节点的右孩子比较来判断是否打印过了右孩子,
                // 如果pre不是栈顶节点的右孩子，那么就开始从栈定节点的右孩子开始遍历。
                if(root.right!=null&&pre!=root.right){
                    root = root.right;

                    // 如果栈定节点的右孩子为空或者和上一次打印的节点相等，
                    // 说明栈顶节点的左孩子和右孩子都已经打印过，此时打印栈顶节点
                    //同时更新pre的值，root的值设为null，使得下次访问出栈节点的父节点。
                }else{
                    res.add(root.val);
                    pre = root;
                    stack.pop();
                    root = null;
                }

            }
        }
        return res;
    }

    public static void main(String[] args) {
        String data = "[1,2,3,null,null,4,5]";
        TreeNode root = TreeUtils.array2tree(data);
        PostOrderTraverse postOrderTraverse = new PostOrderTraverse();
//        List<Integer> res = preOrderTraverse.preOrderTraverseByReCur(root);
        List<Integer> res = postOrderTraverse.postOrderTraverse(root);

        System.out.println(res);
    }
}
