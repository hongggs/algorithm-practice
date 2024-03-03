package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 1. 선거구 나누기
 * 2. 해당 선거구가 이어져 있는지 검사
 */
public class Solution_bj_17471_게리맨더링_서울_20반_손홍서 {
    static int N;
    static int[] arr;
    static boolean[] ch;
    static int ans;
    static int[][] adjArr;
    static int[] parent;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        adjArr = new int[N + 1][N + 1];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            for(int j = 0; j < m; j++) {
                adjArr[i][Integer.parseInt(st.nextToken())] = 1;
            }
        }

        ans = Integer.MAX_VALUE;
        ch = new boolean[N + 1];
        parent = new int[N + 1];
        for(int i = 1; i <= N / 2; i++) {
            combi(1, 0, i);
        }

        if(ans == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(ans);

        }
        br.close();
    }

    static void combi(int s, int cnt, int M) {
        if(cnt == M) {
            if(isValid()) {
                int area1 = 0;
                int area2 = 0;
                for(int i = 1;  i <= N; i++) {
                    if(ch[i]) {
                        area1 += arr[i];
                    } else {
                        area2 += arr[i];
                    }
                }
                ans = Math.min(ans, Math.abs(area1 - area2));
            }
            return;
        }

        for(int i = s; i <= N; i++) {
            ch[i] = true;
            combi(i + 1, cnt  + 1, M);
            ch[i] = false;
        }
    }

    static boolean isValid() {
        initParent();
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                if(ch[i] == ch[j]) {
                    if(adjArr[i][j] == 1 || adjArr[j][i] == 1) {
                        union(i, j);
                    }
                }
            }
        }

        for(int i = 1; i < N; i++) {
            for(int j = i + 1; j <= N; j++) {
                if(ch[i] == ch[j]) {
                    if(find(i) != find(j)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    static void initParent() {
        for(int i = 1; i <= N; i++) {
            parent[i] = i;
        }
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);
        if(pa <= pb) {
            parent[pb] = pa;
        } else {
            parent[pa] = pb;
        }
    }

    static int find(int a) {
        if(parent[a] == a) {
            return a;
        }
        return parent[a] = find(parent[a]);
    }
}

