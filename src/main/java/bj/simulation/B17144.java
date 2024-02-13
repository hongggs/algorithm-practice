package bj.simulation;

import java.util.*;
import java.io.*;
public class B17144 {
    final static int[] dr = {-1, 1, 0, 0};
    final static int[] dc = {0, 0, -1, 1};
    static int R, C, T;
    static int[][] map;
    static int[][] spread;
    static ArrayList<Integer> circulator;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        spread = new int[R][C];
        circulator = new ArrayList<>();
        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == -1) {
                    circulator.add(i);
                }
            }
        }

        while(T-- > 0) {
            for(int i = 0; i < R; i++) {
                Arrays.fill(spread[i], 0);
            }
            //1. 미세먼지 확산
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(map[i][j] >= 5) {
                        spreadDust(i, j);
                    }
                }
            }
            calc();
            //2. 확산
            circulate();
        }

        int ans = 2;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                ans += map[i][j];
            }
        }
        System.out.println(ans);
    }

    static void spreadDust(int r, int c) {
        int cnt = countDir(r, c);
        int dust = map[r][c] / 5;
        spread[r][c] -= (cnt * dust);
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != -1) {
                spread[nr][nc] += dust;
            }
        }
    }

    static int countDir(int r, int c) {
        int result = 0;
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(0 <= nr && nr < R && 0 <= nc && nc < C && map[nr][nc] != -1) {
                result++;
            }
        }
        return result;
    }

    static void calc() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                map[i][j] += spread[i][j];
            }
        }
    }

    static void circulate() {

        int sr = circulator.get(0);
        //하
        for(int i = sr - 1; i > 0; i--) {
            map[i][0] = map[i - 1][0];
        }
        //좌
        for(int i = 0; i < C - 1; i++) {
            map[0][i] = map[0][i + 1];
        }
        //상
        for(int i = 0; i < sr; i++) {
            map[i][C - 1] = map[i + 1][C - 1];
        }
        //우
        for(int i = C - 1; i > 1; i--) {
            map[sr][i] = map[sr][i - 1];
        }
        map[sr][1] = 0;

        sr = circulator.get(1);
        //상
        for(int i = sr + 1; i < R - 1; i++) {
            map[i][0] = map[i + 1][0];
        }
        //좌
        for(int i = 0; i < C - 1; i++) {
            map[R - 1][i] = map[R - 1][i + 1];
        }
        //하
        for(int i = R - 1; i > sr; i--) {
            map[i][C - 1] = map[i - 1][C - 1];
        }
        //우
        for(int i = C - 1; i > 1; i--) {
            map[sr][i] = map[sr][i - 1];
        }
        map[sr][1] = 0;
    }
}

