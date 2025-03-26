# 다른 클래스를 재사용한 스택의 구현
## 연결 리스트를 활용 - 상속(inherit)
연결 리스트를 활용한 스택 구현은 연결 리스트 구현과 비슷하다.   
그래서 이미 만들어놓은 클래스 `LinkedList<E>`를 상속받아 재사용하면 구현에 드는 노력이 줄어든다.   
상위 클래스의 생성자를 사용해도 되고, 미흡하면 생성자 오버로딩을 하면 된다.

### 그대로 사용할 경우
상위 클래스의 생성자를 그대로 사용하게 된다면 `super`을 사용하면 된다.

```java
public class InheritedStack<E> extends LinkedList<E> implements StackInterface<E> {
    public InheritedStack() { super; }
    ...
}
```
`super`을 통해 상위 클래스의 동일한 메소드를 사용할 수 있다.   
만약 상위 클래스의 변수에 접근하려면 `super.variableName`와 같이 사용할 수 있다.


## ADT 리스트를 활용
ADT 리스트를 사용해서 스택을 구현해보자.   
필드는 `list` 하나를 가지고 스택으로 사용할 리스트를 레퍼런스한다.
이 필드는 객체 내부의 메소드를 통해서만 접근할 수 있다.

### 활용 방법
생성자에서 ADT 리스트를 구현한 클래스 하나를 연결한다.   
연결 리스트 혹은 배열 리스트를 연결하자.   
리스트의 상위 인터페이스인 `ListInterface<E>` 관점으로 다뤄보자.

```java
public class ListStack<E> implements StackInterface<E> {
    private ListInterface<E> list;
    
    public ListStack() {
        list = new LinkedList<E>();
    }
}
```

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/out/production/Data-Structure/stack 내에 있다.