package list;

public interface ListInterface<E> {
    public void add(int index, E x);
    public void append(E x);
    public E remove(int index);
    public boolean removeItem(E x);
    public E get(int index);
    public void set(int index, E x);
    public int indexOf(E x);
    public int len();
    public boolean isEmpty();
    public void clear();
    public void print();
}

/*
    제네릭 버전으로 구현하기 위한 인터페이스
    - 배열 리스트인 ArrayList (정수 한정 배열 리스트에는 사용하지 않음)
    - 연결 리스트인 LinkedList
*/