package inflearn.dfs;

import java.util.*;
import java.io.*;
public class Subset {
    static int n;
    static boolean[] visit;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n =  Integer.parseInt(br.readLine());
        visit = new boolean[n + 1];
        dfs(1);
    }

    public static void dfs(int L) {
        if(L == n + 1) {
            for(int i = 1; i <= n; i++) {
                if(visit[i]) {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
            return;
        }
        visit[L] = true;
        dfs(L + 1);
        visit[L] = false;
        dfs(L + 1);
    }
}
