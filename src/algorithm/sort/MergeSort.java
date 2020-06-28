package algorithm.sort;

import java.util.Arrays;

public class MergeSort {
    public static void mergesort(Comparable[] list){
        mergesort(list,0,list.length-1);
    }

    private static void mergesort(Comparable[] list,int lo,int hi){
        if(lo>=hi){
            return;
        }
        int mid = (lo+hi)/2;
        mergesort(list,lo,mid);
        mergesort(list,mid+1,hi);
        merge(list,lo,mid,hi);
    }

    private static void merge(Comparable[] list,int lo,int mid,int hi){
        Comparable[] tmp = new Comparable[hi-lo+1];
        for(int i=0;i<=hi-lo;i++){
            tmp[i] = list[i+lo];
        }
        int i = 0;
        int j = mid-lo+1;
        int mid_tmp = mid-lo;

        for(int index=lo;index<=hi;index++){
            //左半部分走完
            if(i>mid_tmp){
                list[index] = tmp[j++];
                //右半部分走往
            }else if(j>hi-lo){
                list[index] = tmp[i++];
                //把小的值复制到list指定位置中
            }else if(tmp[i].compareTo(tmp[j])<0){
                list[index] = tmp[i++];
            }else {
                list[index] = tmp[j++];
            }
        }


    }




    public static void main(String[] args) {
        Integer[] list = {2,5,8,1,7,4,4,9,3,10};
        mergesort(list);
        System.out.println(Arrays.toString(list));
    }
}
