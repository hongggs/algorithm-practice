package samsung;


import java.io.*;
import java.util.*;

public class Solution_모의_1949_등산로조성_서울_20반_손홍서 {
    static int N, K;
    static int[][] map;
    static boolean[][] v;
    static int ans;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            int max = 0;

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] > max) {
                        max = map[i][j];
                    }
                }
            }

            ArrayList<int[]> starts = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(max == map[i][j]) {
                        starts.add(new int[] {i, j});
                    }
                }
            }

            ans = 0;
            for(int[] start : starts) {
                v = new boolean[N][N];
                v[start[0]][start[1]] = true;
                solution(start[0], start[1], false, 1);
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static void solution(int r, int c, boolean flag, int sum) {
        for(int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(0 <= nr && nr < N && 0 <= nc && nc < N && !v[nr][nc]) {
                if(map[nr][nc] < map[r][c]) {
                    v[nr][nc] = true;
                    solution(nr, nc, flag, sum + 1);
                    v[nr][nc] = false;
                } else {
                    if(!flag) {
                        v[nr][nc] = true;
                        for(int k = K; k >= 1; k--) {
                            if(map[nr][nc] - k < map[r][c]) {
                                map[nr][nc] -= k;
                                solution(nr, nc, true, sum + 1);
                                map[nr][nc] += k;
                            }
                        }
                        v[nr][nc] = false;
                    }
                }
            }
        }

        ans = Math.max(ans, sum);
    }
}

