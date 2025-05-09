package Graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

// 인접 행렬 기반 선형 탐색을 통한 프림 알고리즘 구현 - 시간 복잡도 O(V^2)
public class PrimAlgorithm {
    private static final int INF = Integer.MAX_VALUE;
    public static int V; // 그래프의 정점 개수

    public static void primMST(int[][] graph) {
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

    private static int minKey(int[] key, boolean[] mstSet) {
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

    private static void printMST(int[] parent, int[][] graph) { // 결과 출력
        List<int[]> lines = new ArrayList<>();
        for (int i = 1; i < V; i++)
            lines.add(new int[] {parent[i], i, graph[i][parent[i]]});
        lines.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]) return Integer.compare(o1[0], o2[0]);
                else return Integer.compare(o1[1], o2[1]);
            }
        });
        StringBuilder sb = new StringBuilder();
        sb.append("Edge \tWeight\n");
        for (int[] arr : lines)
            sb.append(arr[0]).append(" - ").append(arr[1]).append("\t").append(arr[2]).append("\n");
        System.out.println(sb);
    }

    public static void main(String[] args) throws IOException { // 테스트 코드
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer init = new StringTokenizer(br.readLine());
        V = Integer.parseInt(init.nextToken());
        int N = Integer.parseInt(init.nextToken()); // 가중치 입력으로 주어지는 입력의 개수
        int[][] graph = new int[V][V];
        for (int i = 0; i < V; i++) for (int j = 0; j < V; j++) graph[i][j] = 0;

        // 무향 그래프의 입력이 들어온다고 가정, 입력은 두 정점의 번호 및 가중치가 공백 구분으로 주어진다고 가정
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken()), num2 = Integer.parseInt(st.nextToken()), value = Integer.parseInt(st.nextToken());
            graph[num1][num2] = value;
            graph[num2][num1] = value;
        }

        primMST(graph);
    }
}
