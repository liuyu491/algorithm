package datastructure.queue;

public interface Queue<E> {
    boolean isEmpty();
    int getSize();
    int getCapacity();
    void enQueue(E e);
       E deQueue();
    E peek();
}
