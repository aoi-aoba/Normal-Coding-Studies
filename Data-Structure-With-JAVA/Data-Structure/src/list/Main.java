package list;

public class Main {
    public static void main(String[] args) {
       IntegerArrayList integerArrayList = new IntegerArrayList();
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
    }
}