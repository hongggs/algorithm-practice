package bj.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

class B2667 {

  final static int[] dr = {-1, 1, 0, 0}; //상하좌우
  final static int[] dc = {0, 0, -1, 1};
  static int N;
  static int[][] map;
  static int cnt;
  static ArrayList<Integer> list;
  public static void main(String[] args) throws IOException{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringBuilder sb = new StringBuilder();

    N = Integer.parseInt(br.readLine());
    map = new int[N][N];
    list = new ArrayList<>();

    for(int i = 0; i < N; i++) {
      String s = br.readLine();
      for(int j = 0; j < N; j++) {
        map[i][j] = s.charAt(j) - '0';
      }
    }

    int totalBlock = 0;
    for(int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if(map[i][j] == 1) {
          totalBlock++;
          map[i][j] = 0;
          cnt = 1;
          dfs(i, j);
          list.add(cnt);
        }
      }
    }
    sb.append(totalBlock).append("\n");

    Collections.sort(list);
    for(int i = 0;  i < list.size(); i++) {
      sb.append(list.get(i)).append("\n");
    }
    System.out.println(sb);
  }

  static void dfs(int r, int c) {
    for(int i = 0; i < 4; i++) {
      int nr = r + dr[i];
      int nc = c + dc[i];
      if (isValid(nr, nc) && map[nr][nc] == 1) {
        map[nr][nc] = 0;
        dfs(nr, nc);
        cnt++;
      }
    }
  }

  static boolean isValid(int r, int c) {
    return 0 <= r && r < N && 0 <= c && c < N;
  }
}