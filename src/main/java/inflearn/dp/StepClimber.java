package inflearn.dp;

import java.io.*;
import java.util.*;

class StepClimber {
  static int N;
  static int[] dp;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    dp = new int[N + 1];
    int ans = solution();
    System.out.println(ans);
  }

  public static int solution() {
    dp[1] = 1;
    dp[2] = 2;
    for(int i = 3; i <= N; i++) {
      dp[i] = dp[i - 2] + dp[i - 1];
    }
    return dp[N];
  }
}
