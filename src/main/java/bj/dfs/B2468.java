package bj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2468 {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int N;
    static int[][] map;
    static boolean[][] visit;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        StringTokenizer st;

        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, map[i][j]);
            }
        }

        int ans = 0;
        for(int r = 0; r <= max; r++) {
            visit = new boolean[N][N];
            int cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] > r && !visit[i][j]) {
                        dfs(i, j, r);
                        cnt++;
                    }
                }
            }
            ans = Math.max(ans, cnt);
        }
        System.out.println(ans);
    }

    static void dfs(int r, int c, int l) {
        int nr, nc;
        for(int i = 0; i < dr.length; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(0 <= nr && nr < N && 0 <= nc && nc < N && map[r][c] > l && !visit[nr][nc]) {
                visit[nr][nc] = true;
                dfs(nr, nc, l);
            }
        }
    }
}
