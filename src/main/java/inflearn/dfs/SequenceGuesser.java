package inflearn.dfs;

import java.util.*;
import java.io.*;

class SequenceGuesser {
    static int n;
    static int f;
    static int[][] dp;
    static int[] b;
    static int[] p;
    static int[] ch;
    static boolean flag = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());
        dp = new int[n][n];
        b = new int[n];
        p = new int[n];
        ch = new int[n + 1];
        for(int i = 0; i < n; i++) {
            b[i] = combi(n - 1, i);
        }
        dfs(0, 0);
    }

    public static int combi(int n, int r) {
        if(dp[n][r]>0){
            return dp[n][r];

        }
        if(n==r||r==0) {
            return 1;
        }
        else {
            return dp[n][r] = combi(n - 1, r - 1) + combi(n - 1, r);
        }
    }

    public static void dfs(int L, int sum) {
        if(flag) {
            return;
        }
        if(L == n) {
            if(sum == f) {
                for(int x : p) {
                    System.out.print(x + " ");
                    flag = true;
                }
            }
        }
        else {
            for (int i = 1; i <= n; i++) {
                if (ch[i] == 0) {
                    ch[i] = 1;
                    p[L] = i;
                    dfs(L + 1, sum + (p[L] * b[L]));
                    ch[i] = 0;
                }
            }
        }

    }

}