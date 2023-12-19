package bj.tsp;
//tsp 기
import java.io.*;
import java.util.*;

public class B2098 {
    static int N;
    static int statusFullBit;
    static int INF = 16_000_001;
    static int[][] W;
    static int[][] dp;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        statusFullBit = (1 << N) - 1; // 2 ^ n - 1
        W = new int[N + 1][N + 1];
        dp = new int[N + 1][statusFullBit + 1];
        for(int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(tsp(1, 1));
        br.close();
    }


    public static int tsp(int x, int check) {
        // 모든 도시를 방문한 경우
        if(check == statusFullBit) {
            if(W[x][1] == 0) { // 만약 돌아갈 경로가 없다면 INF리턴
                return INF;
            } else { // 다시 시작지점으로 돌아감
                return W[x][1];
            }
        }

        if(dp[x][check] != -1) { //  방문했던 곳이라면 이전 값 리턴
            return dp[x][check];
        }

        dp[x][check] = INF;

        for(int i = 1; i <= N; i++) {
            // 방문 안한 도시들 집합에 포함
            int next = check | (1 << (i - 1));

            // 경로가 없거나 아직 방문한 도시만 가는 경우
            if(W[x][i] == 0 || (check & (1 << (i - 1))) != 0) {
                continue;
            }
            // x에서 다음 도시 i를 방문을 했다 치고, i에서 아직 방문하지 않은 다른 도시 집합들을 방문하기(dfs)
            dp[x][check] = Math.min(dp[x][check], tsp(i, next) + W[x][i]);
        }
        return dp[x][check];
    }
}
