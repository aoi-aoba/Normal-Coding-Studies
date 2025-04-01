package queue;

public class ArrayQueue<E> implements QueueInterface<E> {

    private E[] queue;
    private int numItems, front, tail;
    private static final int DEFAULT_CAPACITY = 64;
    private final E ERROR = null;

    public ArrayQueue() {
        queue = (E[]) new Object[DEFAULT_CAPACITY];
        front = 0;
        tail = queue.length - 1;
        numItems = 0;
    }

    public ArrayQueue(int n) {
        queue = (E[]) new Object[n];
        front = 0;
        tail = n - 1;
        numItems = 0;
    }

    public void enqueue(E x) {
        if (isFull()) System.out.println(x + "can't be enqueued because queue is full.");
        else {
            tail = (tail + 1) % queue.length;
            queue[tail] = x;
            ++numItems;
        }
    }

    public E dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty so it can't be Dequeued.");
            return ERROR;
        } else {
            E queueFront = queue[front];
            front = (front + 1) % queue.length;
            --numItems;
            return queueFront;
        }
    }

    public E front() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return ERROR;
        } else return queue[front];
    }

    public boolean isFull() {
        return (numItems == queue.length);
    }

    public boolean isEmpty() {
        return (numItems == 0);
    }

    public void dequeueAll() {
        queue = (E[]) new Object[queue.length];
        front = 0;
        tail = queue.length - 1;
        numItems = 0;
    }
}
