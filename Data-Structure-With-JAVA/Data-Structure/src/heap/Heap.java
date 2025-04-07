package heap;

public class Heap<E extends Comparable> implements PQInterface<E> {
    private E[] A;
    private int numItems;

    public Heap(int arraySize) {
        A = (E[]) new Comparable[arraySize];
        numItems = 0;
    }

    public Heap(E[] B, int numElements) {
        A = B; // 배열 레퍼런스를 복사
        numItems = numElements;
    }

    // 원소 삽입 (재귀 알고리즘 버전)
    public void insert(E newItem) throws PQException {
        if (numItems < A.length) {
            A[numItems] = newItem;
            percolateUp(numItems);
            numItems++;
        } else throw new PQException("HeapErr: Insert()-Overflow!");
    }

    // 스며올리기
    private void percolateUp(int i) {
        int parent = (i - 1) / 2;
        if (i > 0 && A[i].compareTo(A[parent]) > 0) {
            E tmp = A[i];
            A[i] = A[parent];
            A[parent] = tmp;
            percolateUp(parent);
        }
    }

    public E deleteMax() throws PQException {
        if (!isEmpty()) {
            E max = A[0];
            A[0] = A[numItems - 1];
            numItems--;
            percolateDown(0);
            return max;
        } else throw new PQException("HeapErr: DeleteMax()-Underflow");
    }

    // 스며내리기
    private void percolateDown(int i) {
        int child = 2 * i + 1;
        int rightChild = 2 * i + 2;
        if (child <= numItems - 1) {
            if (rightChild <= numItems - 1 && A[child].compareTo(A[rightChild]) < 0)
                child = rightChild;
            if (A[i].compareTo(A[child]) < 0) {
                E tmp = A[i];
                A[i] = A[child];
                A[child] = tmp;
                percolateDown(child);
            }
        }
    }

    // 힙 만들기
    public void buildHeap() {
        if (numItems >= 2)
            for (int i = (numItems - 2) / 2; i >= 0; i--)
                percolateDown(i);
    }

    public E max() throws Exception {
        if (!isEmpty()) return A[0];
        else throw new PQException("HeapErr: Max()-Empty!");
    }

    public boolean isEmpty() {
        return numItems == 0;
    }

    public void clear() {
        A = (E[]) new Object[A.length];
        numItems = 0;
    }
}