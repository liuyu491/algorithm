package datastructure.stack;

/*
* 为了防止时间复杂度的震荡，扩容的阈值，和缩容的阈值不一样
*
* */
public class StackImpl<E> implements Stack<E> {
    private E[] elements;
    //索引在[0,size-1)区间有数据
    private int size;

    public StackImpl(){
        //栈的默认容量为10
        elements = (E[]) new Object[10];
    }

    public StackImpl(int capacity){
        if(capacity<0&&capacity>Integer.MAX_VALUE){
            throw new IllegalArgumentException();
        }
        elements = (E[])new Object[capacity];
    }

    @Override
    public void push(E e) {
        //数组已满，进行扩容
        if(size==elements.length){
            extendSize();
        }
        elements[size] = e;
        size++;
    }

    @Override
    public E pop() {
        if(size<=0){
            throw new IllegalArgumentException();
        }
        //如果栈中元素的数量小于等于容量的1/4，那么将容量缩小为原来的1半
        if(size<=elements.length/4){
            contractSize();
        }
        size--;
        E element = elements[size];

        return element;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public int getCapacity() {
        return elements.length;
    }

    //扩容
    private void extendSize(){
        //每次扩容将数组容量扩大为原来的2倍。
        int newCapacity = elements.length*2;

        E[] newElements = (E[])new Object[newCapacity];
        for(int i=0;i<size;i++){
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    //收缩
    private void contractSize(){
        int newCapacity = elements.length/2;
        E[] newElements = (E[]) new Object[newCapacity];
        for(int i=0;i<size;i++){
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("size: %d, capacity: %d", size,elements.length));
        sb.append("[");
        for(int i=0;i<size;i++){
            sb.append(elements[i]);
            //风格符和元素分开打印，最后一个元素不加分隔符。
            if(i!=size-1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new StackImpl<>(5);
        for(int i=0;i<20;i++){
            if(i<10)
                stack.push(i);
            else
                System.out.println(stack.pop());

            System.out.println(stack.toString());
        }
    }
}
