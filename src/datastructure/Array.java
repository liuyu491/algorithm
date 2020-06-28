package datastructure;

public interface Array<E> {
    void add(E e);
    int getSize();
    int getCapacity();
    boolean isEmpty();
    void add(int index,E e);
    void get(int index);
    void set(int index,E e);
    void contains(E e);

}
