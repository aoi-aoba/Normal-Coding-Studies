# 다익스트라 알고리즘(Dijkstra Algorithm)
## 최단 경로
네비게이션에 목적지를 입력하면 가장 효율적인 경로를 알려주는데, 어떤 목적지로 갈 때 주로 최단 거리 혹은 최단 시간이 소요되는 경로를 알려주게 된다. 이런 최단 경로(Shortest Path) 구하기 문제는 가중치가 있는 방향 그래프에서 자주 사용된다. 그래프 $G=(V, E)$에서 $E$가 가중치를 가진 방향 간선들의 집합일 때 임의 경로는 연속된 간선들로 이루어진다. 즉, 간선들의 체인이다. 경로를 구성하는 간선들의 가중치 합을 해당 경로의 길이라고 한다.

최단 경로 문제에서 입력 유형은 주로 두 가지이다. 첫째는 모든 간선 가중치가 음이 아닌 일반적인 경우로, 현실 지도를 그래프로 표현하면 이에 해당하고 이는 주로 다익스트라 알고리즘으로 풀이한다. 다른 하나는 음의 가중치가 존재하고 이를 허용하지만 가중치 합이 음인 사이클은 절대 허용하지 않는 경우로, 이는 주로 벨만-포드 알고리즘으로 풀이한다. 음의 사이클이 있으면 해당 사이클을 몇 번이고 반복해 돌아 경로의 가중치 합을 무한정으로 낮출 수 있으므로 문제 자체가 성립할 수 없다.

다익스트라 알고리즘과 벨만-포드 알고리즘은 하나의 시작 정점으로부터 다른 모든 정점에 이르는 최단 경로를 구한다. 이에 반해 모든 정점 쌍 간의 최단 경로를 구하는 알고리즘도 있다. 앞의 유형은 단일 시작점 최단 경로 문제, 뒤의 유형은 모든 쌍 최단 경로 문제라고 한다. 여기서는 단일 시작점 최단 경로 문제로 한정한다. 임의의 두 정점 간 경로가 존재하지 않는 경로의 길이는 $\infty$, 즉 무한대로 정의한다. 즉 가중치가 $\infty$인 간선이 있다고 가정하는 것인데 이는 매우 자연스러운 가정이다.

## 다익스트라 알고리즘의 형태
```
Dijkstra(G, r):
    // G는 주어진 그래프, r은 시작 정점
    S <- {r} // S는 정점의 집합
    r.cost <- 0
    for each u in set V-{r}
        u.cost <- w_ru
    while (S != V)
        u <- deleteMin(V-S)
        S <- S union {u}
        for each v in set u.adj // u.adj는 정점 u에 인접한 정점들의 집합
            if (v in set V-S and u.cost + w_uv < v.cost)
                v.cost <- u.cost + w_uv
                v.prev <- u

deleteMin(Q):
    집합 Q에서 u.cost가 가장 작은 정점 u를 리턴
```

다익스트라 알고리즘을 보면 최소 신장 트리를 위한 프림 알고리즘의 원리와 거의 유사한 골격을 가지고 있다. 다만, 프림 알고리즘에서는 `v.cost`가 정점 `v`의 신장 트리에 대한 연결 비용을 저장하는 반면 다익스트라 알고리즘은  `v.cost`가 시작 정점 `r`에서 정점 `v`에 이르는 거리를 저장한다. 알고리즘 진행 중에 `v.cost`는 지금까지 진행된 범위 내에서 `v`에 이르는 최단 거리 값을 찾는다.

## 다익스트라 알고리즘의 시각화
![](https://velog.velcdn.com/images/aoi-aoba/post/283e8bf8-2da6-42a0-8cb2-0675baefb4cf/image.png)

- 초기에 그래프가 주어진다. 주어지는 그래프는 가중치가 주어진 방향 그래프여야 한다.
- 먼저 정점 $r$을 집합 $S$의 첫 번째 원소로 포함시킨다. 그림에서 한 단계 진행될 때마다 이 집합의 크기가 커진다.
    - 시작 노드인 $r$에 대하여 `r.cost`만 0으로 두고, 나머지 모든 정점 $u$의 `u.cost`를 간선 $(r, u)$의 가중치로 초기화한다. 
    - 그 결과 $r$에 인접한 3개 정점의 `.cost`는 각각 8, 9, 11로 할당된다.
    - 이 중에서 집합 $S$ 바깥의 정점들 중 `.cost`가 가장 작은 정점을 고른다.
- `.cost`가 가장 작은 정점인 8을 집합 $S$에 새롭게 포함시킨다. 한 정점의 거리 값이 $\infty$에서 18이 되었다.
    - 정점의 최단 거리 값이 $\infty$에서 특정 수로 변하는 경우는 최초로 시작 정점 $r$에서 이르는 경로가 발견되었을 때이다.
    - 이제 집합 $S$ 바깥의 정점들 중 최단 거리 값이 가장 작은 것은 9이다.
- 연결 비용이 9인 정점을 새롭게 집합 $S$에 포함시킨다. 한 정점의 최단 거리가 18에서 10으로 변했다.
    - 정점의 최단 거리 값이 특정 수에서 다른 수로 변하는 경우는 이미 경로가 발견되었지만 더 짧은 경로가 발견된 경우이다. 정점의 거리 값은 이처럼 여러 번 바뀔 수 있다.
    - 이제 나머지 정점들 중 최단 거리 값이 가장 작은 것은 10이다.
- 최단 거리 10인 정점이 $S$에 추가되어 한 정점의 최단 거리 값이 $\infty$에서 12로 바뀌었다.
- 그 다음 최단 거리 11인 정점이 $S$에 추가되어 두 정점의 최단 거리 값이 $\infty$에서 19로 바뀌었다.
- 그 다음 최단 거리 12인 정점이 $S$에 추가되어 한 정점의 최단 거리 값이 19에서 16으로 바뀌었다.
- 그 다음 최단 거리 16인 정점이 $S$에 추가되고 이로 인해 최단 거리가 바뀌는 정점은 없다.
- 마지막 남은 최단 거리 19인 정점이 $S$에 추가되면서 알고리즘이 종료된다.
- 마지막은 각 정점의 최단 거리 값이 마지막으로 바뀔 때 사용된 간선을 모은 것이고 모든 정점의 최단 경로는 이 간선들로만 이루어진다.

## 다익스트라 알고리즘의 특징
시간 복잡도(점근적 수행 시간)는 예상했듯, 프림 알고리즘과 거의 같아 동일하다. 힙 구조를 사용한다는 가정 하에 $O(E \log V)$ 시간이 소요된다. 다익스트라 알고리즘은 이전에 말했듯 간선의 가중치가 음이 아닐 때만 사용하는데, 간선의 가중치가 음이 되면 작동하지 않기 때문이다. 다익스트라 알고리즘에서는 임의 정점을 집합 $S$에 더할 때 $r$에서 그 정점까지의 최단 거리는 계산이 끝났다는 확신을 가지고 더한다. 만약 음의 가중치가 존재한다면 이후에 더 짧은 경로가 존재할 가능성이 있어 다익스트라 알고리즘의 논리적 기반이 무너지게 된다. 다익스트라 알고리즘의 골격을 유지한 채에서 조금 바꾼다고 하여 음의 가중치 문제를 해결하기는 어렵다. 이때는 벨만-포드 알고리즘을 사용하는 것이 바람직하다.

## Java 예시코드
```Java
public class Main {
    private static final int INF = Integer.MAX_VALUE;
    public static int V; // 그래프의 정점 개수

    public static void Dijkstra(List<List<int[]>> graph, int startNode) { // 크루스칼 알고리즘을 실행하는 함수
        // 시작 정점은 입력을 받음
        int[] dist = new int[V];
        Arrays.fill(dist, INF);
        dist[startNode] = 0; // 값 초기화 과정

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        // 우선순위 큐를 사용하여 힙 구조를 통한 가장 짧은 거리의 정점부터 처리하게 설정 (정점까지의 최단 거리는 1번 인덱스에 있음)
        pq.offer(new int[]{startNode, 0}); // 시작 정점에서 자기 자신까지의 거리는 0이므로 우선순위 큐에 삽입

        while (!pq.isEmpty()) {
            int[] now = pq.poll(); // 현재 가장 짧은 거리의 정점을 꺼내기
            int u = now[0], d = now[1]; // 현재 방문 중인 정점을 u로 두고 d는 해당 정점까지의 최단 거리

            if (dist[u] < d) continue; // 이미 u까지 더 짧은 거리로 방문한 적 있다면 경로를 무시함 (중복 계산 방지)
            for (int[] neighbor : graph.get(u)) { // 정점 u에 연결된 모든 이웃 노드를 탐색
                int v = neighbor[0], weight = neighbor[1]; // 이웃 정점 v의 간선 가중치 weight 받아오고
                if (dist[v] > dist[u] + weight) { // 현재 경로로 v까지 가는 거리가 기존보다 짧다면
                    dist[v] = dist[u] + weight; // dist를 현재 경로로 갱신하고
                    pq.offer(new int[]{v, dist[v]}); // 갱신된 거리 값과 v를 우선순위 큐에 추가
                }
            }
        }

        print(dist);
    }

    private static void print(int[] dist) { // 결과 출력
        for (int i = 0; i < dist.length; i++) {
            System.out.println("정점 " + i + "까지의 최단 거리: " + (dist[i] == INF ? "INF" : dist[i]));
        }
    }

    public static void main(String[] args) throws IOException { // 테스트 코드
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer init = new StringTokenizer(br.readLine());
        V = Integer.parseInt(init.nextToken());
        int N = Integer.parseInt(init.nextToken()), P = Integer.parseInt(init.nextToken());
        // 가중치 입력으로 주어지는 입력의 개수
        List<List<int[]>> graph = new ArrayList<>();
        for (int i = 0; i < V; i++) graph.add(new ArrayList<>());

        /*
        입력은 유향 그래프의 노드가 0번부터 V-1번까지 주어진다고 가정하고,
        공백을 포함하여 '시작 노드, 끝 노드, 가중치'가 주어진다고 가정한다.
        첫 줄에는 공백을 포함하여 '노드 개수 V, 간선 개수 N, 시작 노드 번호 P'가 주어진다.
         */
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), value = Integer.parseInt(st.nextToken());
            graph.get(start).add(new int[] {end, value});
        }

        Dijkstra(graph, P);
    }
}
```

테스트 코드
```
8 14 0
0 1 8
0 3 9
0 2 11
1 4 10
3 1 6
3 2 3
3 4 1
4 7 2
6 3 12
6 7 5
2 6 8
2 5 8
5 6 7
7 5 4
```

위 테스트 코드는 '다익스트라 알고리즘의 시각화' 부분에서 설명한 초기 그래프의 설정이다. 왼쪽 위부터 오른쪽 위 대각선으로 올라가면서 0번부터 노드의 번호를 매겼다.