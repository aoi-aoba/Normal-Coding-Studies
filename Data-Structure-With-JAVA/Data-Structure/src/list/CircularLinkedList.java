package list;

public class CircularLinkedList<E> implements ListInterface<E> {
    private Node<E> tail;
    private int numItems;

    public CircularLinkedList() {
        numItems = 0;
        tail = new Node(-1);
        tail.next = tail;
    }

    public Node<E> getNode(int index) {
        if (index >= -1 && index <= numItems) {
            Node<E> currNode = tail.next; // 첫 노드는 맨 마지막 노드의 다음 노드인 더미 헤드임
            for (int i = 0; i <= index; i++)
                currNode = currNode.next;
            return currNode;
        } else {
            System.out.println("Cannot Get Node Index " + index + " from list"); // 예외 처리
            return null;
        }
    }

    public void add(int index, E x) {
        if (index >= 0 && index <= numItems) {
            Node<E> prevNode = getNode(index - 1);
            Node<E> newNode = new Node<>(x, prevNode.next);
            prevNode.next = newNode;
            if (index == numItems) tail = newNode;
            // 만약 index값이 append랑 같은 위치인 경우에는 tail을 바꿔줘야 함
            numItems++;
        } else System.out.println("Add Index Value Error");
    }

    public void append(E x) {
        Node<E> prevNode = tail;
        Node<E> newNode = new Node<>(x, tail.next);
        prevNode.next = newNode;
        tail = newNode;
        numItems++;
    }

    public E remove(int index) {
        if (index >= 0 && index <= numItems - 1) {
            Node<E> prevNode = getNode(index - 1);
            E removeItem = prevNode.next.item;
            prevNode.next = prevNode.next.next;
            if (index == numItems) tail = prevNode;
            // 마지막 노드를 지운 경우
            numItems--;
            return removeItem;
        } else {
            System.out.println("Cannot Remove Index " + index + " from list");
            return null;
        }
    }

    public boolean removeItem(E x) {
        Node<E> currNode = tail.next; // 더미 헤드
        Node<E> prevNode;
        for (int i = 0; i < numItems; i++) {
            prevNode = currNode;
            currNode = prevNode.next;
            if (((Comparable) (currNode.item)).compareTo(x) == 0) {
                prevNode.next = currNode.next;
                numItems--;
                return true;
            }
        }
        System.out.println("Cannot Remove " + x + "from list");
        return false; // 그런 노드 없다
    }

    public E get(int index) {
        if (index >= 0 && index <= numItems - 1)
            return getNode(index).item;
        else return null;
    }

    public void set(int index, E x) {
        if (index >= 0 && index <= numItems - 1)
            getNode(index).item = x;
        else System.out.println("Set Index Value Error"); // 예외 처리
    }


    public int indexOf(E x) {
        Node<E> currNode = tail.next;
        for (int i = 0; i <= numItems - 1; i++) {
            currNode = currNode.next;
            if (((Comparable) (currNode.item)).compareTo(x) == 0) return i;
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
        numItems = 0;
        tail = new Node(-1);
        tail.next = tail;
    }

    public void print() {
        Node<E> currNode = tail.next; // 더미 헤드
        for (int i = 0; i < numItems; i++) {
            currNode = currNode.next;
            System.out.print(currNode.item + " ");
        }
        System.out.println();
    }
}
