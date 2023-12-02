package bj.dp;

import java.io.*;
import java.util.*;

public class B10942 {
    static int N, M;
    static int[] nums;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        nums = new int[N + 1];
        dp = new int[N + 1][N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        solution_dp();
        M = Integer.parseInt(br.readLine());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
//            sb.append(solution(s, e)).append("\n");
            sb.append(dp[s][e]).append("\n");
        }
        System.out.println(sb);
    }

    public static int solution(int l, int e) {
        int ans = 1;
        while(l <= e) {
            if(nums[l] != nums[e]) {
                ans = 0;
                break;
            }
            l++;
            e--;
        }
        return ans;
    }

    public static void solution_dp() {
        for(int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        for(int i = 1; i <= N - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }

        for(int gap = 2; gap < N; gap++) {
            for(int start = 1; start <= N - gap; start++) {
                if (nums[start] == nums[start + gap] && dp[start + 1][start + gap - 1] == 1) {
                    dp[start][start + gap] = 1;
                }
            }
        }
    }

}
