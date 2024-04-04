package samsung;

import java.io.*;
import java.util.*;

public class Solution_모의_2105_디저트카페_서울_20반_손홍서 {
    static final int[] dr = {1, 1, -1, -1};
    static final int[] dc = {1, -1, -1, 1};
    static int[][] map;
    static boolean[] count;
    static int N;
    static int ans;

    static int[][] v;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            ans = 0;
            for(int i = 0; i < N - 2; i++) {
                for(int j = 1; j < N - 1; j++) {
                    v = new int[N][N];
                    v[i][j] = 1;
                    count = new boolean[101];
                    count[map[i][j]] = true;
                    solution(i, j, i, j, 0, 1);
                }
            }
            if(ans == 0 ) {
                ans = -1;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void solution(int sr, int sc, int r, int c, int d, int sum) {
        int nr = r + dr[d];
        int nc = c + dc[d];
        if(0 <= nr && nr < N && 0 <= nc && nc < N) {
            if(d == 3 && sr == nr && sc == nc) {
                ans = Math.max(ans, sum);
                return;
            }
            if(!count[map[nr][nc]]) {
                v[nr][nc] = 1;
                count[map[nr][nc]] = true;
                solution(sr, sc, nr, nc, d, sum + 1);
                count[map[nr][nc]] = false;
                v[nr][nc] = 0;
            }
        }

        if(d == 3) {
            return;
        }

        d++;
        nr = r + dr[d];
        nc = c + dc[d];
        if(0 <= nr && nr < N && 0 <= nc && nc < N) {
            if(d == 3 && sr == nr && sc == nc) {
                ans = Math.max(ans, sum);
                return;
            }
            if(!count[map[nr][nc]]) {
                v[nr][nc] = 1;
                count[map[nr][nc]] = true;
                solution(sr, sc, nr, nc, d, sum + 1);
                count[map[nr][nc]] = false;
                v[nr][nc] = 0;
            }
        }
    }
}

