package bj.dfs;

import java.io.*;
import java.util.*;

class B1012 {
  static int T;
  static int H;
  static int W;
  static int K;
  static int[][] map;
  static int[] dr = {-1, 1, 0, 0}; //상하좌우
  static int[] dc = {0, 0, -1, 1};
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();
    T = Integer.parseInt(br.readLine());

    StringTokenizer st;
    for(int i = 1; i <= T; i++) {
      st = new StringTokenizer(br.readLine());
      W = Integer.parseInt(st.nextToken());
      H = Integer.parseInt(st.nextToken());
      K = Integer.parseInt(st.nextToken());
      map = new int[H][W];

      for(int j = 0; j < K; j++) {
        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        map[r][c] = 1;
      }
      sb.append(solution()).append("\n");
    }

    System.out.println(sb);
  }

  public static int solution() {
    int ans = 0;
    for(int i = 0; i < H; i++) {
      for(int j = 0; j < W; j++) {
        if(map[i][j] == 1) {
          map[i][j] = 0;
          K--;
          dfs(i, j);
          ans++;
        }
        if(K == 0) {
          return ans;
        }
      }
    }
    return ans;
  }

  public static void dfs(int r, int c) {
    for(int i = 0; i < 4; i++) {
      int nr = r + dr[i];
      int nc = c + dc[i];
      if (isValid(nr, nc) && map[nr][nc] == 1) {
        map[nr][nc] = 0;
        K--;
        dfs(nr, nc);
      }
    }
  }

  public static boolean isValid(int r, int c) {
    return 0 <= r && r < H && 0 <= c && c < W;
  }
}