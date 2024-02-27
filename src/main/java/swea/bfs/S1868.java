package swea.bfs;

import java.io.*;
import java.util.*;

public class S1868 {

    static final int[] dr = {-1,0,1,0,-1,-1,1,1};
    static final int[] dc = {0,-1,0,1,-1,1,-1,1};
    static int N;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_d4_1868.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            visit = new boolean[N][N];

            String s;
            for(int i = 0; i < N; i++) {
                s = br.readLine();
                for(int j = 0; j < N; j++) {
                    if(s.charAt(j) == '.') {
                        map[i][j] = Integer.MAX_VALUE;
                    } else {
                        map[i][j] = -1;
                    }
                }
            }

            int ans = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == Integer.MAX_VALUE  && check(i, j) == 0) {
                        bfs(i, j);
                        ans++;
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] == Integer.MAX_VALUE) {
                        ans++;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[]{r, c, 0});

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            r = curr[0];
            c = curr[1];
            int cnt = curr[2];

            map[r][c] = cnt;
            if(cnt > 0 || visit[r][c]) {
                continue;
            }
            visit[r][c] = true;
            int nr, nc;
            for(int i = 0; i < dr.length; i++) {
                nr = r + dr[i];
                nc = c + dc[i];
                if(0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] == Integer.MAX_VALUE) {
                    if(map[nr][nc] == -1) {
                        break;
                    }
                    q.offer(new int[]{nr, nc, check(nr, nc)});
                }
            }
        }
    }

    static int check(int r, int c) {
        int cnt = 0;
        int nr, nc;
        for(int i = 0; i < dr.length; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(0 <= nr && nr < N && 0 <= nc && nc < N) {
                if(map[nr][nc] == -1) {
                    cnt++;
                }
            }
        }
        return cnt;
    }
}