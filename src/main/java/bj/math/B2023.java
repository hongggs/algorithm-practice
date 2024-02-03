package bj.math;


import java.util.*;
import java.io.*;
public class B2023 {
    static int N;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        solution(2, 1);
        solution(3, 1);
        solution(5, 1);
        solution(7, 1);

        System.out.println(sb);
    }

    static void solution(int n, int cnt) {
        if(cnt == N) {
            sb.append(n).append("\n");
            return;
        }

        for(int i = 1; i < 10; i++) {
            if(isPrime(n * 10 + i)) {
                solution(n * 10 + i, cnt + 1);
            }
        }
    }

    static boolean isPrime(int n) {
        for(int i = 2; i * i <= n; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
