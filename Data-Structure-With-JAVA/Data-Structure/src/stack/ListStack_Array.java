package stack;
import list.ArrayList;
import list.ListInterface;

public class ListStack_Array<E> implements StackInterface<E> {
    private ListInterface<E> list;

    public ListStack_Array() {
        list = new ArrayList<E>();
    }

    public void push(E newItem) {
        list.add(0, newItem);
    }

    public E pop() {
        return list.remove(0);
    }

    public E top() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void popAll() {
        list.clear();
    }
}
