package bj.lis;

import java.io.*;
import java.util.*;

class B7570 {
  static int N;
  static int[] dp;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    dp = new int[N + 1];

    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 1; i <= N; i++) {
      int k = Integer.parseInt(st.nextToken());
      dp[k] = dp[k - 1] + 1;
    }
    Arrays.sort(dp);
    System.out.println(N - dp[N]);
  }
}