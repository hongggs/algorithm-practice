package bj.dfs;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * queen을 n개 공격할 수 없게 놓는 문제
 * queen은 상하좌우대각선(8방) 움직일 수 있음
 */
public class Solution_bj_9663_nqueen {
    static int N;
    static int ans;
    static int[] a;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        a = new int[N];
        ans = 0;
        solution(0);
        System.out.println(ans);
    }

    static void solution(int cnt) {
        if(cnt == N) {
            ans++;
            return;
        }

        flag: for(int i = 0; i < N; i++) {
           for(int j = 0; j < cnt; j++) {
              if(i == a[j] || i == a[j] + (cnt - j) || i == a[j] - (cnt - j)) { // Math.abs(a[j]- i) == Math.abs(j - cnt); => 대각선위치
                  continue flag;
              }
           }
           a[cnt] = i;
           solution(cnt + 1);
        }
    }
}
