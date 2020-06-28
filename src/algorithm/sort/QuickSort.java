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
        //(lo,i]中的所有元素都小于等于v，[j,hi]中的所有元素都大于等于v。开始的时候集合中没有元素。

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

        //循环停止时list[j]是最后一个小于v的元素，list[i]是第一个大于v的元素，
        // 所以将j与lo位置的元素互换，使得[lo,j]位置的元素小于等于v。
        swap(list,lo,j);
        return j;
    }

        private static void swap(Comparable[] list,int i,int j){
            Comparable tmp = list[i];
            list[i] = list[j];
            list[j] = tmp;
        }

    //3路快排
    public static void quickSort_3(Comparable[] list ,int lo,int hi){
        //递归停止条件
        if(lo>=hi)
            return;

        Comparable v = list[lo];
        //[lo,lt-1]或者[lo,lt)内的数据都比v小
        int lt = lo;
        //[gt-1,hi]或者(gt,hi]内的数据都比v大
        int gt = hi;

        //[lt,i)内的数据和v相等
        int i = lt+1;

        //[i,gt]内的数据未知
        //循环停止条件：i>gt
        while(i<=gt){
            int cmp = list[i].compareTo(v);
            //如果位置i的元素比v小，那么把位置i的元素与lt位置的元素互换，同时i加1，lt加1。
            if(cmp<0){
                swap(list,lt++,i++);
            }else if(cmp>0){
                swap(list,gt--,i);
            }else{
                i++;
            }
        }

        quickSort_3(list,lo,lt-1);
        quickSort_3(list,gt+1,hi);

    }


    public static void main(String[] args) {
        Integer[] list = {2,5,8,1,7,4,4,9,3,10};
        quickSort_3(list,0,list.length-1);
        System.out.println(Arrays.toString(list));
    }

}


