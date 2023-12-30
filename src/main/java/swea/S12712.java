package swea;

import java.util.*;
import java.io.*;

class S12712 {
    static int N, M;
    static int[][] map;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new int[N + 1][N + 1];
            for(int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int ans = solution();
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static int solution() {
        int max = 0;
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                int sum10 = getSum10(i, j);

                if(sum10 > max) {
                    max = sum10;
                }

                int sumX = getSumX(i, j);

                if(sumX > max) {
                    max = sumX;
                }
            }
        }
        return max;

    }

    public static int getSumX(int r, int c) {
        int sum = map[r][c];
        for(int i = 1; i < M; i++) {
            if (isValid(r - i, c - i)) {
                sum += map[r - i][c - i];
            }
            if(isValid(r + i, c + i)) {
                sum += map[r + i][c + i];
            }
            if(isValid(r + i, c - i)) {
                sum += map[r + i][c - i];
            }
            if(isValid(r - i, c + i)) {
                sum += map[r - i][c + i];
            }
        }
        return sum;
    }

    public static int getSum10(int r, int c) {
        int sum = map[r][c];
        for(int i = 1; i < M; i++) {
            if (isValid(r - i, c)) {
                sum += map[r - i][c];
            }
            if(isValid(r + i, c)) {
                sum += map[r + i][c];
            }
            if(isValid(r, c - i)) {
                sum += map[r][c - i];
            }
            if(isValid(r, c + i)) {
                sum += map[r][c + i];
            }
        }
        return sum;
    }

    public static boolean isValid(int r, int c) {
        return 1 <= r && r <= N && 1 <= c && c <= N;
    }
}
