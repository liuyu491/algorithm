package datastructure.stack;

public interface Stack<E> {
    void push(E e);
    E pop();
    int getSize();
    boolean isEmpty();
    int getCapacity();

}
