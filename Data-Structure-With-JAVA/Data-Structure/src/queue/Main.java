package queue;

public class Main {
    public static void main(String[] args) {
        ArrayQueue<String> arrayQueue = new ArrayQueue<>();
        arrayQueue.enqueue("Joe");
        arrayQueue.enqueue("John");
        arrayQueue.enqueue("Colin");
        arrayQueue.dequeue();
        System.out.println(arrayQueue.front());
        System.out.println(arrayQueue.isEmpty());
        System.out.println(arrayQueue.isFull());
    }
}

// debugger 기능 활용하여 확인해볼 것