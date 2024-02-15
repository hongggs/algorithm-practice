package bj.dfs;

import java.util.*;
import java.io.*;
public class B1992 {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N+1][N+1];
        String s;
        for(int i = 1; i <= N; i++) {
            s = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j+1] = s.charAt(j) - '0';
            }
        }

        System.out.println(solution(1, 1, N));
    }

    static String solution(int r, int c, int n) {

        if(n == 1) {
            return map[r][c] + "";
        }

        int zeroCnt = 0;
        for(int i = r; i < r + n; i++) {
            for(int j = c; j < c + n; j++) {
                if(map[i][j] == 0) {
                    zeroCnt++;
                }
            }
        }

        if(zeroCnt == 0) {
            return "1";
        } else if(zeroCnt == n*n) {
            return "0";
        } else {
            return "(" + solution(r, c, n/2)
                    + solution(r, c + n/2, n/2)
                    + solution(r + n/2, c, n/2)
                    + solution(r + n/2, c + n/2, n/2) + ")";
        }

    }
}
