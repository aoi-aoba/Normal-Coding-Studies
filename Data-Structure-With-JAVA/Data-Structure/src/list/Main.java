package list;

public class Main {
   // 테스트케이스 작성
    public static void main(String[] args) {
       IntegerArrayList integerArrayList = new IntegerArrayList();
       ArrayList<String> arrayList = new ArrayList<>();
       LinkedList<Integer> linkedList = new LinkedList<>();

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
    }
}