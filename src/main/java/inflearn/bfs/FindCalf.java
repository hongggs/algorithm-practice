package inflearn.bfs;

import java.util.*;
import java.io.*;

public class FindCalf {

    static int[] dir = {1, -1, 5};
    static boolean[] visit = new boolean[10001];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s =  Integer.parseInt(st.nextToken());
        int e =  Integer.parseInt(st.nextToken());
        System.out.println(bfs(s, e));
    }

    static int bfs(int s, int e) {
        Queue<Integer> q = new ArrayDeque<>();
        visit[s] = true;
        q.offer(s);

        int ans = 0;
        while(!q.isEmpty()) {
            int len = q.size();
            for(int i = 0; i < len; i++) {
                int loc = q.poll();
                for(int j = 0; j < dir.length; j++) {
                    int ns = loc + dir[j];
                    if(ns == e) {
                        return ans + 1;
                    }
                    if(ns > 0 && ns <= 10000 && !visit[ns]) {
                        visit[ns] = true;
                        q.offer(ns);
                    }
                }
            }
            ans++;
        }

        return -1;
    }

}

