package list;

import java.text.Bidi;

public class CircularDoublyLinkedList<E> implements ListInterface<E> {
    public BidirectionalNode<E> head;
    private int numItems;

    public CircularDoublyLinkedList() {
        numItems = 0;
        head = new BidirectionalNode<>(null); // 더미 헤드 노드
        head.next = head.prev = head;
    }

    public BidirectionalNode<E> getNode(int index) { // 첫 번째 원소는 0번 원소
        if (index >= -1 && index <= numItems - 1) {
            BidirectionalNode<E> currNode = head;
            if (index < numItems / 2)
                for (int i = 0; i <= index; i++)
                    currNode = currNode.next;
            else
                for (int i = numItems - 1; i >= index; i--)
                    currNode = currNode.prev;
            return currNode;
        } else return null;
    }

    public void add(int index, E x) {
        if (index >= 0 && index <= numItems) {
            BidirectionalNode<E> prevNode = getNode(index - 1);
            BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, x, prevNode.next);
            newNode.next.prev = newNode;
            prevNode.next = newNode;
            numItems++;
        } else System.out.println("Add Index Value Error");
    }

    public void append(E x) {
        BidirectionalNode<E> prevNode = head.prev;
        BidirectionalNode<E> newNode = new BidirectionalNode<>(prevNode, x, head);
        prevNode.next = newNode;
        head.prev = newNode;
        numItems++;
    }

    public E remove(int index) {
        if (index >= 0 && index <= numItems - 1) {
            BidirectionalNode<E> currNode = getNode(index);
            currNode.prev.next = currNode.next;
            currNode.next.prev = currNode.prev;
            numItems--;
            return currNode.item;
        } else {
            System.out.println("Cannot Remove Index " + index + " from list");
            return null;
        }
    }

    public boolean removeItem(E x) {
        BidirectionalNode currNode = head; // 더미 헤드
        for (int i = 0; i <= numItems - 1; i++) {
            currNode = currNode.next;
            if (((Comparable) (currNode.item)).compareTo(x) == 0) {
                currNode.prev.next = currNode.next;
                currNode.next.prev = currNode.prev;
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
        BidirectionalNode<E> currNode = head;
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
        head.next = head.prev = head;
    }

    public void print() {
        BidirectionalNode<E> currNode = head; // 더미 헤드
        for (int i = 0; i < numItems; i++) {
            currNode = currNode.next;
            System.out.print(currNode.item + " ");
        }
        System.out.println();
    }
}
