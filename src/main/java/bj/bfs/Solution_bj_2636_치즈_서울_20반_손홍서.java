package bj.bfs;

import java.util.*;
import java.io.*;

public class Solution_bj_2636_치즈_서울_20반_손홍서 {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static int R, C;
    static int[][] map;
    static int cnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];

        int totalCnt = 0;
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1) {
                    totalCnt++;
                }
            }
        }

        int ans = 0;
        int last = 0;
        while(true) {
            cnt = 0;
            bfs(0, 0);
            ans++;
            if(totalCnt - cnt <= 0) {
                last = cnt;
                break;
            } else {
                totalCnt -= cnt;
            }
        }

        sb.append(ans).append("\n").append(last);
        System.out.println(sb);
    }


    static void bfs(int r, int c) {
        boolean[][] v = new boolean[R][C];
        v[r][c] = true;
        Queue<int[]> q = new ArrayDeque<>();

        q.offer(new int[] {r, c});

        int nr, nc;
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            r = curr[0];
            c = curr[1];

            for(int d = 0; d < dr.length; d++) {
                nr = r + dr[d];
                nc = c + dc[d];
                if(0 <= nr && nr <R && 0 <= nc && nc < C && !v[nr][nc]) {
                    v[nr][nc] = true;
                    if(map[nr][nc] != 1) {
                        q.offer(new int[] {nr, nc});
                    } else {
                        map[nr][nc] = 0;
                        cnt++;
                    }
                }
            }
        }
    }
}
