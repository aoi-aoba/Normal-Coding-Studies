# 위상 정렬(Topological Sorting)
## 위상 정렬
위상 정렬은 사이클이 없는 방향 그래프 $G = (V, E)$를 정렬하는 방식이다. 그래프의 모든 간선에 대해서 $V$의 모든 정점을 정렬하되 다음 성질을 만족해야 한다.

> 간선 $(i, j)$가 존재한다면 정렬 결과에서 정점 $i$는 반드시 정점 $j$보다 앞에 위치한다.

만약 그래프에 사이클이 있다면 다음 성질은 결코 만족될 수 없으므로 위상 정렬을 할 수 없다.

## 위상 정렬의 특징

![](https://velog.velcdn.com/images/aoi-aoba/post/0ca8d8d6-73b2-4cf9-9459-0bb15df07ea9/image.png)

사실상 간선 산의 방향이 주어져 있어서 선후관계가 확실한 것이 아니라면 굳이 아무 제약을 받지 않는다. 잘 보면, 일반적으로는 a에서 b로 가는 것을 처음에 생각하게 되겠지만 사실상 c는 a와 아무런 관계가 없으므로 c를 먼저 한 다음에 a로 이동해도 아무런 문제가 없는 것이다. b를 진행한 다음에 c로 가도 된다. 즉, 모든 간선에 대하여 간선의 방향이 주어지면서 선후관계를 명확히 하지 않는다면 해당 선후관계만 지켜진다면 어떤 순서라도 좋은 것이다.

## 위상 정렬의 알고리즘
```
topologicalSort(G):
    for i <- 0 to n-1
        진입 간선이 없는 정점 u를 선택
        A[i] <- u
        정점 u와 u의 진출 간선들을 모두 제거
// 이 시점에 배열 A에는 정점들이 모두 위상 정렬된 상태가 됨
```

![](https://velog.velcdn.com/images/aoi-aoba/post/1bb5ea1c-ac40-491b-b01d-d4428d99899f/image.png)

진입 간선(Incoming Edge)은 왼쪽처럼 `u`를 향하는 방향으로 오는 간선을, 진출 간선(Outgoing Edge)은 오른쪽처럼 `u`에서 뻗어나가는 간선을 의미한다. 

## 위상 정렬 알고리즘의 시각화
![](https://velog.velcdn.com/images/aoi-aoba/post/15551c1a-6e57-420a-bed7-d397163eea9d/image.png)

- 정점을 우선 살펴보며 진입 간선이 없는 정점을 본다. a와 c가 있다.
- 진입 간선이 없는 정점 중 임의로 a를 선택하고, 이 정점과 진출 간선을 모두 제거한다.
    - 해당 상황에서 진입 간선이 없는 정점은 b와 c이다.
- 진입 간선이 없는 정점 중 임의로 b를 선택하고, 이 정점과 진출 간선을 모두 제거한다.
    - 해당 상황에서 진입 간선이 없는 정점은 c밖에 없다.
- 진입 간선이 없는 c를 선택하고, 이 정점과 진출 간선을 모두 제거한다.
    - 해당 상황에서 진입 간선이 없는 정점은 e와 f이다.
- 진입 간선이 없는 정점 중 임의로 e를 선택하고, 이 정점과 진출 간선을 모두 제거한다.
    - 해당 상황에서 진입 간선이 없는 정점은 f밖에 없다.
- 진입 간선이 없는 f를 선택하고, 이 정점과 진출 간선을 모두 제거한다.
- 남은 한 개의 정점 d는 당연히 진입 간선이 없으므로 이를 제거한다. 이 시점에 배열에는 위상 정렬이 마무리된다.

정점이 제거되는 순서가 하나의 위상 정렬을 이루게 되는 것이다. 이는 위에서 제시한 세 가지 방법 중에서 방법 1에 해당한다.

## 위상 정렬 알고리즘의 수행 시간
위상 정렬 알고리즘은 개인의 프로젝트 스케줄링, 가중치를 가지고 사이클이 없는 방향 그래프의 최단 경로 찾기 등에 사용된다. 위에서 제시한 알고리즘을 보면, for 루프가 $n$번 반복된다. 반복할 때마다 1개의 정점이 선택되고 해당 정점에 연결된 진출 간선이 모두 제거된다. 각 간선은 단 한 번만 취급되기 때문에 총 수행 시간은 $\Theta(V+E)$이다. 물론, 이를 달성하기 위해서는 진입 간선이 없는 정점을 찾는 것이 상수 시간에 이루어져야 한다고 해야 가능하다.

### 진입 간선이 없는 정점을 상수 시간에 찾기
하지만 이는 완전히 자명하지 않다. 우선, 그래프 표현이 인접 행렬이면 행렬의 준비 과정에서 $\Theta(V^2)$이 소요되므로 안 된다. 그러면 인접 리스트로 생각해보자. 인접 리스트에서 간선 $(u, v)$는 정점 $u$의 연결 리스트에만 포함시키기 때문에 정점 $u$의 진출 간선으로만 관리된다. 그런데 이러면 진입 간선이 없는 정점을 찾기 위해서는 모든 정점의 진출 간선 리스트를 다 보아야 하므로 이것 하나를 찾기 위해 최악의 경우 $\Theta(V+E)$가 걸릴 수 있다.

그래서 이 문제를 해결하기 위해서 다음과 같이 할 수 있다. 알고리즘을 시작할 때 배열 `C[0...n-1]`에 각 정점의 간선 수를 기록하고, 인접 리스트를 한 번 훑으며 간선 $(u, v)$를 만나면 `C[v]`를 1 증가시킨다. 이와 같은 방법으로 정점의 진입 간선 수를 기록하고 난 뒤 간선을 지우면서 배열 값을 1씩 지워가면서 진입 간선 수를 항상 가질 수 있게 하면 된다. 이런 관리 과정에서 `C[v]`가 0인 리스트를 하나 만들어서 진입 간선 수가 0인 정점을 따로 관리한다면 필요할 때 이들 중 하나를 빼서 사용하면 된다. 그래서 이런 일련의 과정을 통해 상수 시간 내에 정점을 찾고 알고리즘이 $\Theta(V+E)$ 시간에 가능해진다.

## 위상 정렬의 예시 코드(Java)
```Java
public class TopologicalSort {
    private static int[] incomingEdges, sorted;
    private static List<Integer> noIncomingEdgeVertexList = new ArrayList<>();
    private static List<List<Integer>> edgeList;

    public static void topologicalSort(int N) {
        sorted = new int[N];
        for (int i = 0; i < N; i++) {
            int vertex = noIncomingEdgeVertexList.getFirst();
            sorted[i] = vertex;
            for (int temp : edgeList.get(vertex))
                incomingEdges[temp]--;
            edgeList.get(vertex).clear();
            noIncomingEdgeVertexList.removeFirst();
            incomingEdges[vertex] = -1;
            for (int j = 0; j < incomingEdges.length; j++)
                if (incomingEdges[j] == 0 && !noIncomingEdgeVertexList.contains(j))
                    noIncomingEdgeVertexList.add(j);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer init = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(init.nextToken()), E = Integer.parseInt(init.nextToken());

        incomingEdges = new int[N];
        Arrays.fill(incomingEdges, 0);

        edgeList = new ArrayList<>();
        for (int i = 1; i <= N; i++) edgeList.add(new ArrayList<>());
        for (int i = 0; i < E; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()), to = Integer.parseInt(st.nextToken());
            edgeList.get(from-1).add(to-1); // 가중치 없는 방향 그래프 저장
            incomingEdges[to-1]++; // 진입 간선 수 세기 위한 배열
        }

        for (int i = 0; i < incomingEdges.length; i++)
            if (incomingEdges[i] == 0) noIncomingEdgeVertexList.add(i);

        topologicalSort(N);
        for (int item : sorted) System.out.print((item + 1) + " ");
        br.close();
    }
}
```