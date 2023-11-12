package inflearn.dfs;

import java.util.*;
import java.io.*;
public class LimitMaxSubset {

    static int ans = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        dfs(0, 0, c, n, arr);
        System.out.println(ans);
    }

    public static void dfs(int start, int sum, int c, int n, int[] arr) {
        if(sum > c) {
            return;
        }

        if(start >= n) {
            if(sum > ans) {
                ans = sum;
            }
            return;
        }

        dfs(start + 1, sum + arr[start], c, n, arr);
        dfs(start + 1, sum, c, n, arr);
    }

}
