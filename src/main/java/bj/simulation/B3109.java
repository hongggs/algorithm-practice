package bj.simulation;

import java.util.*;
import java.io.*;
public class B3109 {

    static final int[] dr = {-1, 0, 1};
    static final int[] dc = {1, 1, 1};
    static int R, C;
    static char[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for(int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        for(int i = 0; i < R; i++) {
            if(map[i][0] == 'x') {
                continue;
            }

            if(solution(i, 0)) {
                map[i][0] = 'x';
                ans++;
            }
        }
        System.out.println(ans);
    }

    static boolean solution(int r, int c) {
        if(c == C - 1) {
            return true;
        }
        int nr, nc;
        for(int d = 0; d < dr.length; d++) {
            nr = r + dr[d];
            nc = c + dc[d];
            if(0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != 'x') {
                map[nr][nc] = 'x';
                if(solution(nr, nc)) {
                    return true;
                }
            }
        }
        return false;
    }
}

