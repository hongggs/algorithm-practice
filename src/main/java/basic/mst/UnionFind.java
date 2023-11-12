package basic.mst;

import java.util.*;
import java.io.*;

class UnionFind {
    static int N, M;
    static int[] parent;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        initParent();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            union(a, b);

        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        if(find(a) == find(b)) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }

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
        int pa = find(a);
        int pb = find(b);

        if(pa <= pb) {
            parent[pa] = pa;
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
            parent[pb] = pb;
        }
    }
}
