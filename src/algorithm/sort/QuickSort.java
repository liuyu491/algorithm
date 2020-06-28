package algorithm.sort;

import datastructure.Array;

import java.util.Arrays;

public class QuickSort {


    public static void quickSort(Comparable[] list){
            quickSort(list,0,list.length-1);
    }

    private static void quickSort(Comparable[] list,int lo,int hi){
        /*递归停止的条件*/
        if(lo>=hi){
            return;
        }
        int index = partition(list,lo,hi);
        quickSort(list,lo,index-1);
        quickSort(list,index+1,hi);
    }

    //双轴快排
    private static int partition(Comparable[] list, int lo,int hi){

        //选取第一个位置的元素作为分隔元素
        Comparable v = list[lo];
        int i = lo;
        int j = hi+1;
        //[lo,i]中的所有元素都小于等于v，[j,hi]中的所有元素都大于等于v。开始的时候集合中没有元素。

        while(true){
            while(list[++i].compareTo(v)<0){
                //防止越界
                if(i==hi){
                    break;
                }
            }

            while(list[--j].compareTo(v)>0) {

            }
               if(i>=j) {
                   break;
               }
            swap(list,i,j);
        }
        swap(list,lo,j);
        return j;
    }

        private static void swap(Comparable[] list,int i,int j){
            Comparable tmp = list[i];
            list[i] = list[j];
            list[j] = tmp;
        }

    public static void main(String[] args) {
        Integer[] list = {2,5,8,1,7,4,4,9,3,10};
        quickSort(list);
        System.out.println(Arrays.toString(list));
    }
    }


