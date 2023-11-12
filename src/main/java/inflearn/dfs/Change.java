package inflearn.dfs;

import java.util.*;
import java.io.*;

public class Change {
    static int ans = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Integer[] arr = new Integer[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int m = Integer.parseInt(br.readLine());

        dfs(0, 0, arr, new boolean[n], n, m);
        System.out.println(ans);

    }

    public static void dfs(int cnt, int visitCnt, Integer[] arr, boolean[] visit, int n, int m) {
        if(visitCnt == n || m == 0) {
            ans = Math.min(ans, cnt);
            return;
        }
        for(int i = 0; i < n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                dfs(cnt + m / arr[i], visitCnt + 1, arr, visit, n, m % arr[i]);
                visit[i] = false;
            }
        }
    }
}
