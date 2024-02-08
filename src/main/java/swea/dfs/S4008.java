package swea.dfs;

import java.io.*;
import java.util.*;

public class S4008 {

    static int N, M;
    static int[] nums;
    static int[] operator = new int[4];
    static int max, min;
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("res/input_모의_4008.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            nums = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 4; i++) { // +, -, *, /
                operator[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
            dfs(0, nums[0]);
            sb.append(max - min).append("\n");
        }
        System.out.println(sb);
    }
    static void dfs(int cnt, int result) {
        if(cnt == N - 1) {
            max = Math.max(max, result);
            min = Math.min(min, result);
            return;
        }

        for(int i = 0; i < operator.length; i++) {
            if(operator[i] == 0) {
                continue;
            }
            operator[i]--;
            dfs(cnt + 1, calc(result, nums[cnt + 1], i));
            operator[i]++;
        }
    }

    static int calc(int a, int b, int op) {
        switch(op) {
            case 0:
                return a += b;
            case 1:
                return a -= b;
            case 2:
                return a *= b;
            case 3:
                return a /= b;
        }
        return 0;
    }

}
