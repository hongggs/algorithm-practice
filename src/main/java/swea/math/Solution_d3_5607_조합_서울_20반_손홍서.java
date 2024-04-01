package swea.math;


import java.io.*;
import java.util.*;

public class Solution_d3_5607_조합_서울_20반_손홍서 {
    static int N, R;
    static int ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());

            sb.append(nCr(N, R, 1234567891)).append("\n");
        }
        System.out.println(sb);
    }

    static long nCr(int n, int r, int p) {
        if(r == 0) {
            return 1L;
        }

        long[] fac = new long[n + 1];
        fac[0] = 1;

        for(int i = 1; i <= n; i++) {
            fac[i] = fac[i - 1] * i % p;
        }

        return (fac[n]
                * power(fac[r], p - 2, p)
                % p * power(fac[n-r], p - 2, p)
                % p);
    }

    static long power(long x, long y, long p) {
        long res = 1L;
        x = x % p;
        while(y > 0) {
            if(y % 2 == 1) {
                res = (res * x) % p;
            }
            y = y >> 1;
            x = (x * x) % p;
        }
        return res;
    }
}

