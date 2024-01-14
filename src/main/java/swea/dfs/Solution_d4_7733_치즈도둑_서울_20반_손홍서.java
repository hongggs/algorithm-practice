package swea.dfs;

import java.io.*;
import java.util.*;

public class Solution_d4_7733_치즈도둑_서울_20반_손홍서 {
    final static int DAY = 100;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int[][] map;
    static boolean[][] visit;
    static int N;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());

            visit = new boolean[N][N];
            map = new int[N][N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int ans = 0;
            for(int d = 0; d <= DAY; d++) {
                int temp = 0;
                for(int i = 0; i < N; i++) {
                    Arrays.fill(visit[i], false);
                }
                for(int i = 0; i < N; i++) {
                    for(int j = 0; j < N; j++) {
                        if(!visit[i][j] && map[i][j] > d) {
                            visit[i][j] = true;
                            dfs(i, j, d);
                            temp++;
                        }
                    }
                }

                ans = Math.max(ans, temp);
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int r, int c, int d) {
        for(int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(0 <= nr && nr < N && 0 <= nc && nc < N && !visit[nr][nc] && map[nr][nc] > d) {
                visit[nr][nc] = true;
                dfs(nr, nc, d);
            }
        }
    }
}
