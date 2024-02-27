package swea.bfs;

import java.io.*;
import java.util.*;

public class S2117 {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static int N, M;
    static int[][] map;
    static int totalCnt;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_모의_2117.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            map = new int[N][N];
            totalCnt = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        totalCnt++;
                    }
                }
            }

            int ans = 0;
            int K = 1;
            while(true) {
                if(ans == totalCnt || totalCnt * M - (K * K + (K - 1) * (K - 1)) <= 0) {
                    break;
                }
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        int cnt = bfs(i, j, K - 1);
                        int money = cnt * M - (K * K + (K - 1) * (K - 1));
                        if( money >= 0) {
                            ans = Math.max(ans, cnt);
                        }
                    }
                }
                K++;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);

    }

    static class Point {
        int r, c;
        int depth;
        public Point(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }

    }

    static int bfs(int r, int c, int depth) {
        boolean[][] visit = new boolean[N][N];
        ArrayDeque<Point> q = new ArrayDeque<>();

        visit[r][c] = true;
        q.offer(new Point(r, c, 0));

        int result = 0;
        while(!q.isEmpty()) {
            Point curr = q.poll();
            if(map[curr.r][curr.c] == 1) {
                result++;
            }
            if(curr.depth == depth) {
                continue;
            }
            for(int d = 0; d < dr.length; d++) {
                int nr = curr.r + dr[d];
                int nc = curr.c + dc[d];
                if(0 <= nr && nr < N && 0 <= nc && nc < N && !visit[nr][nc]) {
                    visit[nr][nc] = true;
                    q.offer(new Point(nr, nc, curr.depth + 1));
                }
            }
        }
        return result;
    }

}
