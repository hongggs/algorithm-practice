package basic.mst;

import java.util.*;
import java.io.*;

class Kruskal { //Minimum Spanning Tree
    static int N, M;
    static int[] parent;
    static PriorityQueue<Edge> pq;
    static class Edge implements Comparable<Edge>{
        int start;
        int target;
        int cost;
        Edge(int start, int target, int cost) {
            this.start = start;
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

        parent = new int[N + 1];
        initParent();

        pq = new PriorityQueue<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.offer(new Edge(start, target, cost));
        }
        System.out.println(kruskal());
    }

    static void initParent() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }

    static void union(int a, int b) {
        if(a <= b) {
            parent[a] = a;
            parent[b] = a;
        } else {
            parent[a] = b;
            parent[b] = b;
        }
    }

    static int kruskal() {
        int ans = 0;
        int cnt = 0;
        while(!pq.isEmpty()) {
            if (cnt == N - 1) {
                break;
            }
            Edge e = pq.poll();
            int pa = find(e.start);
            int pb = find(e.target);
            if(pa != pb) {
                ans += e.cost;
                union(pa, pb);
                cnt++;
            }
        }
        return ans;
    }
}
