package basic.mst;

import java.util.*;
import java.io.*;

class MST_PQ {
    static int N, M;
    static boolean[] visit;
    static PriorityQueue<Edge> pq;
    static ArrayList<ArrayList<Edge>> graph;
    static class Edge implements Comparable<Edge>{
        int target;
        int cost;
        Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        public int compareTo(Edge e) {
            return cost - e.cost;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visit = new boolean[N + 1];
        pq = new PriorityQueue<>();
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Edge(target, cost));
            graph.get(target).add(new Edge(start, cost));
        }
        System.out.println(mst());
    }

    static int mst() {
        int ans = 0;
        pq.offer(new Edge(1,0));
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            if(!visit[e.target]) {
                visit[e.target] = true;
                ans += e.cost;
                for(Edge ne : graph.get(e.target)) {
                    if(!visit[ne.target]) {
                        pq.offer(ne);
                    }
                }
            }
        }
        return ans;
    }
}