package datastructure.queue;

import java.util.Arrays;

/*
* 循环队列的实现
* 循环队列可以看作一个环形队列，数组的最后一个元素的下一个元素是数组的第一个元素，
*下一个元素的位置应该由：(front+1)%数组长度取得。
*
*
* */
public class CircularQueue<E> implements Queue<E> {
    private E[] elements;
    private int size;
    //指向队列的头部元素
    int front;
    //指向队列尾部元素的后一个空位置，
    int tail;
    /*队列为空的条件：
    *     front==tail
    * */

    /*
    * 队列为满的条件：(tail+1)%elements.length==front，这样浪费了一个元素位置。
    * 所以队列的容量为数组长度减去1。
    * */
    public CircularQueue(){
        elements = (E[])new Object[6];
    }

    public CircularQueue(int capacity){
        if(capacity<0){
            throw new IllegalArgumentException("容量值不能小于0");
        }
        //数组长度比队列容量多1。
        elements = (E[])new Object[capacity+1];
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getCapacity() {
        return elements.length-1;
    }

    @Override
    public void enQueue(E e) {
        //队列已满，进扩容操作
        if((tail+1)%elements.length==front){
            resize(2*getCapacity()+1);
        }
        elements[tail] = e;
        tail = (tail+1)%elements.length;
        size++;
    }

    @Override
    public E deQueue() {
        if(front==tail){
            throw new IllegalArgumentException("队列为空");
        }
        E ret = elements[front];
        front = (front+1)%elements.length;
        size--;
        //不用size<=getCapacity/4,
        // 是为了防止出现队列中只有一个元素的时候，元素出队，必然会缩减容量
        if(size==getCapacity()/4)
            resize(getCapacity()/2+1);
        return ret;
    }

    @Override
    public E peek() {
        return null;
    }

    private void resize(int newCapacity){
        if(newCapacity==1){
            newCapacity = 5;
        }

        E[] newElements = (E[])new Object[newCapacity];
        /*
        * 新数组和原来数组各个元素索引对应关系：
        * newElements[i] = elements[(i+front)%elements.length],新数组front为0
        *
        * */
        for(int i=0;i<size;i++){
            newElements[i] = elements[(i+front)%elements.length];
        }
        elements = newElements;
        front = 0;
        tail = size;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("size: %d capacity: %d ", size,getCapacity()));
        sb.append("front [");
        for(int i=0;i<size;i ++){
            sb.append(elements[(i+front)%elements.length]);
            if(i!=size-1){
                sb.append(", ");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> que = new CircularQueue<>();
        for(int i=0;i<25;i++){
            if(i<10)
                que.enQueue(i);
            else if(i<15)
                System.out.println(que.deQueue());
            else
                que.enQueue(i);

            System.out.println(Arrays.toString(((CircularQueue<Integer>) que).elements));
        }

    }
}
