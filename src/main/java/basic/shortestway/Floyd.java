package basic.shortestway;

import java.util.Arrays;

public class Floyd {
    static final int INF = 10_001;
    public static void main(String[] args) {

    }

    public static int[][] floyd(int N, int[][] graph) {
        Arrays.fill(graph, INF);
        for (int k = 1; k <= N; k++) { //거쳐갈 노드
            graph[k][k] = 0;
            //왼전 탐색
            for (int i = 1; i <= N; i++) {
                for(int j = 1; j <= N; j++) {
                    graph[i][j] = Math.min(graph[i][j], graph[i][k] + graph[k][j]);
                }
            }
        }
        return graph;
    }
}
