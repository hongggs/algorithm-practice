package basic.dfs;

import java.util.*;
import java.io.*;
public class Permutation {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        dfs(new int[m],0, n, m);
    }

    public static void dfs(int[] pick, int currentIndex, int n, int m) {
        if(currentIndex == m) {
            for(int x : pick) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            for(int i = 1; i <= n; i++) {
                pick[currentIndex] = i;
                dfs(pick, currentIndex + 1, n, m);
            }
        }
    }
}
