package bj.dfs;

import java.util.*;
import java.io.*;

public class B1987 {

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static int R, C;
    static int[][] map;
    static boolean[] v;
    static int ans, cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        v = new boolean[26];

        String s;
        for(int i = 0; i < R; i++) {
            s = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j) - 'A';
            }
        }

        ans = cnt = 1;
        v[map[0][0]] = true;
        solution(0, 0);
        System.out.println(ans);
    }


    static void solution(int r, int c) {
        int nr, nc;
        for(int d = 0; d < dr.length; d++) {
            nr = r + dr[d];
            nc = c + dc[d];
            if(0 <= nr && nr < R && 0 <= nc && nc < C && !v[map[nr][nc]]) {
                v[map[nr][nc]] = true;
                cnt++;
                solution(nr, nc);
                ans = Math.max(ans, cnt);
                cnt--;
                v[map[nr][nc]] = false;
            }
        }
    }
}
