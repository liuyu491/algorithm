package datastructure;
/*
* 自定义链表对象
* */


public class LinkedList<E> {
    class Node{
        private E e;
        Node next;
        private Node(E e){
               this.e = e;
        }
    }

    private Node  dummyHead = new Node(null);

    private int size;



    LinkedList(){

    }

    public void addFirst(E e){
        Node node = new Node(e);
        node.next = dummyHead.next;
        dummyHead.next = node;
        size++;
    }

    public void add(int index,E e){
        if(index<0||index>size){
            throw new IllegalArgumentException("索引超出范围");
        }
        //添加节点：首先找到要添加位置节点的前一个节点
        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        Node node = new Node(e);
        node.next = prev.next;
        prev.next = node;
        size++;
    }

    public boolean isEmpty(){
        return size==0;
    }
    //删除节点，先找到要删除那个节点的前一个节点
    public E delete(int index){
        if(index<0||index>=size){
            throw new IllegalArgumentException("索引超出范围");
        }
        //添加节点：首先找到要添加位置节点的前一个节点
        Node prev = dummyHead;
        for(int i=0;i<index;i++){
            prev = prev.next;
        }
        Node node = prev.next;
        prev.next = node.next;
        node.next = null;
        size--;
        return node.e;
    }

    @Override
    public String toString() {
        Node cur = dummyHead.next;
        StringBuilder sb = new StringBuilder();
        while(cur!=null){
            sb.append(cur.e);
            sb.append("->");
            cur = cur.next;
        }
        sb.append("null");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        for(int i=0;i<20;i++){
            if(i<10){
                linkedList.add(i,i);
            }else{
                linkedList.delete(0);
            }
            System.out.println(linkedList);
        }
    }
}
