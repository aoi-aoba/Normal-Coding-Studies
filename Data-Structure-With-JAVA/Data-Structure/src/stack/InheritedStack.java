package stack;
import list.LinkedList;

public class InheritedStack<E> extends LinkedList<E> implements StackInterface<E> {

    public InheritedStack() {
        super();
    }
    public void push(E newItem) {
        super.add(0, newItem);
    }

    public E pop() {
        if(!isEmpty()) {
            return super.remove(0);
        } else return null;
    }

    public E top() {
        return super.get(0);
    }

    public boolean isEmpty() {
        return super.isEmpty();
    }

    public void popAll() {
        super.clear();
    }
}
