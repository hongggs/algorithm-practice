package bj.simulation;

import java.io.*;
import java.util.*;

public class B15686 {
    static int N, M;
    static int ans;
    static ArrayList<int[]> homes;
    static ArrayList<int[]> chickens;
    static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[M];
        homes = new ArrayList<>();
        chickens = new ArrayList<>();
        int input;
        for(int i = 0;  i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++) {
                input = Integer.parseInt(st.nextToken());
                if(input == 2) {
                    chickens.add(new int[]{i, j});
                } else if(input == 1) {
                    homes.add(new int[]{i, j});
                }
            }
        }

        ans = Integer.MAX_VALUE;
        combi(0, 0);

        System.out.println(ans);
    }

    static void combi(int cnt, int s) {
        if(cnt == M) {
            ans = Math.min(ans, getTotalDist());
            return;
        }

        for(int i = s; i < chickens.size(); i++) {
            arr[cnt] = i;
            combi(cnt + 1, i + 1);
        }
    }

    static int getTotalDist() {
        int[] dists = new int[homes.size()];
        Arrays.fill(dists, Integer.MAX_VALUE);

        int r, c;
        for(int i = 0; i < M; i++) {
            r = chickens.get(arr[i])[0];
            c = chickens.get(arr[i])[1];
            for(int j = 0; j < homes.size(); j++) {
                dists[j] = Math.min(dists[j], getDist(r, c, homes.get(j)[0], homes.get(j)[1]));
            }
        }
        int result = 0;
        for(int i = 0; i < homes.size(); i++) {
            result += dists[i];
        }


        return result;
    }
    static int getDist(int r1, int c1, int r2, int c2) {
        return Math.abs(r1 - r2) + Math.abs(c1 - c2);
    }
}
