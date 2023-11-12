package basic.shortestway;

//https://www.acmicpc.net/problem/1865
import java.util.*;
import java.io.*;

class Bellman_Ford {
    static int T, N, M, W;
    static ArrayList<Edge> graph;
    static final int INF = 10_001;
    static class Edge{
        int start;
        int target;
        int cost;
        Edge(int start, int target, int w) {
            this.start = start;
            this.target = target;
            this.cost = w;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for(int i = 1; i <= T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            graph = new ArrayList<>();

            //도로
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, w));
                graph.add(new Edge(e, s, w));
            }

            //웜홀
            for (int j = 0; j < W; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int w = -Integer.parseInt(st.nextToken());
                graph.add(new Edge(s, e, w));
            }

            if (bellman_ford(N, (2 * M) + W, 1)) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

    public static boolean bellman_ford(int N, int M, int start) {
        //모든 정점까지 최소비용을 INF값으로 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        //정점의 개수만큼 반복
        for(int i = 0; i < N; i++) {
            //간선의 개수만큼 반복
            for(int j = 0; j < M; j++) {
                Edge e = graph.get(j);
                if (dist[e.start] != INF && dist[e.target] > dist[e.start] + e.cost) {
                    dist[e.target] = dist[e.start] + e.cost;
                    if (i == N - 1) {
                        return true;
                    }
                }
            }
            for(int j = 1; j <= N; j++) {
                System.out.print(dist[i] + " ");
            }
            System.out.println();
        }

        return false;
    }
}