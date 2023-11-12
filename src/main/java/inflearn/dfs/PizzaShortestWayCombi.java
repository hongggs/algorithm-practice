package inflearn.dfs;

import java.util.*;
import java.io.*;

class PizzaShortestWayCombi {
    static int N, M;
    static int[] arr;
    static ArrayList<Point> pizza = new ArrayList<>();
    static ArrayList<Point> home = new ArrayList<>();
    static int ans = Integer.MAX_VALUE;
    static class Point {
        int r, c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                int tmp = Integer.parseInt(st.nextToken());
                if(tmp == 2) {
                    pizza.add(new Point(i, j));
                }else if(tmp == 1) {
                    home.add(new Point(i, j));
                }
            }
        }
        combination(0, 0);
        System.out.println(ans);
    }

    public static void combination(int s, int L) {
        if(L == M) {
            ans = Math.min(ans, getShortestWay());
            return;
        }
        for(int i = s; i < pizza.size(); i++) {
            arr[L] = i;
            combination(i + 1, L + 1);
        }
    }

    public static int getShortestWay() {
        int sum = 0;
        for(int i = 0; i < home.size(); i++) {
            int minVal = Integer.MAX_VALUE;
            for(int j = 0; j < M; j++) {
                minVal = Math.min(minVal, getDist(home.get(i).r, home.get(i).c, pizza.get(arr[j]).r, pizza.get(arr[j]).c));
            }
            sum += minVal;
        }
        return sum;
    }

    public static int getDist(int r1, int c1, int r2, int c2){
        return Math.abs(r1 -r2) + Math.abs(c1 - c2);
    }
}