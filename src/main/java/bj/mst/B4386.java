package bj.mst;

import java.awt.Point;
import java.io.*;
import java.util.*;

public class B4386 {
    static int N;
    static int[] parent;
    static Point[] points;

    static class Point {
        double x;
        double y;

        public Point(double x, double y) {
            this.x = x;
            this.y = y;
        }
    }
    static class Edge implements Comparable<Edge> {
        int start;
        int target;
        double cost;

        public Edge(int start, int target, double cost) {
            this.start = start;
            this.target = target;
            this.cost = cost;
        }

        public int compareTo(Edge e) {
            if(e.cost > this.cost) {
                return -1;
            }
            return 1;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        initParent();
        points = new Point[N + 1];

        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());
            points[i] = new Point(x, y);
        }

        System.out.println(kruskal());
    }

    static double kruskal(){
        double ans = 0.0;
        int cnt = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for(int start = 1; start < N; start++) {
            for(int target = start + 1; target <= N; target++) {
                pq.offer(new Edge(start, target, getDist(points[start], points[target])));
            }
        }

        while(!pq.isEmpty()) {
            Edge now = pq.poll();
            if(find(now.start) != find(now.target)) {
                union(now.start, now.target);
                ans += now.cost;
            }
        }
        return Math.round(ans * 100)/100.0;
    }

    static void initParent() {
        for(int i = 1; i <= N; i++) {
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
        a = find(a);
        b = find(b);

        if(a <= b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
    }

    private static double getDist(Point p1, Point p2) {
        double x1 = p1.x;
        double y1 = p1.y;
        double x2 = p2.x;
        double y2 = p2.y;
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }
}