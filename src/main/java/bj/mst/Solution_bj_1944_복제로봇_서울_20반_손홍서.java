package bj.mst;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_bj_1944_복제로봇_서울_20반_손홍서 {

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int N, M;
    static int[][] map;
    static int[][] keys;
    static int[] parent;
    static PriorityQueue<Link> pq;
    static class Link implements Comparable<Link>{
        int s, e, w;

        public Link(int s, int e, int w) {
            this.s = s;
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Link o) {
            if(w > o.w) {
                return 1;
            }
            return -1;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int index = 1;
        keys = new int[M + 1][2];
        parent = new int[M + 1];
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            String s = br.readLine();
            for(int j = 0; j < N; j++) {
                char temp = s.charAt(j);
                if(temp == 'S') {
                    keys[0][0] = i;
                    keys[0][1] = j;
                    map[i][j] = 5;
                } else if(temp == 'K') {
                    keys[index][0] = i;
                    keys[index++][1] = j;
                    map[i][j] = 9;
                } else {
                    map[i][j] = temp - '0';
                }
            }
        }
        pq = new PriorityQueue<>();
        for(int i = 0; i <= M; i++) {
            getDist(i);
        }
        initParent();
        System.out.println(solution());
    }

    static int solution() {
        int ans = 0;

        while(!pq.isEmpty()) {
            Link now = pq.poll();

            if (find(now.s) != find(now.e)) {
                union(now.s, now.e);
                ans += now.w;
            }
        }

        for(int i = 1; i <= M; i++) {
            if(find(i) != 0) {
                return -1;
            }
        }
        return ans;
    }

    static void initParent() {
        for(int i = 0; i <= M; i++) {
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
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
        }
    }

    static void getDist(int key) {
        boolean[][] v = new boolean[N][N];
        Queue<int[]> q = new ArrayDeque<>();
        v[keys[key][0]][keys[key][1]] = true;
        q.offer(new int[]{keys[key][0], keys[key][1], 0});

        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int i = 0; i < dr.length; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if(0 <= nr && nr < N && 0 <= nc && nc < N && !v[nr][nc] && map[nr][nc] != 1) {
                    if(map[nr][nc] != 0) {
                        for(int j = 0; j <= M; j++) {
                            if(keys[j][0] == nr && keys[j][1] == nc) {
                                pq.offer(new Link(key, j, now[2] + 1));
                                break;
                            }
                        }
                    }
                    v[nr][nc] = true;
                    q.offer(new int[]{nr, nc, now[2] + 1});
                }
            }
        }
    }
}
