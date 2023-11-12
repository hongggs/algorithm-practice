package inflearn.dfs;

import java.util.*;
import java.io.*;

class Combination_MEMO {
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        dp = new int[n + 1][n + 1];
        System.out.println(dfs(n, r));
    }
    public static int dfs(int n, int r) {
        if(dp[n][r] > 0) {
            return dp[n][r];
        }
        if(n == r || r == 0) {
            return 1;
        } else {
            return dp[n][r] = dfs(n - 1, r -1) + dfs(n - 1, r);
        }
    }

}