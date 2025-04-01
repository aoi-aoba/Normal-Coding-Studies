package queue;

import list.ArrayList;
import list.ListInterface;

public class ListQueue<E> implements QueueInterface<E>{
    private final ListInterface<E> list;

    public ListQueue() {
        list = new ArrayList<>(); // 또는 LinkedList
    }

    public void enqueue(E newItem) {
        list.append(newItem);
    }

    public E dequeue() {
        return list.remove(0);
    }

    public E front() {
        return list.get(0);
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void dequeueAll() {
        list.clear();
    }
}
