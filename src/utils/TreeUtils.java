package utils;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {
    public static TreeNode array2tree(String data){
        TreeNode node = null;
        if(data.length()<3){
            return null;
        }
        String data_temp = data.substring(1,data.length()-1);

        String[] data_array = data_temp.split(",");
        Queue<TreeNode> que = new LinkedList<>();
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(data_array[index]));
        que.add(root);
        while(!que.isEmpty()){
            TreeNode node_root = que.poll();
            index++;

            if(index<data_array.length&&!data_array[index].equals("null")){
                TreeNode node_l = new TreeNode(Integer.parseInt(data_array[index]));
                node_root.left = node_l;
                que.add(node_l);
            }
            else{
                node_root.left=null;
            }
            index++;
            if(index<data_array.length&&!data_array[index].equals("null")){
                TreeNode node_r = new TreeNode(Integer.parseInt(data_array[index]));
                que.add(node_r);
                node_root.right = node_r;
            }
            else{
                node_root.right=null;
            }

        }
        return root;
    }

    public static String serialize(TreeNode root) {
        if(root==null)
            return "[]";
        Queue<TreeNode> que = new LinkedList<>();

        que.add(root);
        String res = "["+root.getVal();
        while(!que.isEmpty()){
            int size = que.size();
            for(int i=0;i<size;i++){
                TreeNode node = que.poll();
                if(node.getLeft()!=null ){
                    que.add(node.getLeft());
                    res+=","+node.getLeft().getVal();
                }

                else
                    res+=",null";

                if(node.getRight()!=null ){
                    que.add(node.getRight());
                    res+=","+node.getRight().getVal();
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
        String data = "[1,2,3,null,null,4,5]";
        TreeNode root = array2tree(data);
        System.out.println(serialize(root));
    }
}
