package bj.dp;

import java.io.*;
import java.util.*;

public class B11779 {
    static int N;
    static int M;
    static ArrayList[] adjList;
    static final int INF = Integer.MAX_VALUE;

    static class Edge implements Comparable<Edge>{
        int target;
        int cost;

        public Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        public int compareTo(Edge n) {
            return this.cost - n.cost;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        adjList = new ArrayList[N + 1];
        for(int i = 1; i <= N; i++) {
            adjList[i] = new ArrayList<Edge>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            adjList[start].add(new Edge(target, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        dijkstra(start, end);
        br.close();
    }

    public static void dijkstra(int start, int end) {
        StringBuilder sb = new StringBuilder();

        int[] parent = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        int[] dp = new int[N + 1];
        Arrays.fill(dp, INF);

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.offer(new Edge(start, 0));
        dp[start] = 0;

        while(!pq.isEmpty()) {
            Edge curr = pq.poll();

            if(dp[curr.target] < curr.cost) {
                continue;
            }

            int len = adjList[curr.target].size();
            for(int i = 0; i < len; i++) {
                Edge next = (Edge) adjList[curr.target].get(i);
                if(dp[next.target] > dp[curr.target] + next.cost) {
                    parent[next.target] = curr.target;
                    dp[next.target] =  dp[curr.target] + next.cost;
                    pq.offer(new Edge(next.target, dp[next.target]));
                }
            }
        }

        sb.append(dp[end]).append("\n").append(getCount(parent, end)).append("\n").append(getRoute(parent, end));
        System.out.println(sb);
    }

    private static int getCount(int[] parent, int index) {
        int cnt = 0;
        while(true) {
            cnt++;
            if(parent[index] == index) {
                break;
            }
            index = parent[index];
        }
        return cnt;
    }

    private static String getRoute(int[] parent, int index) {
        if(parent[index] == index) {
            return index + " ";
        }
        return getRoute(parent, parent[index]) + index + " ";
    }
}