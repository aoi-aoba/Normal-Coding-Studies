package list;

public class LinkedList<E> implements ListInterface<E> {
    private Node<E> head;
    private int numItems;

    public LinkedList() {
        numItems = 0;
        head = new Node<>(null, null);
        // dummy head node
    }

    public void add(int index, E item) {
        if (index >= 0 && index <= numItems) {
            Node<E> prevNode = getNode(index - 1);
            prevNode.next = new Node<>(item, prevNode.next);
            // inline variable (new 생성자로 대입 연산자 안에서 생성)
            numItems++;
        } else System.out.println("Add Index Value Error"); // 예외 처리
    }

    public void append(E item) {
        Node<E> prevNode = head;
        while (prevNode.next != null) {
            prevNode = prevNode.next; // 헤드에서 마지막 노드까지 이동
        }
        prevNode.next = new Node<>(item, prevNode.next);
        numItems++;
    }

    public E remove(int index) {
        if (index >= 0 && index <= numItems - 1) {
            Node<E> prevNode = getNode(index - 1);
            Node<E> currNode = prevNode.next; // return value를 위해
            prevNode.next = currNode.next;
            // currNode 선언 없이 prevNode.next.next 활용해도 됨
            numItems--;
            return currNode.item; // 제대로 실행됐다면 삭제한 item을 return
        } else {
            System.out.println("Cannot Remove Index " + index + " from list"); // 예외 처리
            return null;
        }
    }

    public boolean removeItem(E item) {
        Node<E> prevNode, currNode = head;
        for (int i = 0; i <= numItems - 1; i++) {
            prevNode = currNode;
            currNode = currNode.next;
            if (((Comparable)(currNode.item)).compareTo(item) == 0) {
                // compareTo 사용을 위해 컴파일러가 인식할 수 있게 Comparable 강제 할당
                prevNode.next = currNode.next;
                numItems--;
                return true;
            }
        }
        System.out.println("Cannot Remove " + item + " from list"); // 예외 처리
        return false;
    }

    public Node<E> getNode(int index) {
        if (index >= -1 && index <= numItems - 1) {
            // -1부터 시작해야 더미 헤드 노드를 -1로 출력해줄 수 있음!
            Node<E> currNode = head;
            for(int i=0; i<index; i++) {
                currNode = currNode.next;
            }
            return currNode;
        } else {
            System.out.println("Cannot Get Node Index " + index + " from list"); // 예외 처리
            return null;
        }
    }

    public E get(int index) {
        if (index >= 0 && index <= numItems - 1) {
            return getNode(index).item;
        } else {
            System.out.println("Cannot Get Node Index " + index + " from list"); // 예외 처리
            return null;
        }
    }

    public void set(int index, E item) {
        if (index >= 0 && index <= numItems - 1) {
            getNode(index).item = item;
        } else {
            System.out.println("Set Index Value Error"); // 예외 처리
        }
    }

    public int indexOf(E item) {
        Node<E> currNode = head;
        int i;
        for (i = 0; i < numItems; i++) {
            currNode = currNode.next;
            if (((Comparable)(currNode.item)).compareTo(item) == 0)
                // compareTo 사용을 위해 컴파일러가 인식할 수 있게 Comparable 강제 할당
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
        numItems = 0;
        head = new Node<>(null, null);
        // head를 inline에서 아예 처리해서 새 더미 헤드 노드를 지정
    }

    public void print() {
        Node<E> currNode = head;
        for (int i = 0; i < numItems; i++) {
            currNode = currNode.next;
            System.out.print(currNode.item + " ");
        }
        System.out.println();
    }
}
