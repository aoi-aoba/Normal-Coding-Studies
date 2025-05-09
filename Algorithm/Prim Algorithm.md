# 프림 알고리즘 : 최소 신장 트리를 찾는 알고리즘
## 최소 신장 트리(Minimum Spanning Tree)
간선의 방향이 없는 그래프에서 모든 정점들 간에 간선을 따라 서로 다다를 수 있으면 이 그래프를 '연결된 그래프(Connected Graph)' 혹은 '연결 그래프'라고 한다. 대부분의 그래프는 연결 상태를 유지하는 데 필요한 최소한의 간선보다 더 많이 가지는데, 그래프 $G=(V, E)$에서 일반적으로 $|V|-1$개의 간선을 가지면 모두 연결 상태를 가질 수 있다.

이진 검색 트리에 대해 생각해보면, 루트로부터 간선을 통해 자식들로 연결된 형태였다. 일반적으로 그래프 이론에서 트리는 '사이클이 없는 연결된 그래프'로 정의된다. 즉, 모든 트리는 $|V|-1$개의 최소한의 간선을 사용하는 것으로 유지되는 연결 그래프인 셈이다. 이렇게 정점 집합을 그대로 두고 간선을 최소한으로 남겨 트리가 되게 한 것을 '신장 트리(Spanning Tree)'라고 한다.

임의의 그래프로부터 최소 신장 트리는 꼭 하나만 만들어지진 않는다. 그리고 그래프 간선에 가중치가 있다면 신장 트리의 구성 간선 가중치를 모두 더한 합을 비용(Cost)라고 하는데, 가중치가 있는 무향 그래프 $G=(V,E)$로부터 만들 수 있는 모든 신장 트리 중에서 비용이 가장 낮은 트리를 '최소 신장 트리(Minimum Spanning Tree)'라고 한다.

아래에서는 최소 신장 트리를 찾는 것을 목표로 하는 알고리즘의 소개이다.

## 프림 알고리즘 (Prim Algorithm)
프림 알고리즘은 아무 간선도 없는 상태에서 간선을 하나씩 더하는 작업을 $|V|-1$번 한다. 이를 구현하는 수단으로서 정점 집합 $S$를 운용한다. 집합 $S$는 처음에는 시작 정점 $r$ 하나로 시작해 한 번에 정점을 하나씩 더해나가고, 하나 더할 때 대응되는 간선이 하나씩 더해진다. 결국 정점 $|V|-1$개를 더하면 모든 정점이 집합 $S$에 들어오며 $S=V$가 되고, 그 과정에서 간선 $|V|-1$개가 더해지며 최소 신장 트리를 이룬다.

## 프림 알고리즘의 형태
```
Prim(G, r):
// G=(V,E) 그래프가 주어지고 r은 시작 정점
    S <- {r} // S는 정점 집합
    r.cost <- 0
    for each u in set V-{r}
        u.cost <- w_ru
    while (S != V)
        u <- deleteMin(V-S)
        S <- S union {u}
        for each v in set u.adj
            if (v in set V-S and w_uv < v.cost)
                v.cost <- w_uv
                v.tree <- u

deleteMin(Q):
    집합 Q에서 u.cost 값이 가장 작은 정점 u를 삭제하면서 리턴
```

- `u.cost`는 정점 `u`를 신장 트리에 포함시키는 데 드는 최소 비용   
    - 궁극적으로는 각 정점 `u`를 신장 트리에 연결하기 위한 최소 비용을 갖게 되지만 수행 과정에서는 충분히 큰 값으로부터 시작하여 이 최소 비용을 향해 계속 감소함
    - 즉, `u.cost`는 지금까지의 실행 범위에서 정점 `u`의 연결 최소 비용
- `v.tree`는 `v`와 인접한 정점 중 지금까지의 점위에서 `v`를 신장 트리에 연결시키는 비용이 가장 적게 드는 `u`를 저장
    - 즉, `v`와 `u`를 잇는 간선 (v-u)가 저장되는 셈

알고리즘이 끝나면 `v.tree` 정보를 통해 최소 신장 트리를 만들 수 있다. 시작 정점 `r`을 제외한 나머지 정점에 대해 `.tree`를 활용해 `|V|-1`개의 간선을 특정할 수 있다.

## 프림 알고리즘의 시각화
![](https://velog.velcdn.com/images/aoi-aoba/post/16eff86c-23f9-4576-96bc-b7317f5dc421/image.png)

- 처음  그래프가 먼저 주어진다.   
- 먼저 정점 `r`을 집합 `S`의 첫 번째 원소로 포함시킨다. 시작 노드인 `r`의 `r.cost`만 0으로 두고, 나머지 모든 정점 `u`의 `u.cost`를 간선 `(r-u)`의 가중치로 초기화한다. (`w_ru`)
    - 이 과정에서 8, 9, 11의 세 정점이 `.cost`가 할당되었다.
    - 연결될 수 없는 정점들은 INF로 할당된다.
    - 그들 중 최솟값을 찾는다. 여기서는 8이 최솟값이다.
- 집합 `S`에 연결하는 데 가장 비용이 적은 정점을 새로 집합 `S`에 포함시킨다.
    - 8의 비용이 드는 정점을 집합 `S`에 포함시키고 나니 한 정점의 비용이 10으로 바뀌었다.
    - 이 최소 비용의 변경은 위 알고리즘에서 if문 아래의 `w_uv` 할당에 속한다.
- 지금 상황에서 가장 연결 비용이 작은 것은 9 비용이므로 이 정점을 포함시킨다.
    - 이 과정에서 한 정점의 연결 비용은 12로 바뀌고, 한 정점은 10에서 5로 바뀐다.
    - 연결 비용이 최소인 경우는 이제 비용이 5인 정점이다.
- 비용이 5인 정점을 새로 집합 `S`에 포함시킨다. 이 과정에서 새로 바뀌는 비용 영향은 없다.
- 비용이 최소인 정점 11을 `S`에 포함시킨다. 한 정점의 연결 비용이 8로 바뀐다.
- 비용이 최소인 정점 8을 ` S`에 포함시킨다. 한 정점의 연결 비용이 12에서 7로 바뀐다.
- 남은 정점이 7로서 연결되면서 모든 정점이 `S`에 포함되어 최소 신장 트리가 완성된다.

![](https://velog.velcdn.com/images/aoi-aoba/post/a0bbd75c-4746-437a-b3ba-d4a7af2eb05f/image.png)

결과값으로 나온 최소 신장 트리이다.

새로 들어간 정점으로 인해 변동이 생기는 경우도 있고, 변동이 전혀 생기지 않는 경우도 있다. 어떤 정점은 여러 번의 변동이 생기기도 한다. 이렇게 값이 바뀌는 것을 이완(Relaxation)이라고 한다.

## 프림 알고리즘의 수행 시간
```
Prim(G, r):
// G=(V,E) 그래프가 주어지고 r은 시작 정점
    S <- {r} // S는 정점 집합
    r.cost <- 0
    for each u in set V-{r}
        u.cost <- w_ru
    while (S != V)
        u <- deleteMin(V-S)
        S <- S union {u}
        for each v in set u.adj
            if (v in set V-S and w_uv < v.cost)
                v.cost <- w_uv
                v.tree <- u

deleteMin(Q):
    집합 Q에서 u.cost 값이 가장 작은 정점 u를 삭제하면서 리턴
```

다시 알고리즘을 보며 생각해보자.   
우선, for 루프는 정확히 $n-1$회 반복된다. $|V|$에서 $r$이라는 것 하나를 제외하고 실행된다. 각각의 for 루프는 단순 할당작업으로 상수 시간이 걸려 $\Theta(V)$ 시간을 소요한다. 다만, 결과적으로 while 루프가 더 시간이 소요될 것이므로 for 루프는 사실상 점근적 시간에 영향을 미치지 않는다.   
while 루프는 $n-1$회 반복된다. 이 안에 for 루프가 중첩되어 있다. 여기에서 2개 루프가 중첩되어 있긴 하지만 단순히 최대 반복 횟수를 곱하면 과다 계산된다. 사실상 여기서의 for 루프는 $u$와 인접한 간선을 훑는데 어떻게 되든 한 간선은 알고리즘을 통틀어서 두 번만 관심을 받게 된다. (if 문에 의하여) 그러므로 for 루프는 사실상 while 루프를 통틀어서 $2|E|$ 번 순환된다.   
프림 알고리즘의 수행 시간은 `deleteMin()`이 while 루프가 반복될 때마다 수행되는 시간과 for 루프 내에서 수행되는 cost/tree 할당이 수행되는 시간 중 더 큰 쪽이 좌우한다. `.cost`를 최소 힙으로 구성하면 `deleteMin()`은 사실상 $O(\log V)$ 시간이면 가능하다. while 루프가 $n-1$회 반복되므로 이와 관계된 비용은 $O(V \log V)$이다.   
그런데 `.cost` 값의 변동이 생기면 힙을 조정해주어야 한다. 힙에서 임의 원소 값이 변해서 이를 반영해 조정하는 데는 $O(\log V)$ 시간이 소요된다. 이 수정은 최악의 경우 $|E|$ 번 일어날 수 있으므로 총 비용은 $O(E \log V)$가 된다.

## 인접 행렬 방식을 활용한 Java로의 구현
```Java
public class PrimAlgorithm {
    private static final int INF = Integer.MAX_VALUE;
    private int V; // 그래프의 정점 개수

    public PrimAlgorithm(int V) {
        this.V = V;
    }

    public void primMST(int[][] graph) {
        // 최소 신장 트리를 구성하고 출력하는 함수

        boolean[] mstSet = new boolean[V];  // 최소 신장 트리에 구성된 노드
        int[] key = new int[V];             // 키 값 (최소 가중치 저장)
        int[] parent = new int[V];          // 최소 신장 트리의 부모를 저장

        Arrays.fill(key, INF);  // 키 값을 무한대로 초기화
        key[0] = 0;             // 시작 정점
        parent[0] = -1;         // 첫 번째 노드는 루트

        for (int count = 0; count < V - 1; count++) {   // 첫 번째 노드를 제외한 노드에 대해
            int u = minKey(key, mstSet);                // 최소 키 값을 받아 u로 저장
            mstSet[u] = true;                           // u는 최소 신장 트리에 포함시킴

            for (int v = 0; v < V; v++) {
                if (graph[u][v] != 0 && !mstSet[v] && graph[u][v] < key[v]) {
                    // 그래프에서 u와 인접하면서 최소 신장 트리에 안 들어갔고 지금까지 알려진 최소 가중치보다 u로 가는 경로가 작다면
                    parent[v] = u;
                    key[v] = graph[u][v];
                    // u의 이전 노드를 v로 하고 v의 최소 비용을 갱신하여 key에 저장
                }
            }
        }

        printMST(parent, graph); // 모두 마치면 출력
    }

    private int minKey(int[] key, boolean[] mstSet) {
        int min = INF, minIndex = -1;

        for (int v = 0; v < V; v++) {
            if (!mstSet[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }
        // 전체 정점을 돌면서 해당 정점이 없으면서 최소 비용인 정점의 인덱스를 반환

        return minIndex;
    }

    private void printMST(int[] parent, int[][] graph) { // 결과 출력
        System.out.println("Edge \tWeight");
        for (int i = 1; i < V; i++)
            System.out.println(parent[i] + " - " + i + "\t" + graph[i][parent[i]]);
    }

    public static void main(String[] args) { // 테스트 코드
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        PrimAlgorithm pa = new PrimAlgorithm(5);
        pa.primMST(graph);
    }
}
```

