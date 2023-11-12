package basic.lis;

import java.util.*;
import java.io.*;

public class LIS_DP1 { // O(N^2)
  static int N;
  static int[] arr;
  static int[] dp;
  public static void main(String args[]) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());

    arr = new int[N + 1];
    dp = new int[N + 1];
    StringTokenizer st = new StringTokenizer(br.readLine());
    for(int i = 1; i <= N; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }
    int ans = solution();

    System.out.println(ans);
  }

  public static int solution() {
    int ans = 0;
    for(int i = 1; i <= N; i++) {
      int num = arr[i];
      dp[i] = 1;
      for(int j = 1; j < i; j++) {
        if(num > arr[j]) {
          dp[i] = Math.max(dp[i], dp[j] + 1);
        }
      }
      ans = Math.max(ans, dp[i]);
    }
    return ans;
  }
}
