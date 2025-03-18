package list;

public class Node<E> {
    public E item;
    public Node<E> next;

    public Node(E newItem) {
        item = newItem;
        next = null;
    }

    public Node(E newItem, Node<E> nextNode) {
        item = newItem;
        next = nextNode;
    } // 생성자 오버로딩을 통해 nextNode 지정해서 생성할 수도 있음
}

/*
    노드 객체의 사용법
    1> Node<Integer> t2 = new Node<>(y)
        y라는 Integer 값을 할당한 t2 Node / 다음 Node 미설정으로 null
    2> Node<Integer> t1 = new Node<>(x)
        x라는 Integer 값을 할당한 t1 Node / 다음 Node 미설정으로 null
    3> t1.next = t2;
        Node<E>로 정의된 t1.next에 대하여 Node t2를 제공하였으므로 t1 >> t2로 링크됨
    4> Node<Integer> t3 = new Node<>(z, t1)
        z라는 Integer 값을 할당한 t3 Node의 다음 Node는 t1, 즉 t3 >> t1 >> t2로 링크됨
 */

/*
    제네릭 클래스 대신에 Object 클래스를 사용한다면?

    public class Node {
        public Object item;
        public Node next;
        public Node(Object newItem) {
            item = newItem;
            next = null;
        }
        public Node(Object newItem, Node nextNode) {
            item = newItem;
            next = nextNode;
        }
    }

    - 모든 클래스에 대한 상위 클래스인 Object
    - 대신 Object 자리에 모두 같은 타입만 와야 한다는 의미는 표현할 수 없음
    - 관용성은 있으나 모호함(흐릿함)이 있는 이유로 TypeCastingException 발생할 수 있음
    - 비일관성 문제도 발생할 수 있으므로 이 점에서 애매하지 않은 표현법이 필요함
    - 제네릭 타입을 사용하여 이런 모호함을 없애주는 것!
 */