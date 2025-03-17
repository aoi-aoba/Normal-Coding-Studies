package list;

public class Main {
    public static void main(String[] args) {
       IntegerArrayList integerArrayList = new IntegerArrayList();
       ArrayList<String> arrayList = new ArrayList<>();

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
    }
}