package basic.lis;

import java.io.*;
import java.util.*;

public class LIS_DP2 { // O(LogN)
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
    int LIS = 0;
    for(int i = 1; i <= N; i++) {
      int idx = binarySearch(arr[i], 0, LIS, LIS + 1);
      if(idx == -1) {
        dp[LIS++] = arr[i];
      } else {
        dp[idx] = arr[i];
      }
    }
    return LIS;
  }

  private static int binarySearch(int num, int start, int end, int size) {
    int res = 0;
    while(start <= end) {
      int mid = (start + end) / 2;
      if(num <= dp[mid]) {
        res = mid;
        end = mid - 1;
      } else {
        start = mid + 1;
      }
    }
    if(start == size) {
      return -1;
    } else {
      return res;
    }
  }
}
