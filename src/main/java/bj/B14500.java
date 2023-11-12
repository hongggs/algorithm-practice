package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B14500 {
    static int n, m;
    static int[][] map;
    static boolean[][] visit;
    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visit = new boolean[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                visit[i][j] = true;
                solution(i, j, 1, map[i][j]);
                visit[i][j] = false;
            }
        }

        System.out.println(max);

    }

    static void solution(int y, int x, int cnt, int sum) {
        if(cnt == 4) {
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if(isValid(ny, nx)) {
                if(cnt == 2) {
                    visit[ny][nx] = true;
                    solution(y, x, cnt + 1, sum + map[ny][nx]);
                    visit[ny][nx] = false;
                }
                visit[ny][nx] = true;
                solution(ny, nx, cnt + 1, sum + map[ny][nx]);
                visit[ny][nx] = false;
            }
        }
    }

    static boolean isValid(int y, int x) {
        return 0 <= y && y < n && 0 <= x && x < m && !visit[y][x];
    }
}
