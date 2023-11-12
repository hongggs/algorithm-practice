package bj.simulation;

import java.io.*;
import java.util.*;

class B17779 {
    static int N;
    static int[][] map;
    static int total = 0;
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                total += map[i][j];
            }
        }

        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                for(int d1 = 1; d1 < N; d1++) {
                    for(int d2 = 1; d2 < N; d2++) {
                        //1. 기준점 (x, y)와 경계의 길이 d1, d2를 정한다. (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
                        if (i + d1 + d2 > N || 1 > j - d1 || j + d2 > N) {
                            continue;
                        }
                        solution(i, j, d1, d2);
                    }
                }
            }
        }

        System.out.println(ans);
    }

    static void solution(int x, int y, int d1, int d2) {
        boolean[][] border = new boolean[N + 1][N + 1];

        // 2. 칸의 경계선을 그린다.
        /*
        1. (x, y), (x+1, y-1), ..., (x+d1, y-d1)
        4. (x+d2, y+d2), (x+d2+1, y+d2-1), ..., (x+d2+d1, y+d2-d1)
         */
        for (int i = 0; i <= d1; i++) {
            border[x + i][y - i] = true;
            border[x + d2 + i][y +d2 - i] = true;
        }

        /*
        2. (x, y), (x+1, y+1), ..., (x+d2, y+d2)
        3. (x+d1, y-d1), (x+d1+1, y-d1+1), ... (x+d1+d2, y-d1+d2)
         */
        for (int i = 0; i <= d2; i++) {
            border[x + i][y + i] = true;
            border[x + d1 + i][y - d1 + i] = true;
        }

        // 각 선거구의 인구를 구한다.
        int[] zone = new int[5];
        // 1번 선거구: 1 ≤ r < x+d1, 1 ≤ c ≤ y
        for (int i = 1; i < x + d1; i++) {
            for(int j = 1; j <= y; j++) {
                if(border[i][j]) {
                    break;
                }
                zone[0] += map[i][j];
            }
        }
        // 2번 선거구: 1 ≤ r ≤ x+d2, y < c ≤ N
        for(int i = 1; i <= x + d2; i++) {
            for(int j = N; j > y; j--) {
                if(border[i][j]) {
                    break;
                }
                zone[1] += map[i][j];
            }
        }

        // 3번 선거구: x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        for (int i = x + d1; i <= N; i++) {
            for(int j = 1; j < y - d1 +d2; j++) {
                if(border[i][j]) {
                    break;
                }
                zone[2] += map[i][j];
            }
        }

        // 4번 선거구: x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
        for(int i = x + d2 + 1; i <= N; i++) {
            for (int j = N; j >= y - d1 + d2; j--) {
                if (border[i][j]) {
                    break;
                }
                zone[3] += map[i][j];
            }
        }

        // 5번 선거구: 전체인구 - (1~4번 선거구 인구)
        zone[4] = total;
        for(int i = 0; i < 4; i++) {
            zone[4] -= zone[i];
        }

        Arrays.sort(zone);

        ans = Math.min(ans, zone[4] - zone[0]);
    }
}