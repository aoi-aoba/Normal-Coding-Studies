# 연결 리스트의 확장 : 양방향 연결 리스트(Doubly Linked List)
## 단방향 연결 리스트의 불편함
연결 리스트는 일반적으로 단방향으로 진행된다.   
어떤 노드를 한 번 지나면 따로 저장하지 않는 한 반대 방향으로는 돌아갈 수 없다.   
그래서 이를 해소하기 위해 **각 노드가 앞뒤로 링크**&ZeroWidthSpace;하게 하는데, 이 방법이 바로 양방향 연결 리스트이다.

## 양방향 원형 연결 리스트
양방향 원형 연결 리스트는 환 구조를 이루면서 각 노드가 앞뒤로 링크하는 형태이다.   
각 노드가 **다음 노드 및 직전 노드의 정보** 모두를 가지고 있기 때문에 앞뒤로 자유롭게 이동이 가능하다.   
또한, 양방향 연결 리스트의 성질도 가지고 있다.   
여기서는 `head` 레퍼런스 변수를 활용하여, 마지막 원소는 next로 더미 헤드 노드를 가리키고, 더미 헤더 노드의 prev가 마지막 노드를 가리킨다.   

## 양방향 원형 연결 리스트 : 양방향 노드
일반 노드가 아니라 양방향에 지원되는 새로운 노드를 만들어야 한다.   
양방향 노드는 다음과 같이 구성하면 된다.
- `prev` 노드와 `next` 노드를 참조하기 위해 필드에 저장하는 이들 역시 양방향 노드 타입이다.
- `item`은 기존과 같은 형태이다.
- 생성자는 기본 생성자, 새 `item`만 제공한 경우, 이전 노드/`item`/다음 노드를 모두 제공한 경우로 나눈다.
- 제공하지 않은 정보를 모두 `null` 처리한다.

## 양방향 원형 연결 리스트 작업 변동 : 초기화
`head`에 처음 지정되는 것이 양방향 노드이고, 더미 노드도 null 값을 가지는 양방향 노드여야 한다.   
그리고 `head`의 `next`와 `prev`가 모두 `head`인 상태로 초기화해야 한다.

## 양방향 원형 연결 리스트 작업 변동 : 원소 삽입
```
BidirectionalNode<E> newNode = new BidirectionalNode<>(newItem, prevNode, prevNode.next);
newNode.next.prev = newNode;
prevNode.next = newNode;
numItems++;
```
삽입은 위에 적어둔 알고리즘으로 모두 해결할 수 있다.
- 중간과 마지막 노드는 사실상 어떤 이유로 저렇게 작동하는지 명료하므로 설명하지 않는다.
- 사실상 빈 리스트에 첫 원소를 삽입하는 것이 애매해보일 수 있다.
  - 삽입 노드의 직전 노드는 더미 헤드 노드 자신을 참조하므로 `prevNode`는 더미 헤드를 가리킨다.
  - 이 점에서 착안한다면 잘 작동한다는 것을 생각해볼 수 있다.

## 양방향 원형 연결 리스트 작업 변동 : 원소 삭제
```
currNode.prev.next = currNode.next;
currNode.next.prev = currNode.prev;
numItems--;
```
원소 삭제 역시 모든 케이스에 대해 위에 적어둔 알고리즘으로 모두 해결할 수 있다.   
리스트가 길면 후반부 원소에 대해 리스트를 훑어가는 것이 내키지 않을 수도 있다.   
이럴 때는 양방향성을 이용해 개선이 가능하다.   
중간 기준으로 후반부인 `getNode()`는 뒤에서부터 훑어나가면 빠르다.
```
currNode = head
if (index < numItems / 2)
    for (int i = 0; i <= index; i++)
        currNode = currNode.next
else
    for (int i = numItems - 1; i >= index; i--)
        currNode = currNode.prev
return currNode
```

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/out/production/Data-Structure/list 내에 있다.