package inflearn.dp;

import java.io.*;
import java.util.*;

class Coin {
    static int N;
    static int M;
    static int[] coins;
    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        coins = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());
        dp = new int[M + 1];
        solution();
        System.out.println(dp[M]);

    }

    private static void solution() {
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for(int i = 0; i < N; i++) {
            for(int j = coins[i]; j <= M; j++) {
                dp[j] = Math.min(dp[j], dp[j - coins[i]] + 1);
            }
        }
    }
}