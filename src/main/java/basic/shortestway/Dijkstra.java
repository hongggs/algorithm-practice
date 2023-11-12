package basic.shortestway;
//https://www.acmicpc.net/problem/1916
import java.util.*;
import java.io.*;

class Dijkstra {
    static int N, M;
    static ArrayList[]  adjList;
    static final int INF = Integer.MAX_VALUE;
    static class Edge implements Comparable<Edge>{
        int target;
        int w;
        Edge(int target, int w) {
            this.target = target;
            this.w = w;
        }

        public int compareTo(Edge e) {
            if(w > e.w) {
                return 1;
            }
            return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Edge>();
        }

        StringTokenizer st;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            adjList[start].add(new Edge(target, w));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());

        int ans = dijkstra(start, target);
        System.out.println(ans);
    }
    public static int dijkstra(int start, int target) {
        //모든 정점까지 최소비용을 INF값으로 초기화
        int[] dist = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            dist[i] = INF;
        }

        //최단거리를 판단할 PQ선언 후 시작 지점 삽입, 출발지 비용을 0으로 둔다.
        PriorityQueue pq = new PriorityQueue<Edge>();
        pq.add(new Edge(start, 0));

        dist[start] = 0;

        //최소 힙에서 맨 위에 있는 정점을 꺼낸다
        while(!pq.isEmpty()) {
            Edge now = (Edge) pq.poll();

            if(dist[now.target] < now.w) {
                continue;
            }

            int len = adjList[now.target].size();

            for(int i = 0; i < len; i++) {
                Edge next = (Edge) adjList[now.target].get(i);
                if(dist[next.target] > now.w + next.w) {
                    dist[next.target] = now.w + next.w;
                    pq.add(new Edge(next.target, dist[next.target]));
                }
            }
        }
        return dist[target];
    }
}
