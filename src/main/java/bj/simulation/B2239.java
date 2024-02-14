package bj.simulation;

import java.util.*;
import java.io.*;
public class B2239 { //Solution_bj_2239_스도쿠_서울_20반_손홍서
    static int N = 9;
    static int[][] map;
    static int total;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        map = new int[N][N];

        String s;
        total = 0;
        for(int i = 0; i < N; i++) {
            s = br.readLine();
            for(int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j) - '0';
                if(map[i][j] > 0) {
                    total++;
                }
            }
        }

        flag: for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] == 0) {
                    solution(i, j);
                    break flag;
                }
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static boolean solution(int r, int c) {
        ArrayList<Integer> possibleList = check(r, c);

        if(total == 81) {
            return true;
        }

        if(possibleList.isEmpty()) {
            return false;
        }

        flag: for(int a = 0; a < possibleList.size(); a++) {
            map[r][c] = possibleList.get(a);
            total++;
            for(int i = r; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(map[i][j] > 0) {
                        continue;
                    }
                    if(!solution(i, j)) {
                        map[r][c] = 0;
                        total--;
                        continue flag;
                    }
                }
            }
            return true;
        }

        return false;
    }

    static ArrayList<Integer> check(int r, int c) {
        boolean[] v = new boolean[N + 1];
        for(int i = 0; i < N; i++) {
            v[map[r][i]] = true;
            v[map[i][c]] = true;
        }
        for(int i = r - (r % 3); i < r + (3 - (r % 3)); i++) {
            for(int j = c - (c % 3); j < c + (3 - (c % 3)); j++) {
                v[map[i][j]] = true;
            }
        }

        ArrayList<Integer> result = new ArrayList<>();
        for(int i = 1; i <= N; i++) {
            if(!v[i]) {
                result.add(i);
            }
        }
        return result;
    }
}


