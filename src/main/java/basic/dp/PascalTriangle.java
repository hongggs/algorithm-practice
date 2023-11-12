package basic.dp;

import java.io.*;
import java.util.*;
public class PascalTriangle {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        System.out.println(solution(N, K));
    }

    private static int solution(int N, int K) {
        int[][] dp = new int[N + 1][N + 1];
        dp[1][1] = 1;
        if(N >= 2) {
            dp[2][1] = 1;
            dp[2][2] = 1;
        }

        for(int i = 3; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                if(i == j || j == 1) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }
        }
        return dp[N][K];
    }
}
