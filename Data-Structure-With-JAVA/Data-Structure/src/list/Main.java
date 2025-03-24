package list;

public class Main {
   // 테스트케이스 작성
    public static void main(String[] args) {
       IntegerArrayList integerArrayList = new IntegerArrayList();
       ArrayList<String> arrayList = new ArrayList<>();
       LinkedList<Integer> linkedList = new LinkedList<>();
       CircularLinkedList<Integer> circularLinkedList = new CircularLinkedList<>();
       CircularDoublyLinkedList<Integer> circularDoublyLinkedList = new CircularDoublyLinkedList<>();

       integerArrayList.add(0, 300);
       integerArrayList.add(0, 200);
       integerArrayList.add(0, 100);
       integerArrayList.print();
       integerArrayList.append(500);
       integerArrayList.append(600);
       integerArrayList.remove(3);
       integerArrayList.remove(1000);
       integerArrayList.print();
       integerArrayList.add(3, 250);
       integerArrayList.add(1, 50);
       integerArrayList.add(0, 10);
       integerArrayList.append(700);
       integerArrayList.remove(1);
       integerArrayList.removeItem(600);
       integerArrayList.print();
       integerArrayList.clear();
       integerArrayList.print();

       arrayList.add(0, "test");
       arrayList.add(0, "sample");
       arrayList.add(0, "String");
       arrayList.print();
       arrayList.append("test2");
       arrayList.print();
       arrayList.remove(3);
       arrayList.print();
       arrayList.add(3, "added-at-3");
       arrayList.add(1, "added-at-1");
       arrayList.add(0, "added-at-0");
       arrayList.print();
       arrayList.append("appended");
       arrayList.print();
       arrayList.remove(1);
       arrayList.print();
       arrayList.removeItem("added-at-3");
       arrayList.print();

       System.out.println(); // 구분을 위해
       linkedList.add(0, 300);
       linkedList.add(0, 300);
       linkedList.add(0, 300);
       linkedList.print();
       linkedList.append(500);
       linkedList.append(600);
       linkedList.print();
       linkedList.remove(3);
       linkedList.print();
       linkedList.add(3, 250);
       linkedList.add(1, 50);
       linkedList.add(0, 10);
       linkedList.print();
       linkedList.append(700);
       linkedList.print();
       linkedList.remove(1);
       linkedList.removeItem(600);
       linkedList.print();

       System.out.println(); // 구분을 위해
       circularLinkedList.add(0, 300);
       circularLinkedList.add(0, 200);
       circularLinkedList.add(0, 100);
       circularLinkedList.print();
       circularLinkedList.append(500);
       circularLinkedList.append(600);
       circularLinkedList.print();
       circularLinkedList.remove(3);
       circularLinkedList.print();
       circularLinkedList.add(3, 250);
       circularLinkedList.add(1, 50);
       circularLinkedList.add(0, 10);
       circularLinkedList.print();
       circularLinkedList.append(700);
       circularLinkedList.print();
       circularLinkedList.remove(1);
       circularLinkedList.removeItem(600);
       circularLinkedList.print();

       System.out.println(); // 구분을 위해
       circularDoublyLinkedList.add(0, 300);
       circularDoublyLinkedList.add(0, 200);
       circularDoublyLinkedList.add(0, 100);
       circularDoublyLinkedList.print();
       circularDoublyLinkedList.append(500);
       circularDoublyLinkedList.append(600);
       circularDoublyLinkedList.print();
       circularDoublyLinkedList.remove(3);
       circularDoublyLinkedList.print();
       circularDoublyLinkedList.add(3, 250);
       circularDoublyLinkedList.add(1, 50);
       circularDoublyLinkedList.add(0, 10);
       circularDoublyLinkedList.print();
       circularDoublyLinkedList.append(700);
       circularDoublyLinkedList.print();
       circularDoublyLinkedList.remove(1);
       circularDoublyLinkedList.removeItem(600);
       circularDoublyLinkedList.print();
    }
}