package bj.simulation;

import java.io.*;
import java.util.*;
public class Solution_bj_4920_테트리스게임 {
    static int N;
    static int[][] map;
    /**
     * 가장 위쪽의 가장 왼쪽 좌표 기준으로 direction선언하기
     */
    static int[][][] d = {
            {{0,0,0,0},{0,1,2,3}},
            {{0,1,2,3},{0,0,0,0}},
            {{0,0,1,1},{0,1,1,2}},
            {{0,1,1,2},{0,0,-1,-1}},
            {{0,0,0,1},{0,1,2,2}},
            {{0,1,2,2},{0,0,0,-1}},//
            {{0,0,1,2},{0,1,0,0}},
            {{0,1,1,1},{0,0,1,2}},
            {{0,1,2,1},{0,0,0,1}},
            {{0,1,1,1},{0,-1,0,1}},
            {{0,0,0,1},{0,1,2,1}},
            {{0,1,1,2},{0,-1,0,0}},//
            {{0,0,1,1},{0,1,0,1}}
    };
    static int MIN = Integer.MIN_VALUE;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int t = 1;
        while(true) {
            N = Integer.parseInt(br.readLine().trim());
            if(N == 0) {
                break;
            }
            sb.append(t).append(". ");

            map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = MIN;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    ans = Math.max(ans, getCount(i, j));
                }
            }

            sb.append(ans).append("\n");
            t++;
        }
        System.out.println(sb);
    }
    static int getCount(int r, int c)
    {
        int maxCount = MIN;
        for(int k = 0; k < d.length; k++) {
            int temp = 0;
            for(int i = 0; i < 4; i++) {
                int nr = r + d[k][0][i];
                int nc = c + d[k][1][i];
                if(isValid(nr, nc)) {
                    temp += map[nr][nc];
                } else {
                    break;
                }
                if(i == 3) {
                    maxCount = Math.max(maxCount, temp);
                }
            }
        }
        return maxCount;
    }
    static boolean isValid(int r, int c) {
        return 0 <= r && r < N && 0 <= c && c < N;
    }
}
