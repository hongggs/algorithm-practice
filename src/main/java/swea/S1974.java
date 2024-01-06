package swea;

import java.util.*;
import java.io.*;

class S1974
{
    static int N = 9;
    static boolean[] visit;
    static int[][] map;
    public static void main(String args[]) throws Exception
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T;
        T=Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++)
        {
            sb.append("#").append(t).append(" ");

            map = new int[N+1][N+1];
            visit = new boolean[N+1];

            StringTokenizer st;
            for(int i = 1; i <= N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 1; j <= N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            sb.append(solution()).append("\n");
        }
        System.out.println(sb);
    }

    public static int solution() {
        for(int i = 1; i <= N; i++) {
            if(!width(i)) {
                return 0;
            }

            if(!height(i)) {
                return 0;
            }
        }

        for(int i = 1; i <= N; i+=3) {
            for(int j = 1; j <= N; j+=3) {
                if(!square(i, j)) {
                    return 0;
                }
            }
        }

        return 1;
    }

    public static boolean width(int r) {
        initVisit();
        for(int i = 1; i <= N; i++) {
            if(visit[map[r][i]]) {
                return false;
            }
            visit[map[r][i]] = true;
        }
        return true;
    }

    public static boolean height(int r) {
        initVisit();
        for(int i = 1; i <= N; i++) {
            if(visit[map[i][r]]) {
                return false;
            }
            visit[map[i][r]] = true;
        }
        return true;
    }

    public static boolean square(int r, int c) {
        initVisit();
        for(int i = r; i <= r+3-1; i++) {
            for(int j = c; j <= c+3-1; j++) {
                if(visit[map[i][j]]) {
                    return false;
                }
                visit[map[i][j]] = true;
            }
        }
        return true;
    }

    public static void initVisit() {
        for(int i = 1; i <= N; i++) {
            visit[i] = false;
        }
    }

}
