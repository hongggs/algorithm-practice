package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution_d4_키순서_손홍서 {
    static int N, M, ans;
    static ArrayList<Integer>[] adjList;
    public static void main(String[] args) throws Exception{
        BufferedReader br  = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());
            M = Integer.parseInt(br.readLine());

            adjList = new ArrayList[N];
            for(int i = 0; i < N; i++) {
                adjList[i] = new ArrayList<>();
            }

            int s, e;
            for(int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                s = Integer.parseInt(st.nextToken());
                e = Integer.parseInt(st.nextToken());
                adjList[s].add(e);
            }

            ans = 0;
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }


    static void solution() {
        int cnt;
        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            cnt = 0;
            q.clear();
            q.offer(i);
            while(!q.isEmpty()) {

            }
        }
    }
}
