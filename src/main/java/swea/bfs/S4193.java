package swea.bfs;

import java.util.*;
import java.io.*;

public class S4193 {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static boolean[][] v;
    static int sr, sc, er, ec;
    static class Point implements Comparable<Point>{
        int r;
        int c;
        int time;

        public Point(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }

        @Override
        public int compareTo(Point o) {
            if(time > o.time) {
                return 1;
            }
            return -1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            v = new boolean[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            sr = Integer.parseInt(st.nextToken());
            sc = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            er = Integer.parseInt(st.nextToken());
            ec = Integer.parseInt(st.nextToken());

            int ans = solution();
            sb.append(ans).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static int solution() {
        PriorityQueue<Point> pq = new PriorityQueue<>();

        pq.offer(new Point (sr, sc, 0));
        v[sr][sc] = true;

        while(!pq.isEmpty()) {
            Point p = pq.poll();
            if(p.r == er && p.c == ec) {
                return p.time;
            }

            for(int i =0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if(0 <= nr && nr < N && 0 <= nc && nc < N && !v[nr][nc] && map[nr][nc] != 1) {
                    v[nr][nc] = true;
                    if(map[nr][nc] == 2) {
                        if((p.time + 1) % 2 == 2) {
                            pq.offer(new Point(nr, nc, p.time + 2));
                        } else {
                            int time = p.time + 2 - (p.time % 3) + 1;
                            pq.offer(new Point(nr, nc, time));
                        }
                    } else {
                        pq.offer(new Point(nr, nc, p.time + 1));
                    }
                }
            }
        }
        return -1;
    }
}

