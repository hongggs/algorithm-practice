package inflearn.dp;

import java.io.*;
import java.util.*;

class Knapsack {
    static int N;
    static int M;
    static int[] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dp = new int[M + 1];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int score = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            for(int j = M; j >= time; j--) {
                dp[j] = Math.max(dp[j], dp[j - time] + score);
            }
        }
        System.out.println(dp[M]);

    }
}