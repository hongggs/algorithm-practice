package inflearn.dp;

import java.io.*;
import java.util.*;

class Brickss {
  static int N;
  static Brick[] arr;
  static class Brick implements Comparable<Brick> {
    public int s, h, w;

    Brick(int s, int h, int w) {
      this.s = s;
      this.h = h;
      this.w = w;
    }

    public int compareTo(Brick b) {
      return b.s - this.s;
    }
  }
  static int[] dp;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    N = Integer.parseInt(br.readLine());
    dp = new int[N];
    arr = new Brick[N];

    StringTokenizer st;
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      int s = Integer.parseInt(st.nextToken());
      int h = Integer.parseInt(st.nextToken());
      int w = Integer.parseInt(st.nextToken());
      arr[i] = new Brick(s, h, w);
    }

    int ans = solution();
    System.out.println(ans);
  }

  public static int solution() {
    Arrays.sort(arr);
    dp[0] = arr[0].h;
    int ans = dp[0];
    for(int i = 1; i < N; i++) {
      int max_h = 0;
      for (int j = i - 1; j >= 0; j--) {
        if (arr[j].w >= arr[i].w && dp[j] > max_h) {
          max_h = dp[j];
        }
      }
      dp[i] = max_h + arr[i].h;
      ans = Math.max(ans, dp[i]);

    }
    return ans;
  }
}