package swea.dfs;

import java.io.*;
import java.util.*;

public class Solution_d3_5215_햄버거다이어트_서울_20반_손홍서 {
    static int[] cals;
    static int[] scores;
    static int N;
    static int L;
    static int ans = 0;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            StringTokenizer st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            ans = 0;

            cals = new int[N];
            scores = new int[N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                scores[i] = Integer.parseInt(st.nextToken());
                cals[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0, 0);

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    public static void dfs(int start, int sumCal, int sumScore) {
        if(sumCal > L) {
            return;
        }

        for(int i = start; i < N; i++) {
            if(sumCal + cals[i] <= L) {
                ans = Math.max(ans, sumScore + scores[i]);
            }

            dfs(i + 1, sumCal + cals[i], sumScore + scores[i]);
        }
    }
}
