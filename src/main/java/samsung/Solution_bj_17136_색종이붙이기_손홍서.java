package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//https://www.acmicpc.net/problem/17136
public class Solution_bj_17136_색종이붙이기_손홍서 {
    static final int[] dr = {-1, 0, 1, 0}; // 상좌하우
    static final int[] dc = {0, -1, 0, 1};
    static int N, M;
    static int[][] map;
    static int totalCnt, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        if(d % 2 == 1) {
            d = (d + 2) % 4;
        }

        totalCnt = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 0) {
                    totalCnt++;
                }
            }
        }

        ans = 0;
        System.out.println(ans);
    }
}
