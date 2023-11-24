package bj.dp;

import java.io.*;
import java.util.*;

public class B7579 {
    static int N, M;
    static int[] mems;
    static int[] costs;

    static int[] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        mems = new int[N + 1];
        costs = new int[N + 1];
        dp = new int[10001];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            mems[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            costs[i] = Integer.parseInt(st.nextToken());
        }

        int ans = Integer.MAX_VALUE;
        for(int i = 1; i <= N; i++) {
            for(int j = 10000; j >= costs[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - costs[i]] + mems[i]);
                if(dp[j] >= M) {
                    ans = Math.min(ans, j);
                }
            }
        }

        bw.write(ans + "");
        br.close();
        bw.close();
    }
}