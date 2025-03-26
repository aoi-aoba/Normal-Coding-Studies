package stack;

public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> arrayStack = new ArrayStack<>(10);
        arrayStack.push(300);
        arrayStack.print();
        arrayStack.push(200);
        arrayStack.print();
        arrayStack.push(100);
        arrayStack.print();
        arrayStack.pop();
        arrayStack.print();

        LinkedStack<String> linkedStack = new LinkedStack<>();
        linkedStack.push("test 1");
        linkedStack.push("test 2");
        linkedStack.push("test 3");
        linkedStack.pop();
    }
}
