package bj.dfs;

import java.util.*;
import java.io.*;

public class Solution_bj_14502_연구소_서울_20반_손홍서 {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int N, M;
    static int[][] map;

    static boolean[][] v;
    static ArrayList<int[]> virus;
    static ArrayList<int[]> safe;
    static int ans;
    static int[][] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        virus = new ArrayList<>();
        safe = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2) {
                    virus.add(new int[]{i,j});
                } else if(map[i][j] == 0) {
                    safe.add(new int[]{i, j});
                }
            }
        }

        arr = new int[3][2];
        ans = Integer.MIN_VALUE;
        combi(0, 0);
        System.out.println(ans);
        br.close();
    }

    static void combi(int s, int cnt) {
        if(cnt == 3) {
            ans = Math.max(ans, getCount());
            return;
        }
        for(int i = s; i < safe.size(); i++) {
            arr[cnt][0] = safe.get(i)[0];
            arr[cnt][1] = safe.get(i)[1];
            combi(i + 1, cnt + 1);
        }
    }

    static int getCount() {
        setWall(1);
        v = new boolean[N][M];
        for(int[] curr : virus) {
            spreadVirus(curr[0], curr[1]);
        }

        int temp = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!v[i][j] && map[i][j] == 0) {
                    temp++;
                }
            }
        }

        setWall(0);
        return temp;
    }

    static void spreadVirus(int r, int c) {
        int nr, nc;
        for(int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if(0 <= nr && nr < N && 0 <= nc && nc < M
                    && map[nr][nc] == 0 && !v[nr][nc]) {
                v[nr][nc] = true;
                spreadVirus(nr, nc);
            }
        }
    }

    static void setWall(int s) {
        for(int i = 0; i < 3; i++) {
            map[arr[i][0]][arr[i][1]] = s;
        }
    }
}
