package stack;

public class ArrayStack<E> implements StackInterface<E> {
    private E[] stack;
    private int topIndex;
    private static final int DEFAULT_CAPACITY = 64;
    private final E ERROR = null;

    public ArrayStack() {
        stack = (E[]) new Object[DEFAULT_CAPACITY];
        topIndex = -1;
    }

    public ArrayStack(int n) {
        stack = (E[]) new Object[n];
        topIndex = -1;
    }

    public void push(E newItem) {
        if (isFull()) {
            System.out.println(newItem + "can't be pushed because stack is full.");
        } else stack[++topIndex] = newItem;
    }

    public E pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty so it can't be popped.");
            return ERROR;
        } else return stack[topIndex--];
    }

    public E top() {
        if (isEmpty()) {
            System.out.println("Nothing found from empty stack.");
            return ERROR;
        } else return stack[topIndex];
    }

    public boolean isEmpty() {
        return (topIndex < 0);
    }

    public boolean isFull() {
        return (topIndex == stack.length - 1);
    }

    public void popAll() {
        stack = (E[]) new Object[stack.length];
        topIndex = -1;
    }

    public void print() {
        if (isEmpty())
            System.out.println("Nothing can be printed from empty stack.");
        else {
            for (int i = 0; i <= topIndex; i++) System.out.print(stack[i] + " ");
            System.out.println();
        }
    }
}