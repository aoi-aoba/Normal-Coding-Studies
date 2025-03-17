package list;

public class IntegerArrayList implements IntegerListInterface {
    private Integer[] item;
    private int numItems;
    private static final int DEFAULT_CAPACITY = 64;
    // 모든 ArrayList 객체에 대하여 하나의 static 상수로 생성
    // constructor 작성에 크기를 지정하지 않고 객체를 생성한다면 기본 크기를 이 값으로 지정

    public IntegerArrayList() { // 기본 생성자
        item = new Integer[DEFAULT_CAPACITY];
        numItems = 0;
    }

    public IntegerArrayList(int n) { // constructor overloading
        item = new Integer[n];
        numItems = 0;
    }

    public void add(int index, Integer x) {
        if (numItems >= item.length || index < 0 || index > numItems) {
            System.out.println("Add Index Value Error");
        } else {
            for (int i = numItems - 1; i >= index; i--)
                item[i + 1] = item[i];
            item[index] = x;
            numItems++;
        }
    }

    public void append(Integer x) {
        if (numItems >= item.length) {
            System.out.println("Append Error"); // 방어적 프로그래밍
        } else item[numItems++] = x;
        // 후위 증감 연산자를 활용하여 한 줄로 줄일 수 있음
    }

    public Integer remove(int index) {
        if (isEmpty() || index < 0 || index > numItems - 1) {
            System.out.println("Cannot Remove Index " + index + " from list");
            return null;
        } else {
            Integer removeResult = item[index]; // 삭제하게 된 값을 리턴
            for (int i = index; i <= numItems - 2; i++)
                item[i] = item[i+1];
            numItems--;
            System.out.println("removed value " + removeResult + " from list");
            return removeResult;
        }
    }

    public boolean removeItem(Integer x) {
        int k = 0;
        while (k < numItems && item[k].compareTo(x) != 0)
            k++;
        if (k == numItems) {
            System.out.println("Cannot Remove " + x + "from list");
            return false;
        } else {
            for (int i = k; i <= numItems - 2; i++)
                item[i] = item[i + 1];
            numItems--;
            return true;
        }
    }

    public Integer get(int index) {
        if (index >= 0 && index <= numItems - 1)
            return item[index];
        else
            return null;
    }

    public void set(int index, Integer x) {
        if (index >= 0 && index <= numItems - 1)
            item[index] = x;
        else System.out.println("Set Index Value Error"); // 예외 처리
    }

    public int indexOf(Integer x) {
        int i = 0;
        for (i = 0; i < numItems; i++) {
            if(item[i].compareTo(x) == 0)
                return i;
        }
        System.out.println("IndexOf Finding Value Error"); // 예외 처리
        return Integer.MIN_VALUE; // Int 값의 최저치를 리턴하여 예외 상황으로 처리
    }

    public int len() {
        return numItems;
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public void clear() {
        item = new Integer[item.length];
        numItems = 0;
    }

    public void print() {
        for (int i = 0; i < numItems; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
    }
}
