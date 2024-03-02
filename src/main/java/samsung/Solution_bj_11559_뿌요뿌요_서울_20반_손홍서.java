package samsung;

import java.util.*;
import java.io.*;
import org.w3c.dom.Node;

public class Solution_bj_11559_뿌요뿌요_서울_20반_손홍서 {
    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, -1, 0, 1};
    static char[][] map;
    static boolean[][] v;
    static int N = 12, M = 6;
    static ArrayList<int[]> list;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new char[N][M];
        for(int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int ans = 0;
        while(true) {
            boolean isFinished = true;
            v = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != '.') {
                        list = new ArrayList<>();
                        bfs(map[i][j], i, j);

                        if(list.size() >= 4) {
                            isFinished = false;
                            for(int k = 0; k < list.size(); k++) {
                                map[list.get(k)[0]][list.get(k)[1]] = '.';
                            }
                        }

                    }
                }
            }
            if(isFinished) {
                break;
            }
            ans++;
            down();
        }
        System.out.println(ans);

        br.close();
    }
    static void bfs(char now, int r, int c) {
        Queue<int[]> q = new ArrayDeque<>();
        list.add(new int[]{r, c});
        q.offer(new int[]{r, c});
        v[r][c] = true;

        while(!q.isEmpty()) {
            int[] cur = q.poll();
            r = cur[0];
            c = cur[1];

            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(0 <= nr && nr < N && 0 <= nc && nc < M && !v[nr][nc] && map[nr][nc] == now) {
                    v[nr][nc] = true;
                    list.add(new int[]{nr, nc});
                    q.offer(new int[]{nr, nc});
                }
            }
        }
    }

    static void down() {
        for(int j = 0; j < M; j++) {
            for(int i = N - 1; i >= 0; i--) {
                if(map[i][j] == '.') {
                    for(int k = i - 1; k >= 0; k--) {
                        if(map[k][j] != '.') {
                            map[i][j] = map[k][j];
                            map[k][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }
}

