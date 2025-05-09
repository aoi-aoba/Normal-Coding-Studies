package Graph;

import java.io.*;
import java.util.*;

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
