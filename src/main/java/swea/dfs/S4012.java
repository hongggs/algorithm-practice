package swea.dfs;

import java.io.*;
import java.util.*;

public class S4012 {

    static int N;
    static int[][] map;
    static boolean[] visit;
    static int ans;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_모의_4012.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            visit = new boolean[N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            ans = Integer.MAX_VALUE;
            combi(0, 0);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void combi(int s, int cnt) {
        if(cnt == N/2) {
            ans = Math.min(ans, getDiff());
            return;
        }
        if(s >= N/2 && cnt == 1) {
            return;
        }
        for(int i = s; i < N; i++) {
            visit[i] = true;
            combi(i + 1, cnt + 1);
            visit[i] = false;
        }
    }

    static int getDiff() {
        int a = 0;
        int b = 0;
        for(int i = 0; i < N - 1; i++) {
            for(int j = i + 1; j < N; j++) {
                if(visit[i] && visit[j]) {
                    a += (map[i][j] + map[j][i]);
                } else if(!visit[i] && !visit[j]) {
                    b += (map[i][j] + map[j][i]);
                }
            }
        }
        return Math.abs(a - b);
    }
}
