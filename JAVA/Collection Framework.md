# 컬렉션 프레임워크 - 리스트
컬렉션 프레임워크는 기본적으로 다수의 객체를 보관할 **배열 이상의 무언가**를 만들기 위해 사용된다. 배열은 기본적으로 쉬운 생성과 삭제를 보장하지만 저장 객체 수가 **생성 당시에 결정**되기 때문에 저장할 수 있는 수를 변경하기 위해 새 배열 객체를 만들어야 한다는 단점과, 이를 보완하기 위해 넉넉한 메모리 공간을 잡고 할당하게 되면 **메모리 낭비**&ZeroWidthSpace;가 과하다. 반대로 또 다시 이 낭비를 줄이기 위해 **빠듯하게 메모리를 확보**&ZeroWidthSpace;하게 되면 꽉 찰 경우 내용 이동이 번거롭고 **시간적인 낭비가 발생**&ZeroWidthSpace;한다.   
이런 이유로 우리는 자료구조를 바탕으로 객체를 효율적으로 추가, 삭제, 검사를 할 수 있게 `java.util` 패키지의 `Collection Framework`를 사용하여 관련 인터페이스/컬렉션/클래스를 사용하게 된다.
> 기존 배열의 과한 메모리 낭비 및 빠듯한 메모리 확보로 인한 원소 이동의 시간 낭비 문제
-> 이를 해결하기 위해 나타난 자료구조 바탕의 컬렉션/클래스/인터페이스가 **컬렉션 프레임워크**

![](https://velog.velcdn.com/images/aoi-aoba/post/f24f3aee-25f9-44ac-a04d-7f5cf774a686/image.png)

여기에서는 **리스트**&ZeroWidthSpace;에 해당하는 세 가지
- ArrayList
- Vector
- LinkedList

에 대하여 다룰 것이다. 자세한 자료구조 설명은 `Normal-Coding-Studies` 레포지토리의 `Data-Structure/Arraylist-with-ADT.md`를 참고.

## 리스트 컬렉션
리스트 컬렉션은 객체를 일렬로 나열한 구조를 의미한다.   
리스트  컬렉션은 객체를 **인덱스로 관리**&ZeroWidthSpace;하기 때문에 객체 저장 시 자동으로 인덱스가 부여되고, 이 인덱스를 통해 **객체의 검색, 삭제 기능을 지원**&ZeroWidthSpace;한다. 객체를 직접 저장하는 것이 아니라 객체의 **번지를 참조**&ZeroWidthSpace;(Call-by-Referance)하는 형태임을 이해해야 한다. 동일 객체의 저장도 가능한데, 이 경우 **동일한 번지가 저장**&ZeroWidthSpace;된다. `null` 역시 저장 가능하며, 이 경우 **객체를 참조하지 않는 방식**&ZeroWidthSpace;으로 저장한다.

## 리스트 인터페이스
자바 컬렉션 프레임워크의 멤버로서 참조되는 인터페이스
- Type Parameter : `E` (Generics 활용한 Element 표현)
- SuperInterfaces : `Collection<E>`, `Iterable<E>`
- Implementing Classes (인터페이스 참조하는 클래스들) :
	`AbstractList`, `AbstractSequentialList`, `ArrayList`, `AttributeList`, `CopyOnWriteArrayList`, `LinkedList`, `RoleList`, `RoleUnresolvedList`, `Stack`, `Vector`

```JAVA
public interface List<E> extends Collection<E>
```

## 리스트 인터페이스 공통 메소드 리스트
여기서 다루는 메소드는 잘 사용되는 메소드를 위주로 한다.   
제외된 메소드는 맨 아래에 적혀있는 출처에서 읽어보자.

### int size()
- Return type : `int`
	- 리스트에 존재하는 모든 요소의 개수를 반환
- 특징
	- 리스트에 존재하는 모든 요소의 개수를 `int`형으로 반환한다.
   	- 만약 리스트에 존재하는 요소의 개수가 `int` 범위를 초과한다면, `return value`는 `Integer.MAX_VALUE`로 고정된다.
    
### void clear()
- Throws Exceptions for:
	- `UnsupportedOperationException` : 해당 리스트에서 이 메소드가 지원되지 않는 경우
- 특징
	- 리스트의 모든 요소를 지운다. (조건적)
   	- 이 리스트는 이후 빈 상태가 된다. (반환값 없이)

### boolean isEmpty()
- Return type : `boolean`
	- 요소가 리스트 내에 존재하지 않는다면 `true`를 반환
- 특징
	- 요소가 리스트 내에 존재하는지 여부를 확인한다.
    
### E get(int index)
- Parameter : `index` - return할 요소의 인덱스 값
- Return type : `E`(Element)
	- 리스트의 해당 위치에 있는 요소
- Throws Exceptions for:
	- `IndexOutOfBoundsException` : 인덱스가 범위 내를 벗어난 경우
- 특징
	- 리스트 내에서 해당 위치에 존재하는 값을 반환한다.
    
### E set(int index, E element)
- Parameter : `E` - 어떤 값을 바꾸고 싶은 목적 값, `index` - 목적 값 형태로 바꾸고 싶은 이전값의 위치
- Return type : `E`(element)
	- 해당 인덱스에 이전에 위치했던 요소
- Throws Exceptions for:
	- `UnsupportedOperationException` : 해당 리스트에서 이 메소드가 지원되지 않는 경우
	- `ClassCastException` : 특정 element를 Class에서 리스트에 추가되지 못하게 막는 경우
	- `NullPointerException` : 특정 element가 `null`값을 가지며 해당 리스트가 `null` 값을 저장하려 하지 않는 경우
	- `IllegalArgumentException` : 특정 element의 특징 자체로부터 리스트에 추가될 수 없게 작용하는 경우
    - `IndexOutOfBoundsException` : 인덱스가 범위 내를 벗어난 경우
- 특징
	- 리스트에서 지정한 위치에 있는 값을 지정된 값으로 바꿔준다. (조건적)

### boolean isEmpty()
- Return type : `boolean`
	- 요소가 리스트 내에 존재하지 않는다면 `true`를 반환
- 특징
	- 요소가 리스트 내에 존재하는지 여부를 확인한다.
    
### boolean contains(Object o)
- Parameter : `o` - 리스트 내에서 존재 여부를 확인하고 싶은 요소
- Return type : `boolean`
	- 해당 요소가 존재한다면 `true` 반환, 없으면 `false` 반환
- Throws Exceptions for:
	- `ClassCastException` : 리스트 내에서 해당 element의 type가 존재할 수 없는 경우 (조건적)
	- `NullPointerException` : 리스트 내에서`null` element를 할당받을 수 없는 경우에 `null`을 검사하려 하면 발생 (조건적)
- 특징
	- `(o==null) ? (e==null) : o.equals(e)`를 실행한다.
   	- 요소 `o`를 하나라도 가지고 있다면 `true`가 반환된다.
    
### Iterator `<E>` Iterator
- Return type : `Iterator<E>`
	- 리스트에 적절하게 사용될 수 있는 Iterator(반복자) 반환

> 반복자에 대한 설명은 Normal-Coding-Studies/JAVA 문서를 참고하자.

### boolean add(E e) / void add(int index, E element)
- Parameter : `E` - 리스트에서 받는 Element, `index` - 리스트에 Element를 넣을 위치
- Return type : `boolean` 혹은 `void`
	- 요청한 사항이 제대로 작동해서 Collections가 변경한 경우 `true` 반환
	- `index`를 활용한 경우에는 return value가 없음
 	- `Collection.add(E)`와 동일하게 작용함
- Throws Exceptions for:
	- `UnsupportedOperationException` : 해당 리스트에서 이 메소드가 지원되지 않는 경우
	- `ClassCastException` : 특정 element를 Class에서 리스트에 추가되지 못하게 막는 경우
	- `NullPointerException` : 특정 element가 `null`값을 가지며 해당 리스트가 `null` 값을 저장하려 하지 않는 경우
	- `IllegalArgumentException` : 특정 element의 특징 자체로부터 리스트에 추가될 수 없게 작용하는 경우
- 특징
	- `boolean return type` : 주어진 객체를 맨 끝에 추가한다. (조건적 명령어)
   	- `void return type` : 주어진 객체를 해당 `index`에 추가한다.
	- 리스트 컬렉션에서 `null`의 추가를 막거나 `elements type`에 대한 제한이 걸려 있을 수 있다.
	- 리스트 클래스에서는 문서에 추가 가능한 요소에 대해 명확한 제한 사항을 명시해야 한다.

### boolean remove(Object o) / E remove(int index)
- Parameter : `index` - Element가 리스트에 있는 위치
- Return type : `boolean` 혹은 `E`
	- 삭제하기 전까지 위치했던 해당 `index`의 element `E`
- Throws Exceptions for:
	- `UnsupportedOperationException` : 해당 리스트에서 이 메소드가 지원되지 않는 경우
	- `IndexOutOfBoundsException` : 인덱스가 범위 내를 벗어난 경우
- 특징
	- `boolean return type` : 주어진 객체를 맨 끝에 추가한다. (조건적 명령어)
   	- `E return type` : 지정된 위치에서 요소를 제거하고 다음 요소를 왼쪽으로 이동한다.

## 배열 리스트(ArrayList)
리스트 인터페이스의 구현 클래스로, 객체를 관리한다는 점에서는 일반 배열과 크게 다르지 않다. 다만, 중요한 차이점으로 **배열과 달리 저장 용량(capacity)이 자동적으로 늘어난다**&ZeroWidthSpace;는 특징이 있다. 사용 중에 자동으로 저장 용량이 늘어나는 것이다.
- 기본 생성자 기준, 배열 리스트의 초기 용량(capacity)은 **10개 객체를 저장할 수** 있게 주어진다.
- 객체 수와 관계없이 초기 용량을 늘리고 싶다면 생성자(constructor)의 매개값(parameter)로 용량의 크기를 부여하면 된다. (constructor overloading  방식 활용)
- 저장할 객체 타입은 type parameter로 표기하고 기본 생성자를 호출하되, 타입 파라미터에는 **객체**&ZeroWidthSpace;만이 저장될 수 있다.
- 객체 저장 시 **Object 타입으로 변환되어 저장**&ZeroWidthSpace;되기 때문에 실행 성능에 좋지 못한 영향을 미친다.
- **JAVA 5부터 type parameter를 통하여 객체 타입을 지정함**&ZeroWidthSpace;에 따라 불필요한 타입 변환을 없앴다.
 
![](https://velog.velcdn.com/images/aoi-aoba/post/727b1050-956d-40f5-9007-34e5ffafd449/image.png)

치명적인 단점이 있다면, ArrayList는 **특정 객체를 삭제하면 바로 뒤 인덱스부터 맨 뒤 인덱스까지 모두 앞으로 당겨지는** 과정이 이루어진다. 반대로, 객체를 삽입하면 반대로 **모두 뒤로 미뤄지는** 과정이 이루어진다. 그말인즉, 삽입과 삭제 과정에서 일반적으로 $$O(N)$$의 시간 복잡도가 발생하기 때문에 빈번한 삽입과 삭제 과정에서는 사용하기 부적합하다.

![](https://velog.velcdn.com/images/aoi-aoba/post/4a398f3a-2378-401a-aea3-6e8d3f9a55ca/image.png)
> `List<T> list = Arrays.asList(T[]... a)` 메소드를 활용하여 변수를 리스트로 변경할 수 있다.   
T 타입 파라미터에 맞게 매개값을 순차적으로 입력하거나 `T[]` 배열을 매개값으로 줄 수 있다. 

> - 장점 : 인덱스 검색, 맨 마지막에 객체를 추가하는 경우 성능을 발휘함
- 단점 : 객체 삭제와 삽입이 빈번한 경우 시간 복잡도 면에서 저하된 성능을 보임, 자바 5에서 개선되긴 하였으나 모든 객체를 Object 형태로 변환해서 저장하고 찾아올 때 다시 변환하는 불필요한 과정을 거침

## 벡터(Vector)
벡터는 배열 리스트와 다르게 **동기화된(synchronized)** 메소드로 구성되어 있기 때문에 멀티 스레드가 이 메소드를 **동시에 실행할 수 없고** 하나의 스레드가 실행을 완료해야만 다른 스레드를 실행할 수 있다. 즉, **멀티 스레드** 환경에서 안전하게 객체 추가, 삭제가 가능하게 해 주기 때문에 **스레드가 안전(Thread Safe)하다** 라고 말할 수 있다.
![](https://velog.velcdn.com/images/aoi-aoba/post/fd85a438-532b-4fda-b546-bc02ebb25d2f/image.png)

## 연결 리스트(LinkedList)
연결 리스트는 배열 리스트가 내부 배열에 객체를 인덱스로 관리하는 것과 달리, **각 객체의 인접 참조 링크** 방식을 활용하여 **체인**&ZeroWidthSpace;처럼 관리한다. 특정 인덱스의 객체를 삭제해도 **앞뒤 링크만 바뀌고** 나머지 링크는 변경되지 않는다. 삽입 역시 마찬가지이다. 그 결과 **빈번한 객체 삽입과 수정, 제거**&ZeroWidthSpace;가 이루어지는 작업에서 굉장히 유리하다.
![](https://velog.velcdn.com/images/aoi-aoba/post/54987bfc-f3d6-4c08-8f32-118a1ca2d665/image.png)

## 정리
![](https://velog.velcdn.com/images/aoi-aoba/post/895fd33c-dfc1-4f2e-baa1-0b73e1543e65/image.png)

- ArrayList와 LinkedList를 적재적소에 바르게 사용할 수 있어야만이 공간 복잡도와 시간 복잡도 면에서 유리한 코드를 짤 수 있다.
- ArrayList와 LinkedList가 사용할 수 있는 각각의 여러 메소드를 추가로 알아두는 것도 더 편하게 사용할 수 있는 방법이 될 것이다.

## 출처
- 리스트 인터페이스(docs.oracle)
https://docs.oracle.com/javase/8/docs/api/java/util/List.html
- LinkedList Class(docs.oracle)
https://docs.oracle.com/javase/8/docs/api/java/util/LinkedList.html
- ArrayList Class(docs.oracle)
https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
- 건복치 님의 티스토리 블로그 - Collection Framework1 - List(ArrayList / Vector / LinkedList)
https://minhamina.tistory.com/14
- 책 <이것이 자바다> 본책 내용 중 (722p~)
