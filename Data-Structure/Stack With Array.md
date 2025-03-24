# 배열을 이용한 스택
## 배열 스택의 객체 구조
스택을 위한 배열 `stack[0...n-1]`을 활용하자.   
변수 `topIndex`는 스택의 맨 위쪽 원소의 자리를 가리키는 배열 인덱스이다.   
- 스택의 맨 위 원소인 스택 탑 원소에 대해 나타낼 수 있다.   
- `topIndex`는 처음에는 원소가 없다는 뜻으로 -1로 세팅하자.
- 원소가 하나씩 삽입될 때마다 1씩 증가시킨다.

임의의 배열 스택 구조에 대해서 하나의 객체(Class)로서 정의된다고 했을 때, 구조는 다음과 같다:
- **필드** : 스택의 원소들이 저장되는 배열 `stack[]`, 스택 탑 원소 자리 인덱스 `topIndex`
- **메소드** : `push(x)`, `pop()`, `top()`, `isEmpty()`, `popAll()`
  - `push(x)` : 스택의 맨 위에 원소 x를 삽입한다.
  - `pop()` : 스택의 맨 위 원소를 알려주고 삭제한다.
  - `top()` : 스택의 맨 위 원소를 알려준다.
  - `isEmpty()` : 스택이 비었는지 알려준다.
  - `clear()` : 스택을 비운다.

## 배열 스택의 작업 : (1) 원소 삽입
스택에 새 원소를 삽압할 때는 스택 탑 **바로 위**&ZeroWidthSpace;에 원소를 저장하고 **스택 탑 인덱스를 1 증가**&ZeroWidthSpace;시킨다.
- 먼저 `topIndex`를 1 증가시키고 그 자리에 새 원소를 저장한다.
- 만약, `topIndex = stack.length - 1`이면 배열이 꽉 찬 것이므로 들어갈 공간이 없다.

### 원소 삽입의 ADT 구조
```
push(x):
    if (isFull())
        /* 에러 처리 */
    else
        topindex++
        stack[topIndex] <- x
```

## 배열 스택의 작업 : (2) 원소 삽입
스택의 원소 삭제 시 **무조건 탑의 원소를 삭제**&ZeroWidthSpace;한 후 **스택 탑 인덱스를 1 감소**&ZeroWidthSpace;시킨다.  
단, 이때 탑 인덱스의 **값을 리턴**&ZeroWidthSpace;해주는 것이 포인트이다.  
만약 `topIndex` 값이 -1이라면 스택이 빈 상태이다.  

### 원소 삭제의 ADT 구조
```
pop():
    if (isEmpty())
        /* 에러 처리 */
    else
        topItem <- stack[topIndex]
        topIndex--
        return topItem
```

## 배열 스택의 기타 작업
기타 작업들에 대해서도 ADT 구조를 살펴보자.

1. 스택 탑 인덱스 자리의 원소를 리턴하는 `top()`
```
top():
    if (isEmpty())
        /* 에러 처리 */
    else return stack[topIndex]
```

2. 스택이 꽉 찬 상테인지 확인하는 `isFull()`
```
isFull():
    if (topIndex = stack.length - 1)
        return true
    else return false
```

3. 스택이 비었는지 확인하는 `isEmpty()`
```
isEmpty():
    if (topIndex = -1)
        return true
    else return false
```

4. 스택을 완전히 비우는 `popAll`
```
popAll():
    topIndex <- -1
```

## 구현
JAVA를 활용해 구현한 코드는 Normal-Coding-Studies/Data-Structure-With-JAVA/Data-Structure/out/production/Data-Structure/stack 내에 있다.