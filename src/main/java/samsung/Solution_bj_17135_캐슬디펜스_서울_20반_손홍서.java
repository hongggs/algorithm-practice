package samsung;

import java.util.*;
import java.io.*;

public class Solution_bj_17135_캐슬디펜스_서울_20반_손홍서 {

    static final int[] dr = {0, -1, 1, 0};
    static final int[] dc = {-1, 0, 0, 1};
    static int N, M, D;
    static int[][] map, origin;
    static int[] archers;
    static int ans, cnt;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        origin = new int[N][M];
        map = new int[N][M];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                origin[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = origin[i][j];
            }
        }

        archers = new int[3];
        ans = 0;
        combi(0, 0);
        System.out.println(ans);
    }
    static void combi(int s, int curr) {
        if(curr == 3) {
            cnt = 0;
            bfs();
            ans = Math.max(ans, cnt);
            return;
        }
        for(int i = s; i < M; i++) {
            archers[curr] = i;
            combi(i + 1, curr + 1);
        }
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        ArrayList<int[]> deaths = new ArrayList<>();
        int[] curr;
        int r, c, depth, nr, nc, order, v;
        for(int m = 0; m < N; m++) {
            q.clear();

            v = 0;

            q.offer(new int[]{N - m, archers[0], 1, 0});
            q.offer(new int[]{N - m, archers[1], 1, 1});
            q.offer(new int[]{N - m, archers[2], 1, 2});

            while(!q.isEmpty()) {
                if(v == ((1 << 3) - 1)) {
                    break;
                }

                curr = q.poll();
                r = curr[0];
                c = curr[1];
                depth = curr[2];
                order = curr[3];

                if((v & (1 << order)) != 0) {
                    continue;
                }
                if(depth > D) {
                    continue;
                }

                for(int i = 0; i < dr.length; i++) {
                    nr = r + dr[i];
                    nc = c + dc[i];
                    if(0 <= nr && nr < N - m && 0 <= nc && nc < M) {
                        if(map[nr][nc] == 0) {
                            q.offer(new int[] {nr, nc, depth + 1, order});
                        } else if(map[nr][nc] == 1) {
                            deaths.add(new int[] {nr, nc});
                            v |= (1 << order);
                            break;
                        }
                    }
                }
            }

            //죽이기
            for(int[] d: deaths) {
                if(map[d[0]][d[1]] == 1) {
                    map[d[0]][d[1]] = 0;
                    cnt++;
                }
            }
        }

        //다시 원상복구
        for(int[] d: deaths) {
            if(map[d[0]][d[1]] == 0) {
                map[d[0]][d[1]] = 1;
            }
        }
    }
}
