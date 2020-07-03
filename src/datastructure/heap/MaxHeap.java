package datastructure.heap;

import java.util.Arrays;
import java.util.Random;

/*
* 堆是完全二叉树，因此堆可以使用数组实现
* 最大堆
* */
public class MaxHeap<E extends Comparable> {
    private E[] elements = null;
    //size是数组最后一个元素的索引
    int size;
    public MaxHeap(int capacity){
        elements = (E[])new Comparable[capacity+1];
    }

    //获取堆中元素的数量
    public int getSize(){
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    //取出最大元素，并删除
    public E extractMax(){
        if(isEmpty()){
            throw new IllegalArgumentException("堆为空");
        }
        E e = elements[1];
        elements[1] = elements[size];
        siftDown(1);
        size--;
        return e;
    }

    //返回最大元素
    public E findMax(){
        if(isEmpty()){
            throw new IllegalArgumentException("堆为空");
        }
        E e = elements[1];
        return e;
    }

    //添加元素
    public void add(E e){
        elements[++size] = e;
        siftUp(size);
    }

    //把数组转换为堆
    public MaxHeap Heapfiy(E[] array){
        if(array==null||array.length==0){
            throw new IllegalArgumentException("数组不能为空");
        }
        elements = (E[])new Comparable[array.length+1];
        for(int i=1;i<elements.length;i++){
            elements[i] = array[i-1];
        }
        size=array.length;
        //获取完全二叉树最后一个非叶子节点的索引
        int index = parent(elements.length-1);
        //从最后一个非叶子节点开始，依次向前对每一个节点执行一次下沉操作
        for(int i=index;i>=1;i--){
            siftDown(i);
        }
        return this;
    }

    //取出堆的最大元素，后再加入一个新元素。
    public E replace(E e){
        if(isEmpty()){
           throw new IllegalArgumentException("堆为空");
        }
        E top = elements[1];
        elements[1] = e;
        siftDown(1);
        return top;
    }

    //上浮，把元素上浮到正确的位置
    private void siftUp(int index){

        while(index>1&&elements[index].compareTo(elements[parent(index)])>0){
            swap(elements,index,parent(index));
            index = parent(index);
        }

    }

    //下沉，把节点下沉到正确的位置

    private void siftDown(int index){
        //倒数第2层最后一个节点时停止下沉
        while (leftChild(index)<=size){
            int i = leftChild(index);
            int j = rightChild(index);
            int max_indx = i;
            if(j<=size){
                if(elements[i].compareTo(elements[j])<0){
                    max_indx = j;
                }
            }

            //当前节点的值比左右孩子的值都大，停止下沉。
            if(elements[max_indx].compareTo(elements[index])>0){
                swap(elements,index,max_indx);
                index = max_indx;
            }else{
                break;
            }
        }
    }

    //获取指定索引元素的左孩子元素的索引
    public int leftChild(int index){
        //数组从1开始存数据
        return index*2;
    }

    //获取指定索引元素的右孩子元素的索引
    public int rightChild(int index){
        return index*2+1;
    }

    public int parent(int index){
        return index/2;
    }

    private static void swap(Comparable[] list,int i,int j){
        Comparable tmp = list[i];
        list[i] = list[j];
        list[j] = tmp;
    }

    public static void main(String[] args) {
        Random random = new Random();
        MaxHeap<Integer> maxHeap = new MaxHeap<>(300000);
        Integer[] a = new Integer[300000];
        for (int i = 0; i <300000 ; i++) {
           // maxHeap.add(random.nextInt(1000000));
            a[i] = random.nextInt(1000000);
        }

        MaxHeap<Integer> maxHeap1 = maxHeap.Heapfiy(a);
        Integer pre = Integer.MIN_VALUE;
        for (int i = 0; i <300000 ; i++) {
            Integer cur = maxHeap1.extractMax();
            System.out.println(String.format("cur= %d, pre= %d", cur,pre));
            if(pre<cur&&pre!=Integer.MIN_VALUE){
                System.out.println("出现错误");
                break;
            }
            pre = cur;
        }
    }

}
