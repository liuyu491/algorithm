package algorithm.sort;

import java.util.Arrays;

/*
* 选择排序*/
public class SelectSort {
    public static void selectSort(Comparable[] list){
        for(int i=0;i<list.length;i++){

            int index_min = i;
            for(int j=i+1;j<list.length;j++){
                if(list[index_min].compareTo(list[j])>0){
                    index_min = j;
                }
            }
            swap(list,i,index_min);
        }
    }

    private static void swap(Comparable[] list,int i,int j){
        Comparable tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    public static void main(String[] args) {
        Integer[] list = {2,5,8,1,7};
        selectSort(list);
        System.out.println(Arrays.toString(list));
    }
}
