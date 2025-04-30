# 이진 탐색 트리 알고리즘과 그 구현
## 검색
이진 검색 트리에서 키가 `x`인 노드를 검색하고 해당 노드가 존재하면 해당 노드를 리턴, 존재하지 않으면 `null`을 리턴한다. 여기서는 키가 `x`인 노드를 리턴하지만 필요에 따라 노드 안의 정보를 리턴할 수도 있다.
 
 ![](https://velog.velcdn.com/images/aoi-aoba/post/d2dc18f0-cada-4ef4-9343-b6da4b64d8a0/image.png)
 
 ### 검색 알고리즘
 ```
 search(t, x):
    // t는 (서브)트리의 루트 노드 레퍼런스, x는 검색하고자 하는 키
    if (t = null || t.item = x) return t
    else if (x  < t.item)
        return search(t.left, x)
    else return search(t.right, x)
 ```

 알고리즘 입력으로 검색할 트리의 루트 노드 레퍼런스와 검색하고자 하는 키가 주어진다. 루트 노드 `t`의 킷값과 `x`를 비교하여 `x`가 더 작으면 `t`의 좌서브 트리로 향해 `x`를 찾는다. 반대로, 더 크면 우서브트리로 향하면 된다. 운 좋게 노드의 킷값이 `x`와 일치한다면 답이라고 알린다. `t`가 `null`이면 `null`을 리턴한다. 검색이 성공하든 실패하든 `return t` 부분을 통해 끝나는데, 실패하면 `null`이, 성공하면 검색한 킷값의 노드 레퍼런스가 리턴된다.

 ### 검색 성공과 실패
 성공적인 검색의 경우 `search(*, x)`를 재귀적으로 수행하다가 `x`를 가진 노드를 만나면 해당 노드 레퍼런스를 리턴한다.   

 실패한 검색의 경우 두 가지 케이스가 있을 수 있다.
 - 리프 노드까지 쭉 타고 내려간 결과 양쪽이 모두 `null`이므로 실패라 판정
 - 내부 노드(Internal Node)에서 더 이상 이동할 수 없어서 실패라 판정

## 삽입
 원소 `x`를 이진 검색 트리에 삽입하려면 이진 검색 트리에 `x`를 킷값으로 가진 노드가 없어야 한다. 삽입 자리를 찾는 것은 검색을 한 번 실패하는 것과 같다. 즉, 루트 노드로부터 `x`에 대한 검색을 실행한 결과 임의 리프 노드에 이르러 더 내려갈 곳이 없음이 확인되면 `x`를 해당 리프 노드의 자식으로 매달면 된다.

 ### 원소 삽입의 예시

 가령, 다음과 같은 삽입 순서의 트리를 만든다고 하자.
 > 30, 20, 25, 40, 10, 35

 - 아무것도 없는 상태의 트리에서 30이 입력되어 하나만으로 이루어진 트리가 만들어짐
 - 20이 입력되어 30보다 작으므로 30의 왼쪽으로 매달림
 - 25가 입력되어 30보다는 작으므로 30의 왼쪽으로 향하고, 20보다는 크므로 20의 오른쪽에 매달림
 - 40이 입력되어 30보다는 크므로 30의 오른쪽으로 매달림
 - 10이 입력되어 30보다는 작으므로 30의 왼쪽으로 향하고, 20보다도 작으므로 20의 왼쪽에 매달림
 - 35가 입력되어 30보다는 크므로 30의 오른쪽으로 향하고, 40보다는 작으므로 40의 왼쪽에 매달림

 ### 삽입 알고리즘의 초안
 ```
 insertSketch(t, x):
    // t는 (서브)트리의 루트 노드 레퍼런스, x는 삽입하고자 하는 키
    if (t = null)
        x를 키로 하는 노드를 t의 부모 밑에 매달고 종료
    else if (x < t.item) insertSketch(t.left, x)
    else insertSketch(t.right, x)
 ```
 대략적으로는 이런 알고리즘을 따르게 될 것이다. 여기에서 *'노드를 t의 부모 밑에 매달고'* 부분을 구현하는 것이 영 자명하지 않다. 부모 노드에서 `insertSketch()`를 호출했지만 그 안에서는 부모 노드가 누구인지 모르므로 부모 밑에 매달 수가 없다. 파라미터를 늘려 부모 노드의 레퍼런스를 포함시키는 방법도 있긴 하겠지만 알고리즘이 지저분해진다.
 
 ### 삽입 알고리즘의 재귀적 버전
 ```
 insert(x):
    // root는 트리의 루트 노드 레퍼런스
    root <- insertItem(root, x)
 
 insertItem(t, x):
    // t는 (서브)트리의 루트 노드 레퍼런스, x는 삽입하고자 하는 키
    // 작업 완료 이후 루트 노드의 레퍼런스를 리턴
    if (t = null)
        r.item <- x     // r은 새 노드
        r.left <- null
        r.right <- null
        return r
    else if (x < t.item)
        t.left <- insertItem(t.left, x)
        return t
    else
        t.right <- insertItem(t.right, x)
        return t
 ```
 여기에서 중요한 것이 있다면, 부모 노드의 레퍼런스 정보 갱신을 재귀함수의 리턴 값으로 처리하는 것이다. `t.left`와 `t.right`가 `insertItem()`의 리턴 값을 기다리고 있으머 이렇게 함으로써 이를 바로 할당해줄 수 있는 것이다. 즉, 부모 노드가 자식의 레퍼런스를 갱신하기 위해 기다리고 있고, `insertItem()`은 그저 새 노드의 레퍼런스를 리턴하면 된다.   
 새로운 노드가 아닌 경우는 그 노드의 레퍼런스를 그냥 리턴하고, 부모 노드에서는 기존 링크를 그대로 써주면 된다. 알고리즘의 3개의 `return` 문 중 하나를 통해 자신을 호줄한 함수에서 기다리고 있는 레퍼런스 값을 채우게 된다. 번거롭게 `insert()`가 하나 더 있는 것은 원소가 아무것도 없는 빈 트리일 경우에 대해 `null`로 된 루트의 레퍼런런스 갱신을 위해서이다.

### 삽입 알고리즘의 비재귀적 버전
```
// root는 트리의 루트 노드 레퍼런스
insert(x):  // 삽입하고자 하는 키 x
    r.item <- x
    r.left <- null
    r.right <- null // r은 새 노드
    if (root = null)
        root <- r // 아예 새 트리인 경우
    else
        t <- root
        while (t != null) // t가 빈 노드가 아닐 때까지
            parent <- t // t를 부모로서 기억하고
            if (x < t.item) t <- t.left
            else t <- t.right
            // 방향에 맞춰서 내려가기
        if (x < parent.item) parent.left <- r
        else parent.right <- r
        // 빈 노드에 도착한 경우, 기억했던 부모와의 관계에 따라 삽입
```

## 검색과 삽입 알고리즘의 점근적 수행 시간
$n$개의 원소로 이진 검색 트리를 만들 때 이상적인 균형 상태에 놓이면 최악의 경우라도 검색 시간은 $\Theta(\log{n})$으로 나타난다. 가장 나쁘게 기울어버린다면, 평균 검색 시간이 $\Theta(n)$이 된다. 가능한 모든 삽입 순서에 따른 트리를 고려하면 평균 검색 시간은 $\Theta(\log{n})$이다. 삽입은 이후 상수 시간의 후처리가 있을 뿐이므로 점근적 수행 시간은 동일하다.

## 삭제
삽입은 항상 리프 노드에서 실행되었지만, 삭제는 트리의 전 영역에서 발생하기 때문에 경우에 따라 다르게 처리할 필요가 있다.
 ### 세 가지 경우
 ```
 deleteSketch(r): // r은 삭제할 노드
    if (r이 리프 노드)
        그냥 r을 버림
    else if (r의 자식이 하나만 있음)
        r의 부모가 r의 자식을 직접 가리키게 함
    else
        r의 오른쪽 서브 트리의 최소 원소 노드 s를 탐색
        s를 r자리로 복사하고 s를 삭제
 ```
 <table>
    <tr>
        <td> 
            <img src = https://velog.velcdn.com/images/aoi-aoba/post/b696e1a2-e8b2-4b20-bec3-f41cad2c97d6/image.png>
        </td>
        <td> 
            <img src = https://velog.velcdn.com/images/aoi-aoba/post/47cfd9b3-f45d-44a2-97aa-8a15ac8240f3/image.png>
        </td>
        <td>
            <img src = https://velog.velcdn.com/images/aoi-aoba/post/a6f52c5e-a723-449b-89ed-a72411cb2ac2/image.png>
        </td>
    </tr>
 </table>
  

 자식 노드가 없는 경우에는 특별히 생각할 필요 없이 `r`을 삭제해도 아무런 영향을 미치지 않는다. 단지 `r`의 부모 노드에서 `r`을 가리키던 링크를 `null`로 바꾸면 되므로 굉장히 간단하다.

 자식 노드가 하나 있는 경우, 그대로 `r`을 삭제해버리면 아래 `r`의 자식 부분부터가 완전히 끊어져버린다. 그래서 `r`의 부모 노드가 `r`을 가리키던 링크를 `r`의 자식을 가리키게 하면 간단히 해결할 수 있다.

 다만, 복잡한 것은 이 경우이다. 만약, 자식 노드를 둘 다 가지고 있다면, `r`을 제거하면 자식들로 가는 양쪽의 연결이 모두 끊어져버린다. 여기에서 자식 노드들 중에서 `r`을 대체할 수 있는 노드를 생각해보면, 서브 트리 원소들보다 크고 우서브 트리보다 작은 원소이므로 다음과 같이 두 가지가 있다.
 - `r`의 좌서브 트리 중에서 가장 큰 원소 (크기순으로 직전에 위치하는 원소)
 - `r`의 우서브 트리 중에서 가장 작은 원소 (크기순으로 직후에 위치하는 원소)

 편의상 이들 중에서 `r`의 크기순으로 직후 원소를 고르자. 그러면 직후 원소는 절대 왼자식을 가질 수 없다. 그러면 오른자식을 가지거나 리프 노드인 경우에 해당하므로 Case 1이나 Case 2가 되므로 비교적 간단히 삭제할 수 있게 된다. 그리고 그 직후원소를 가져와서 `r`의 자리로 배치해줌에 따라 원래 목적에 맞게 삭제할 수 있다.

 ### 삭제 알고리즘의 비재귀적 버전 + 삭제할 노드가 주어지는 버전
 ```
 delete(r, p): // r이 삭제하고자 하는 노드, p는 r의 부모 노드
    if (r = root) 
    // r이 루트 노드인 경우
        root <- deleteNode(root)
    else if (r = p.left)
        p.left <- deleteNode(r)
    else p.right <- deleteNode(r)

 deleteNode(r):
    if (r.left = r.right = null) return null
    // case 1
    else if (r.left = null and r.right != null) return r.right
    // case 2-1
    else if (r.left != null and r.right = null) return r.left
    // case 2-2
    else
        s <- r.right
        while (s.left != null)
            parent <- s
            s <- s.left
        if (s = r.right)
            r.right <- s.right
        else parent.left <- s.right
        return r
 ```

 삭제할 노드가 주어진 상태에서 노드를 삭제하는 알고리즘으로, 함수 `delete`에서 `deleteNode`를 호출하며 `root`, `p.left`, `p.right`가 리턴 값을 기다리고 부모 노드가 기다리는 링크를 리턴해주는 방식이다. 실제로 구현해야 할 때는 좀 더 해야 한다. `delete()`에서는 삭제하려는 노드 `r`의 부모 노드가 주어진다고 했는데, 이게 일반적이지 않다. 단순히 `search` 알고리즘으로 삭제할 키를 가진 노드를 검색한다면 해당 키를 가진 노드는 찾으나 부모 노드의 정보는 가져올 수 없다. 그러면
 - 어떻게든 명시적으로 부모 노드를 알려줘야 하거나
 - 부모 노드가 필요로 하는 링크 정보를 리턴하게 하거나
 선택해야 하는데, 부모 노드의 정보를 복원할 수 있도록 관리하면 코드가 지저분해진다. 삭제할 노드가 정해진 상태에서 시작하므로 삭제 노드의 부모를 갖고 있어야 해서 이런 진행이 불가피해진다.

 ### 삭제 알고리즘의 재귀적 버전 + 삭제할 원소가 주어지는 버전
 ```
 delete(t, x):
    if (t = null)
        // ERROR
    else if (x = t.item)
        t <- deleteNode(t)
        return t
    else if (x < t.item)
        t.left <- delete(t.left, x)
        return t
    else
        t.right <- delete(t.right, x)
        return t
    

 deleteNode(t):
    if (t.left = null && t.right = nul)
        return null // case 1
    else if (t.left = null)
        return t.right  // case 2-1
    else if (t.right = null)
        return t.left   // case 2-2
    else
        (minItem, node) <- deleteMinItem(t.right)
        t.item <- minItem
        t.right <- node
        return t

 deleteMinItem(t):
    if (t.left = null)
        return (t.item, t.right)
    else
        (minItem, node) = deleteMinItem(t.left)
        t.left <- node
        return (minItem, t)
 ```

 삭제할 원소를 가진 노드를 찾는 작업부터 시작하면 루트에서 시작하여 부모 노드에서 리턴 값을 기다리게 할 수 있다. 부모 노드의 정보를 찾고 내려가는 대신 부모 노드에서 함수의 리턴 값을 기다린다. 2개의 값을 쌍으로 받게 되는데, 이는 t의 직후 원소 값을 가져오는 작업과 직후 원소 삭제 작업을 동시에 하기 때문이다. 보통 두 작업을 따로 해야 이해가 빠르긴 한데, 그러면 우서브트리를 또 한 번 내려갔다 오고 지궇 원소 삭제와 관련한 링크 조정을 위해 또 내려가야 하는 번거로움이 있기 때문에 이를 한 번으로 줄인 것이다.

 `deleteMinItem`의 리턴 값은 아이템과 노드의 쌍이다. 이 중, 아이템은 우서브트리의 최솟값 아이템이므로 삭제한 노드에 덮어씌우기 위해 변하지 않고 전달된다. 반면, 노드는 경로를 변경하며 위로 전달된다.

## 삭제 알고리즘의 점근적 수행 시간
일단 원소를 가진 노드를 찾아야 하므로 $\Theta(\log{n})$이다. 찾은 이후의 시간에서는 Case 1, 2는 상수 시간이 걸리고 Case 3은 노드 `r`의 직후 원소를 찾는 데 서브 트리의 높이에 비례하는 시간이 든다. 직후 원소를 찾고 직후 원소 노드를 삭제하는 작업은 Case 1, 2에 해당하므로 상수 시간이 걸린다. 그리고 삭제 노드까지 거슬러 올라가는 과정은 역시 서브 트리 높이에 비례한다. 이 과정을 합하면 루트에서 직후 원소까지 내려가는 시간에 비례하므로 삭제 작업의 시간은 트리의 최대 깊이를 상한으로 한다. 그 결과 $O(\log{n})$ 혹은 $O(n)$이 될 수도 있다.

