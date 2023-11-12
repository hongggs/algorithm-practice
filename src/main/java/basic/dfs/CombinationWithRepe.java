package basic.dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CombinationWithRepe {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
             arr[i] = Integer.parseInt(st.nextToken());
        }
        dfs(new int[m],0, new boolean[n], arr, n, m);
    }

    public static void dfs(int[] pick, int currentIndex, boolean[] visit, int[] arr, int n, int m) {
        if(currentIndex == m) {
            for(int x : pick) {
                System.out.print(x + " ");
            }
            System.out.println();
        } else {
            for(int i = 0; i < n; i++) {
                if(visit[i]) {
                    continue;
                }
                pick[currentIndex] = arr[i];
                visit[i] = true;
                dfs(pick, currentIndex + 1, visit, arr, n, m);
                visit[i] = false;
            }
        }
    }
}
