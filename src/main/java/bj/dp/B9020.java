package bj.dp;

import java.util.*;
import java.io.*;

class B9020 {
    static final int MAX = 10_000;
    static boolean[] isPrime  = new boolean[MAX + 1];
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb  = new StringBuilder();
        initPrime();
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
            String ans = solution(n);
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    public static String solution(int n) {
        int num1 = n / 2;
        int num2 = n - num1;
        while(true) {
            if(isPrime[num1] && isPrime[num2]) {
                break;
            } else {
                num1--;
                num2 = n - num1;
            }
        }

        if(num2 > num1) {
            int temp =  num1;
            num1 = num2;
            num2 = temp;
        }
        return num2 + " " + num1;
    }

    public static void initPrime() {
        for(int i = 2; i <= MAX; i++) {
            isPrime[i] = true;
        }

        for(int i = 2; i <= Math.sqrt(MAX); i++) {
            if(isPrime[i]) {
                for (int j = i * i; j <= MAX; j += i) {
                    isPrime[j] = false;
                }
            }
        }
    }
}