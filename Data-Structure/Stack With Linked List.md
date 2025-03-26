# 연결 리스트를 이용한 스택
## 연결 리스트를 활용
연결 리스트 스택을 사용한다면 스택을 연결 리스트로 구성하고, 어느 쪽을 탑으로 삼을 것인지 결정하면 된다.   
연결 리스트의 맨 앞 혹은 맨 끝을 스택 탑으로 삼을 수 있고, 변수 `topNode`가 해당 노드를 가리키게 하면 된다.   
빈 스택의 모양에서는 `topNode`를 `null`로 해두고 빈 연결 리스트를 만들어두면 된다.

## 배열 스택의 작업 : (1) 원소 삽입
스택에 원소를 삽입할 때는 무조건 맨 위에 놓는다.   
연결 리스트의 **맨 앞에 새 노드를 삽입**&ZeroWidthSpace;하고 탑 레퍼런스를 새 노드로 바꾼다.   
배열 스택에서 삽입 시 스택이 꽉 차면 삽입을 못했으나 연결 리스트에는 삽입하지 못하는 경우는 없다.   
연결 리스트는 중간 원소 삽입과 맨 앞 원소 삽입을 구분하는 번거로움을 줄이기 위해 **더미 헤드 노드** 배치를 했다.   
하지만 스택에서는 원소 삽입이 맨 앞에서만 발생하므로 굳이 더미 헤드 노드를 두지 않아도 된다.

### 원소 삽입의 ADT 구조
```
push(x):
    newNode.item <- x
    newNode.next <- topNode
    topNode <- newNode
```

## 배열 스택의 작업 : (2) 원소 삭제
원소를 삭제할 때는 무조건 **스택 탑 원소를** 삭제한다.   
연결 리스트의 맨 앞 노드를 삭제하고 탑 레퍼런스를 원래 노드의 다음 노드로 설정하면 된다.

### 원소 삭제의 ADT 구조
```
pop():
    if ( isEmpty() )
        /* 에러 처리 */
    else
        temp <- topNode
        topNode <- topNode.next // 스택 탑 레퍼런스 조정
        return temp.item // 스택 탑 원소 리턴
```

## 배열 스택의 기타 작업
탑 원소를 알려주는 `top()`은 스택 탑 인덱스 자리의 원소를 리턴한다.   
스택이 비어 있는지 확인하는 `isEmpty()`는 연결 리스트가 비었는지 확인하면 된다.
스택이 비어 있는지 확인하는 `isEmpty()`는 연결 리스트가 비었는지 확인하면 된다.
스택을 비울 때는 스택 탑 레퍼런스를 `null`로 되돌리면 된다.

```
top():
    if ( isEmpty() )
        /* 에러 처리 */
    else return topNode.item

isEmpty():
    if ( topNode = null )
        return true
    else return false

popAll():
    topNode <- null
```

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/out/production/Data-Structure/stack 내에 있다.