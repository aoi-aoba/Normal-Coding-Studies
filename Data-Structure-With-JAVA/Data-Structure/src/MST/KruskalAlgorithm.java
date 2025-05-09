package MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class KruskalAlgorithm {
    static int V;
    static int[] parent;

    static int find(int x) {
        // 유니온-파인드 함수에서 특정 노드의 루트(parent)를 찾음
        if (parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
    }

    static boolean union(int a, int b) {
        // 유니온-파인드 함수에서 두 정점을 같은 집합으로 묶음
        int rootA = find(a), rootB = find(b);
        // 파인드 함수로 찾아온 루트를 비교
        if (rootA == rootB) return false; // 같은 집합이면 사이클이 발생하므로
        parent[rootB] = rootA; // 다른 집합일 때만 합친다
        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        int[][] graph = new int[V][V];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            graph[u][v] = graph[v][u] = w;
        }
        // 위쪽은 입력을 받는 부분이므로 크게 변화하지 않음

        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < V; i++)
            for (int j = i + 1; j < V; j++)
                if (graph[i][j] != 0) edges.add(new int[] {i, j, graph[i][j]});
        // u, v, w 형태로 간선을 저장

        edges.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                return Integer.compare(e1[2], e2[2]);
            }
        }); // 모든 간선을 가중치 크기순으로 정렬해서 저장

        parent = new int[V];
        for (int i = 0; i < V; i++) parent[i] = i;
        // 각 정점이 자기 자신을 대표로 가지면서 각각의 정점을 하나의 집합으로 간주

        int total = 0;
        List<int[]> mst = new ArrayList<>();
        for (int[] e : edges) {
            if (union(e[0], e[1])) {
                total += e[2];
                mst.add(e);
            }
        }
        // 간선을 하나씩 살펴보며 유니온-파인드 함수로 보내고 집합 합친 경우만 최소 신장 트리로

        mst.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] e1, int[] e2) {
                if (e1[0] != e2[0]) return Integer.compare(e1[0], e2[0]);
                else return Integer.compare(e1[1], e2[1]);
            }
        });

        sb.append("Edge \tWeight\n");
        for (int[] arr: mst)
            sb.append(arr[0]).append(" - ").append(arr[1]).append("\t").append(arr[2]).append("\n");
        sb.append("Total Weight: ").append(total);

        System.out.println(sb);
    }
}
