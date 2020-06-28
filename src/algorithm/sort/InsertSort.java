package algorithm.sort;

import java.util.Arrays;

public class InsertSort {

    public static void insertSort(Comparable[] list){
        //第一层循环顺序抽牌
        for(int i = 1;i<list.length;i++){
            //第二次循环，在抽到的牌比前一张牌小的时候进行插入操作，直到插入的牌比前一张牌大或者到第一张牌
            for(int j = i;j>0&&list[j].compareTo(list[j-1])<0;j--){
                swap(list,j,j-1);
            }
        }
    }

    private static void swap(Comparable[] list,int i,int j){
        Comparable tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    public static void main(String[] args) {
//        Integer[] list = {2,5,8,1,7,4,4,9,3,10};
        Integer[] list = {9,5};

        insertSort(list);
        System.out.println(Arrays.toString(list));
    }
}
