# 연결 리스트를 이용한 큐
## 연결 리스트를 활용
연결 리스트에는 단방향, 양방향, 원형, 더미 헤드 노드의 유무 등 다양한 선택지가 있다.   
여기서는 큐의 특성을 활용하기 위하여 **단방향 원형 연결 리스트** 방식을 활용한다.   
맨 뒤의 노드에 대한 레퍼런스만 있다면 관리가 가능하다.

- 메소드 `enqueue`, `dequeue`, `front`, `isEmpty`, `dequeueAll`
- 필드는 큐의 끝을 가리키는 레퍼런스인 `tail` 하나만을 필요로 한다.
- 빈 큐는 `tail`을 `null`로 두는 것 외에는 할 일이 없다.

## 연결 리스트 큐의 작업 : (1) 원소 삽입
큐에 원소를 하나 삽입할 때는 맨 뒤에 넣는다.   
원소를 `tail` 노드 뒤에 삽입하고 `tail`이 삽입된 새 노드를 가리키게 한다.   
새 노드는 리스트의 첫 번째 노드로 연결시킨다.   
배열 큐와 달리 **연결 리스트 큐는 노드를 새로 할당** 받기 때문에 삽입하지 못하는 경우는 없다.   
큐가 비어 있을 때는 `next` 사용이 불가하므로 `newNode.next <- newNode`로 자기 순환 형태를 만들어야 한다.

### 원소 삽입의 ADT 구조
```
enqueue(newItem):
    if ( isEmpty() )
        newNode.next <- newNode
        tail <- newNode
    else
        newNode.next <- tail.next
        tail.next <- newNode
        tail <- newNode
```
더미 헤드 노드를 둔다면 역시 이 과정을 하나로 통일할 수 있다.

## 연결 리스트 큐의 작업 : (2) 원소 삭제
원소를 삭제할 때는 무조건 **맨 앞에 있는 원소를** 삭제한다.   
원형 리스트에서는 `tail` 노드가 `front`를 링크하므로 `tail`을 한 번 따라가서 `front`를 만날 수 있다.   
`front` 노드의 `tail` 링크를 `front`의 다음 노드로 가리키게 하면 된다.   
원소가 하나라면 유일한 원소가 삭제되므로 `tail`이 `null`이 되어야 한다.   
그래서 이 과정에서 원소가 하나인지, 큐가 비어 있는지 모두 확인해볼 필요가 있다.

### 원소 삭제의 ADT 구조
```
dequeue():
    if ( isEmpty() ) /* 에러 처리 */
    else
        front <- tail.next
        if (front = tail)
            tail <- null
        else tail.next <- front.next
        return front.item
```

## 연결 리스트 큐의 기타 작업
```
front():
    if ( isEmpty() ) /* 에러 처리 */
    else return tail.next.item

isEmpty():
    if (tail = null) return true
    else return false

dequeueAll():
    tail <- null
```

- `front`의 경우 `tail.next`가 `front`를 가리키기 때문에 이를 활용하면 된다.
- `dequeueAll`의 경우, `tail`을 `null`로 돌리면 나머지는 가비지 컬렉션 가능한 상태로 돌아가면서 무리 없이 처리할 수 있다.

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/out/production/Data-Structure/queue 내에 있다.