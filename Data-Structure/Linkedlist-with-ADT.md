# 연결 리스트(Linked List)
## 배열 리스트(Arraylist)의 단점과 연결 리스트
리스트는 **줄 세워진 데이터** 혹은 **나열된 데이터**를 의미한다.   
하지만 이전의 배열 리스트(Array List)를 생각해보면 원소의 양을 알 수 없기 때문에 **충분한 공간 확보가 필수적**&ZeroWidthSpace;이었다.   
그러다 보니 **공간 낭비가 불가피**&ZeroWidthSpace;하며 이를 막기 위해서는 빠듯한 배열 크기 할당으로 인해 원소 옮김이 잦아진다.   

하지만 **연결 리스트**는 배열의 공간 낭비를 줄일 수 있다. 그 특징은 아래와 같다:
- 원소가 추가될 때마다 공간을 할당받아 추가하는 **동적 할당 방식**
- 원소를 저장하는 `item` 필드와 다음 노드를 가리키는 `next` 필드를 활용한 `node`가 하나의 단위
  - `item`은 정수나 실수 같은 기초 타입 혹은 클래스의 객체
  - `next`는 다음 노드를 가리키는 링크로서, JAVA의 **Reference** (C의 경우 Pointer)에 해당
  - `head`는 첫 번째의 노드를 가리키는 레퍼런스

연결 리스트가 접근하는 방식으로서, `head`를 통해 첫 번째 노드로 접근하여 리스트가 링크로 연결되어 있음을 이용한다.   
첫 번째 노드를 밟고 다음 링크(`next` 필드 활용)로 넘어가서 다음 노드들에 차례대로 접근해야 한다.   
그렇기 때문에 노드를 직접 저장한다기보다는, 연결 리스트 객체는 **첫 노드 객체에 접근할 레퍼런스**&ZeroWidthSpace;만 가지면 된다.    
이것이 `head` 필드의 역할이다.

## 배열의 객체 구조
배열 리스트가 하나의 객체(Class)로서 정의된다고 했을 때, 구조는 다음과 같다:
- **필드** : 첫 번째 노드에 대한 레퍼런스 `head`, 저장 중인 값의 총 개수 `numItems`
  - 배열 리스트와 마찬가지로 사용자 프로그램이 필드에 직접 접근하는 것을 막음 (private)
- **메소드** : `add(), append(), remove(), removeItem(), get(), set(), indexOf(), len(), isEmpty(), clear()`

필드의 `head`와 함께 노드 객체를 활용하는 것을 제외하면 결과적으로 배열 리스트와 크게 다른 느낌은 없다.

## 연결 리스트의 작업 : (1) 원소 삽입
연결 리스트에서의 노드 삽입은 삽입 노드의 바로 앞 노드를 가리키는 레퍼런스 `prevNode`가 필요하다.   
만약 노드가 10 - 17 - 35 - 40 - 55와 같이 연결되고 더 연결된 것이 없다고 가정해보자.   
그러면 17과 35 사이에 새로운 노드를 추가하려 한다고 할 때, 여기에 25를 삽입하려면 어떻게 해야 할까?   
간단하다. 레퍼런스 `prevNode`가 원소 17을 가리키게 준비하고, 새 노드를 추가하면 된다.

```
newNode.item <- x                   // x는 삽입할 원소이므로 새 노드의 값으로 저장함
newNode.next <- prevNode.next       // 기존의 이전 노드가 가진 next 값을 가져와서 중간에 들어가니까 next로 지정
prevNode.next <- newNode            // 기존의 이전 노드는 새로 들어갈 중간 값을 next로 가져야 함
numItems++                          // 노드가 추가되었으므로 numItems 값은 1 증가
```

만약, 위에서 주어진 노드 중 10의 앞에 넣는다고 하면 어떻게 해야 할까?   
이 경우 **직전 노드가 존재하지 않는 경우**&ZeroWidthSpace;에 해당하므로 이전의 방법은 사용할 수 없다.   
그렇기 때문에 `head` 값을 수정해서 새 노드를 가리키게 만든다.
그리고 새 노드가 이전의 첫 노드를 `next`로 가리키게 만들면 된다.

```
newNode.item <- x
newNode.next <- head    // 새 노드의 다음 값을 기존의 head가 가리키던 첫 노드로 지정
head <- newNode         // head는 새 노드가 첫 노드가 되어야 하므로 새 노드를 가리키게 함
numItems++
```

### 원소 삽입의 ADT 구조
```
add(parameter):
    if (k = 0) // 원소 인덱스 k에 대하여 첫 번째인 경우
        newNode.item <- x
        newNode.next <- head
        head <- newNode
        numItems++
    else // 원소가 새로 위치하게 되는 곳이 다른 원소들의 중간인 경우
        newNode.item <- x
        newNode.next <- prevNode.next
        prevNode.next <- newNode
        numItems++
```

여기에서 조금 더 확장해보자.   
원소를 삽입할 때 굳이 우리가 경우의 수를 나누어야 하는가?   
이런 번거로운 과정을 거치지 않을 방법이 있는데, 바로 **더미 헤드 노드** 배치 방법이다.

### 더미 헤드 노드 배치
Dummy head node(더미 헤드 노드)는 원소가 없는 **가짜 노드**&ZeroWidthSpace;이다.   
몇 가지의 장점을 가지고 있기 때문에 사용한다.
- 헤드가 더미 헤드 노드를 가리키고, 더미 헤드 노드가 첫 노드를 가리키는 방식으로 사용한다.
- 맨 처음에 노드를 새로 추가하려고 한다면 **더미 헤드 노드가 직전 노드가 되므로** `prevNode.next`에 값 할당이 가능하다.
- 빈 연결 리스트의 경우 `head`가 `null`값이 아니라 더미 헤드를 가리키기 때문에 `null`로 인한 오류를 막을 수 있다.
더미 헤드 노드를 배치할 경우, 두 가지 경우의 수로 나눌 필요 없이 아래와 같이 사용이 가능하다.

```
add(parameter):
    newNode.item <- x
    newNode.next <- prevNode.next   // 어차피 더미 헤드 노드가 앞에 있으므로 prevNode가 사용 가능하다
    prevNode.next <- newNode        // 마찬가지로 prevNode(더미 헤드 노드)는 새 노드를 가리키게 된다
    numItems++
```

## 연결 리스트의 작업 : (2) 원소 제거
원소를 제거하기 위해서는 삭제할 노드의 직전 노드를 알아야 한다.
- `prevNode`가 직전 노드를 가리키도록 준비한다.
- 직전 노드가 바로 삭제 노드 다음 노드로 건너뛰어 링크하게 만든다.

```
prevNode.next <- prevNode.next.next     // 직전 노드가 다음으로 가리키던 노드는 삭제할 노드이므로 그 다음 노드를 가리키게 지정
numItems--
```

직전 노드 다음에 노드가 존재하지 않으면 `prevNode.next.next`가 작동하지 않아 에러가 날 수 있다.   
하지만, 삭제할 노드를 미리 찾아놓고 하는 작업이므로 그럴 일은 없다.   
리스트에 맨 뒤에 있는 노드를 삭제할 때도 위의 코드를 사용할 수 있는데, `prevNode.next`가 삭제할 마지막 위치 노드이다.   
그러면 `prevNode.next.next`는 삭제할 마지막 노드가 `null`을 가리키고 있었을 것이므로 위의 코드가 잘 작동하게 된다.   
여전히 문제는 연결 리스트의 맨 앞에 있는 노드 삭제에 있다. 직전 노드가 없기 때문이다.

```
head <- head.next
numItems--
```

그래서 위와 같은 방법으로 `head`가 가리키는 것을 다음 노드인 삭제할 노드의 그 다음 노드로 지정하면 된다.   
JAVA의 Garbage Collect가 이루어지므로 삭제될 객체는 자동으로 사라질 것이다.


### 원소 제거의 ADT 구조
```
remove(parameter):
    if (k = 0) // 원소 인덱스 k에 대하여 첫 번째 원소를 삭제하는 경우
      head <- head.next // head가 가리키는 노드(제거할 첫 원소)를 그 다음 원소로
      numItems--
    else // 원소가 맨 앞에 있지 않은 경우
      prevNode.next <- prevNode.next.next // 이전 노드가 가리키는 다음 노드를 그 다음 노드로
      numItems--
```

여기서도 삽입에서의 논점을 그대로 가져와서, 왜 'k=0'의 경우를 우리가 나누어야 하는가?
이런 번거로운 과정을 거치지 않을 방법은 여기서도 **더미 헤드 노드** 배치를 진행하는 방법이다.

```
remove(parameter):
    prevNode.next <- prevNode.next.next // 첫 노드의 경우에도 이전 노드가 더미 헤드 노드로 존재하므로 올바르게 적용됨
    numItems--
```


## 연결 리스트의 작업 : (3) k번째 노드 가져오기
다른 함수에서도 많이 사용하게 되는 **k번째 노드 찾기** 작업을 진행하는 `getNode(k)`를 구현해보자.   
- 노드의 인덱스는 `0`부터 `numItems-1`까지 존재하게 된다.
- 더미 헤드를 찾는 경우도 지원하기 위해 `-1`의 인덱스를 지원하자.

```
getNode(k):
    if (k >= -1 && k <= numItems - 1)
        currNode <- head  // current Node(현재 노드)를 더미 헤드로 지정하자
        for <- 0 to k     // 0부터 k까지의 인덱스를 돌게 되면 (그니까 찾는게 더미 헤드가 아니라면)
            currNode <- currNode.next // 계속 현재 노드의 다음 노드를 타고 넘어가서 k번째를 찾아내자
        return currNode
    else return null      // 만약 인덱스를 제대로 배치하지 않았다면 오류 처리를 위해 null을 보내자
```

## 연결 리스트의 작업 : (4) get 함수, set 함수
이 두 함수의 경우 위에서 만든 `getNode(k)`가 큰 도움이 된다.
```
get(k):
    return getNode(k).item // 노드를 받아와서 item을 그냥 값으로 보내주면 됨

set(k, x):
    if (k >= 0 && k <= numItems - 1) // 범위 내에 있다면
        getNode(k).item <- x // 노드를 받아서 item을 수정해주고
    else return null // 아니면 오류 처리를 위해 null return
```

## 연결 리스트의 작업 : (5) indexOf 함수
사실상 `getNode(k)`를 구현하는 것과 비슷한 방식을 차용하면 된다.
```
indexOf(x):
    currNode <- head // 더미 헤드 노드에서 출발
    for i <- 0 to numItems - 1
        currNode <- currNode.next 
        if (currNode.item = x) return i
    return NOT_FOUND // 오류 처리, 여기서는 더미 헤드 노드도 오류에 해당함 (값을 가지지 않으므로!)
```

## 연결 리스트의 작업 : (6) 나머지 함수들
```
len():
    return numItems

isEmpty():
    if (numItems = 0) return true
    else return false

clear():
    newNode.next <- null  // head 변수가 null 값을 갖는 대신에 더미 헤드 노드를 가리키게 하면 된다
    head <- newNode
    numitems <- 0
```

## (JAVA) 객체 지향 규칙을 느슨하게 풀은 형태의 Node 구현
객체 지향 규칙을 엄격하게 적용하게 된다면 사실상 노드 구조에 있어서 item과 next를 외부에서 접근할 수 있게 하는 필드 역할은 좋지 않다.   
객체의 필드는 모두 외부 접근이 **꼭 필요한 정도로만 제한되는** 것이 객체 지향 언어의 철학에 부합하기 때문이다.   
이걸 엄격하게 충실히 지키려고 한다면 노드에는 가장 교과서적인 요구를 지키는 다음 현상들이 발생한다:
- `item`, `node`는 모두 `private` 한정사를 활용해야 하며 그 결과 `setter`와 `getter`를 사용해야 한다.
- 노드에 원소를 저장하기 위해서 `setItem(Integer newItem)`을 통해 `item = newItem`을 실행해준다.
- 노드의 원소를 가져오기 위해서 `getItem()`을 통해 `return item`을 실행한다.
- 노드의 링크 값을 할당하기 위해서 `setNext(Node nextNode)`를 통해 `next = nextNode`를 실행해준다.
- 노드의 링크 값을 가져오기 위해서 `getNext()`를 통해 `return next`를 실행한다.

메소드가 `private` 접근으로 한정된 외부 한정적 접근 필드의 값을 받아오거나 할당하기 위한 단순한 역할을 진행하게 된다.   
클래스 내부를 보호하는 측면에서는 이 점이 바람직하지만, 단순한 구조와 작업에 대해 굳이 벽을 치는 것이 다소 어색하고 너무 자주 이루어져야 한다.   
그래서 이 부분에서는 조금 단순하게 진행해서, 모든 한정사를 `public` 형태로 바꾸고 필드에 외부에서도 진입 가능하게 한다.

가령 아래와 같이 직접 getter와 setter를 사용해야 했다면,
```JAVA
Node a = new Node();
a.setItem(10);
a.getNext();
```
아래와 같은 방식으로 조금 더 빠르게 사용하기 위해서이다.
```JAVA
Node a = new Node();
a.item = 10;
Node nextNode = a.next;
```

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/out/production/Data-Structure/list 내에 있다.