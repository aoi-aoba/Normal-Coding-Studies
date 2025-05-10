package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class DijkstraAlgorithm {
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
