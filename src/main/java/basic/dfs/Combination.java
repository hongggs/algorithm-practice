package basic.dfs;

import java.util.*;
import java.io.*;

class Combination {
    static int k;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        dfs(n, 1, 1, new int[k + 1]);

    }

    public static void dfs(int n, int s, int r, int[] comb) {
        if(r == k + 1) {
            for(int i = 1; i <= k; i++) {
                System.out.print(comb[i]+ " ");
            }
            System.out.println();
        } else {
            for(int i = s; i <= n; i++) {
                comb[r] = i;
                dfs(n, i + 1, r + 1, comb);
            }
        }
    }

}
