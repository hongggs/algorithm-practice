package bj;

import java.util.*;
import java.io.*;

public class B2961 {
    static int N;
    static int[] bitter, sour;
    static int visit;
    static int ans = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        bitter = new int[N];
        sour = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sour[i] = Integer.parseInt(st.nextToken());
            bitter[i] = Integer.parseInt(st.nextToken());
        }

        subs(0, 1, 0);
        System.out.println(ans);
    }

    static void subs(int cnt, int s, int b) {
        if (cnt == N) {
            if (visit == 0) {
                return;
            }
            ans = Math.min(ans, Math.abs(s - b));
            return;
        }

        visit |= (1 << cnt);
        subs(cnt + 1, s * sour[cnt], b + bitter[cnt]);
        visit ^= (1 << cnt);
        subs(cnt + 1, s, b);
    }

}

