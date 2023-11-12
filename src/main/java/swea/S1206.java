package swea;

import java.io.*;
import java.util.*;

public class S1206 {//View
  static int[] buildings;
  static int n;
  public static void main(String[] args) throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    int T = 10;

    StringTokenizer st;
    for(int t = 1; t <= T; t++) {
      n =  Integer.parseInt(br.readLine());
      buildings = new int[n];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
        buildings[i] = Integer.parseInt(st.nextToken());
      }
      int ans = solution();
      sb.append("#").append(t).append(" ").append(ans).append("\n");
    }
    System.out.println(sb);
  }

  static int solution() {
    int ans = 0;
    int[] d = {-2, -1, 1, 2};
    for(int i = 0; i < n; i++) {
      int h = buildings[i];
      int max = -1;
      for(int j = 0; j < 4; j++) {
        int ni = i + d[j];
        if (isValid(ni)) {
          if(h <= buildings[ni]) {
            max = -1;
            break;
          } else {
            max = Math.max(max, buildings[ni]);
          }
        }
      }
      if(max >= 0) {
        ans += (h - max);
      }
    }
    return ans;
  }

  static boolean isValid(int x) {
    return 0 <= x && x < n;
  }
}
