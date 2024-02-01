package bj.dfs;

import java.util.*;
import java.io.*;

public class B12891{
    static int S, P;
    static int[] dna;
    static int[] cnt;
    static int[] dp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        dna = new int[S];
        int index = 0;
        for(char c : br.readLine().toCharArray()) {
            if(c == 'A') {
                dna[index] = 0;
            } else if (c == 'C') {
                dna[index] = 1;
            } else if(c == 'G') {
                dna[index] = 2;
            } else if(c == 'T') {
                dna[index] = 3;
            }
            index++;
        }

        st = new StringTokenizer(br.readLine());
        cnt = new int[4];
        for(int i = 0; i < cnt.length; i++) {
            cnt[i] = Integer.parseInt(st.nextToken());
        }

        int ans = 0;

        dp = new int[4];
        for(int i = 0; i < P; i++) {
            dp[dna[i]]++;
        }

        if(isValidCode()) {
            ans++;
        }

        for(int i = P; i < S; i++) {
            dp[dna[i - P]]--;
            dp[dna[i]]++;

            if(isValidCode()) {
                ans++;
            }
        }

        System.out.println(ans);
    }

    static boolean isValidCode() {
        for(int j = 0; j < dp.length; j++) {
            if(dp[j] < cnt[j]) {
                return false;
            }
        }
        return true;
    }
}