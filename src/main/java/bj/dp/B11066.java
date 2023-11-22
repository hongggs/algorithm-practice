package bj.dp;

import java.io.*;
import java.util.*;

public class B11066 {
    static int N;
    static int[] novel;
    static int[] sum;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            novel = new int[N + 1];
            dp = new int[N + 1][N + 1];
            sum = new int[N + 1];

            for(int i = 1; i <= N; i++) {
                novel[i] = Integer.parseInt(st.nextToken());
                sum[i] = sum[i - 1] + novel[i];
            }

            for(int n = 1; n <= N; n++) {
                for (int from = 1; from + n <= N; from++) {
                    int to = from + n;
                    dp[from][to] = Integer.MAX_VALUE;
                    for (int divide = from; divide < to; divide++) {
                        dp[from][to] = Math.min(dp[from][to],
                                dp[from][divide] + dp[divide + 1][to] + sum[to] - sum[from - 1]);
                    }
                }
            }
            int ans = dp[1][N];
            sb.append(ans).append("\n");

        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}