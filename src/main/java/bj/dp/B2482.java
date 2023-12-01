package bj.dp;

import java.io.*;
import java.util.*;

public class B2482 {
    static int N;
    static int K;
    static final int DIVIDE_NUM = 1_000_000_003;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        if(K == 1) {
            System.out.println(N);
            return;
        }

        if(K > N / 2) {
            System.out.println(0);
            return;
        }

        dp = new int[N + 1][K + 1];
        init();

        System.out.println(dp[N][K]);
        br.close();
    }

    public static void init() {
        for(int i = 1; i <= N; i++) {
            dp[i][1] = i;
        }
        for(int i = 4; i <= N; i++) {
            for(int j = 2; j <= K; j++) {
                dp[i][j] = (dp[i - 1][j] + dp[i - 2][j - 1]) % DIVIDE_NUM;
            }
        }
    }
}