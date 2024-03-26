package bj.dp;

import java.util.*;
import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

public class Solution_bj_7579_앱_서울_20반_손홍서 {
    static final int INF = Integer.MAX_VALUE;
    static class App implements Comparable<App>{
        int m, c;

        public App(int m) {
            this.m = m;
        }

        public int compareTo(App a) {
            if(a.c < c) {
                return 1;
            }
            return -1;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        App[] apps = new App[N];
        int[] mem = new int[10001];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            apps[i] = new App(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            apps[i].c =Integer.parseInt(st.nextToken());
        }

        int ans = INF;
        for(int i = 0; i < N; i++) {
            for(int c = 10000; c >= apps[i].c; c--) {
                mem[c] = Math.max(mem[c], mem[c - apps[i].c] +apps[i].m);
                if(mem[c] >= M) {
                    ans = Math.min(ans, c);
                }
            }
        }

        System.out.println(ans);
        br.close();
    }
}