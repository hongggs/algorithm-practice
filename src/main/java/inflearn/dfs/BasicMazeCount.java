package inflearn.dfs;

import java.util.*;
import java.io.*;

class BasicMazeCount {
    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1};
    static int ans = 0;
    static int[][] map = new int [7 + 1][7 + 1];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 1; i <= 7; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 7; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        map[1][1] = 1;
        solution(1, 1);
        System.out.println(ans);

    }

    public static void solution(int r, int c) {
        if(r == 7 && c == 7) {
            ans++;
            return;
        }

        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(isValid(nr, nc) && map[nr][nc] == 0) {
                map[nr][nc] = 1;
                solution(nr, nc);
                map[nr][nc] = 0;
            }
        }
    }

    public static boolean isValid(int r, int c) {
        return 1 <= r && r <= 7 && 1 <= c && c <= 7;
    }

}