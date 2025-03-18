package list;

public class ArrayList<E> implements ListInterface<E> {
    private E[] item;
    private int numItems;
    private static final int DEFAULT_CAPACITY = 64;
    // 모든 ArrayList 객체에 대하여 하나의 static 상수로 생성
    // constructor 작성에 크기를 지정하지 않고 객체를 생성한다면 기본 크기를 이 값으로 지정

    public ArrayList() { // 기본 생성자
        item = (E[]) new Object[DEFAULT_CAPACITY];
        // compiler warning 발생하지만 이는 object 변환 간 미확인 casting 발생하기 때문이므로 상관없음
        numItems = 0;
    }

    public ArrayList(int n) { // constructor overloading
        item = (E[]) new Object[n];
        // compiler warning 발생하지만 이는 object 변환 간 미확인 casting 발생하기 때문이므로 상관없음
        numItems = 0;
    }

    public void add(int index, E x) {
        if (numItems >= item.length || index < 0 || index > numItems) {
            System.out.println("Add Index Value Error");
        } else {
            for (int i = numItems - 1; i >= index; i--)
                item[i + 1] = item[i];
            item[index] = x;
            numItems++;
        }
    }

    public void append(E x) {
        if (numItems >= item.length) {
            System.out.println("Append Error"); // 방어적 프로그래밍
        } else item[numItems++] = x;
        // 후위 증감 연산자를 활용하여 한 줄로 줄일 수 있음
    }

    public E remove(int index) {
        if (isEmpty() || index < 0 || index > numItems - 1) {
            System.out.println("Cannot Remove Index " + index + " from list");
            return null;
        } else {
            E removeResult = item[index]; // 삭제하게 된 값을 리턴
            for (int i = index; i <= numItems - 2; i++)
                item[i] = item[i+1];
            numItems--;
            return removeResult;
        }
    }

    public boolean removeItem(E x) {
        int k = 0;
        while (k < numItems && ((Comparable)item[k]).compareTo(x) != 0)
            // 객체는 단순히 비교 불가능하므로 Comparable 인터페이스를 E에 부여한 뒤에 compareTo 메소드를 활용
            // 비교 가능한 클래스인지 아닌지 컴파일러는 E에 대해 알 수 없는 상태이므로 type-casting 미리 진행
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

    public E get(int index) {
        if (index >= 0 && index <= numItems - 1)
            return item[index];
        else return null;
    }

    public void set(int index, E x) {
        if (index >= 0 && index <= numItems - 1)
            item[index] = x;
        else System.out.println("Set Index Value Error"); // 예외 처리
    }

    public int indexOf(E x) {
        int i = 0;
        for (i = 0; i < numItems; i++) {
            if(((Comparable)item[i]).compareTo(x) == 0)
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
        item = (E[]) new Object[item.length];
        numItems = 0;
    }

    public void print() {
        for (int i = 0; i < numItems; i++) {
            System.out.print(item[i] + " ");
        }
        System.out.println();
    }
}
