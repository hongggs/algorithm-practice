package bj.dfs;

import java.util.*;
import java.io.*;
public class Solution_bj_체스판 {
    /**
     * 해당 지점에서 시작 가능한지 확인
     * 가능하다면, B인 경우나 W인 경우로 마킹 시작
     */
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st =  new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        String s;
        for(int i = 0; i < N; i++) {
            s = br.readLine();
            for(int j = 0; j < M; j++) {
                if(s.charAt(j) == 'W') {
                    map[i][j] = 0;
                } else {
                    map[i][j] = 1;
                }
            }
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(isValid(i + 8 - 1, j + 8 - 1)) {
                    ans =  Math.min(ans, getCount(i, j));
                }
            }
        }
        System.out.println(ans);
    }

    static int getCount(int r, int c) {
        int cnt1 = 0;
        //W인 경우
        int old = 1;
        for(int i = r; i < r + 8; i++) {
            for(int j = c; j < c + 8; j++) {
                if(map[i][j] == old) {
                    cnt1++;
                }

                if(j == (c + 8 - 1)) {
                    continue;
                }
                old ^= 1;
            }
        }

        /*
        int cnt2 = 0;
        old = 0;
        for(int i = r; i < r + 8; i++) {
            for(int j = c; j < c + 8; j++) {
                if(map[i][j] == old) {
                    cnt2++;
                }

                if(j == (c + 8 - 1)) {
                    continue;
                }
                old ^= 1;

            }
        } */
        return Math.min(cnt1, 64 - cnt1);
    }

    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < M;
    }
}
