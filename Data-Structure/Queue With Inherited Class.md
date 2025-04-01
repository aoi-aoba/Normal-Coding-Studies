# 다른 클래스를 재사용한 큐의 구현
## 연결 리스트를 활용 - 상속(inherit)
연결 리스트를 활용한 큐  구현은 연결 리스트 구현과 비슷하다.   
그래서 이미 만들어놓은 클래스 `LinkedList<E>`를 상속받아 재사용하면 구현에 드는 노력이 줄어든다.   
상위 클래스의 생성자를 사용해도 되고, 미흡하면 생성자 오버로딩을 하면 된다.

### 그대로 사용할 경우
상위 클래스의 생성자를 그대로 사용하게 된다면 `super`을 사용하면 된다.

```java
public class InheritedQueue<E> extends LinkedList<E> implements QueueInterface<E> {
    public InheritedQueue() { super; }
    ...
}
```
`super`을 통해 상위 클래스의 동일한 메소드를 사용할 수 있다.   
만약 상위 클래스의 변수에 접근하려면 `super.variableName`와 같이 사용할 수 있다.

- 연결 리스트의 맨 앞을 `front`, 맨 뒤를 `tail`로 삼기로 한다.
- `enqueue` 메소드는 `append`를 활용하고, `front`는 `get(0)`을 호출하자.
- `dequeue` 메소드는 `remove(0)`을 호출하면 되고, `dequeueAll`은 `clear`을 사용하면 된다.


## ADT 리스트를 활용
ADT 리스트를 사용해서 스택을 구현해보자.   
필드는 `list` 하나를 가지고 큐로 사용할 리스트를 레퍼런스한다.
이 필드는 객체 내부의 메소드를 통해서만 접근할 수 있다.

### 활용 방법
생성자에서 ADT 리스트를 구현한 클래스 하나를 연결한다.   
연결 리스트 혹은 배열 리스트를 연결하자.   
리스트의 상위 인터페이스인 `ListInterface<E>` 관점으로 다뤄보자.

```java
public class ListQueue<E> implements QueueInterface<E> {
    private ListInterface<E> list;
    
    public ListQueue() {
        list = new LinkedList<E>(); // 또는 ArrayList<E>();
    }
}
```

- 리스트의 맨 앞을 큐의 `front`로 해도 되고, 맨 뒤를 `front`로 해도 된다. 리스트 객체의 해석 여부는 개인에게 달려 있다.
- 물론 가장 자연스러운 것은 맨 앞이 `front`, 맨 뒤가 `tail`이긴 하다.
- `enqueue`는 `append`, `front`는 `get(0)`, `dequeue`는 `get(0)`으로 가져오고 `remove(0)`을 해준다.
- `dequeueAll`은 여전히 `clear`을 사용하면 된다.

배열 리스트나 연결 리스트의 상속과 비슷하지만 리스트라는 더 상위 개념을 활용한다는 점에서 차이가 있다.

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/out/production/Data-Structure/stack 내에 있다.