package samsung;

import java.io.*;
import java.util.*;

public class Solution_모의_1953_탈주범검거_서울_20반_손홍서 {
    static final int[] dr = {-1, 1, 0, 0};// 상하좌우
    static final int[] dc = {0, 0, -1, 1};
    static final int[][] direction = {
            {},
            {0, 1, 2, 3},
            {0, 1},
            {2, 3},
            {0, 3},
            {1, 3},
            {1, 2},
            {0, 2}

    };
    static int N, M, R, C, L;
    static int[][] map;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if(L == 1) {
                sb.append("1\n");
            } else {
                sb.append(solution()).append("\n");
            }

        }
        System.out.println(sb);
    }

    static int solution() {
        int ans = 1;
        boolean[][] v = new boolean[N][M];
        Queue<int[]> q = new ArrayDeque<>();

        v[R][C] = true;
        q.offer(new int[]{R, C, 1});


        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            int time = now[2];

            for(int d : direction[map[r][c]]) {
                int nr = r + dr[d];
                int nc = c + dc[d];
                if(0 <= nr && nr < N && 0 <= nc && nc < M && !v[nr][nc] && map[nr][nc] != 0) {
                    if(d % 2 == 0) {
                        d = d + 1;
                    } else {
                        d = d - 1;
                    }

                    for(int nd : direction[map[nr][nc]]) {
                        if(d == nd) {
                            ans++;
                            v[nr][nc] = true;
                            if(time + 1 >= L) {
                                continue;
                            }
                            q.offer(new int[]{nr, nc, time + 1});
                            break;
                        }
                    }
                }
            }
        }

        return ans;
    }

}


