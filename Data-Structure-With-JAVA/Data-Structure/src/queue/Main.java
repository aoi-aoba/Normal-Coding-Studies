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
        System.out.println();

        LinkedQueue<String> linkedQueue = new LinkedQueue<>();
        linkedQueue.enqueue("Joe");
        linkedQueue.enqueue("John");
        linkedQueue.enqueue("Colin");
        linkedQueue.dequeue();
        System.out.println(linkedQueue.front());
        System.out.println(linkedQueue.isEmpty());
        System.out.println();

        ListQueue<String> listQueue = new ListQueue<>();
        listQueue.enqueue("Joe");
        listQueue.enqueue("John");
        listQueue.enqueue("Colin");
        listQueue.dequeue();
        System.out.println(listQueue.front());
        System.out.println(listQueue.isEmpty());
        System.out.println();
    }
}

// debugger 기능 활용하여 확인해볼 것