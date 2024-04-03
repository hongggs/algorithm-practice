package bj.bfs;


import java.util.*;
import java.io.*;

public class Solution_bj_16919_봄버맨2_서울_20반_손홍서 {
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static int R, C, N;
    static char[][] map;
    static boolean[][] v;
    static ArrayList<int[]> starts;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        String s;
        map = new char[R][C];
        v = new boolean[R][C];
        starts = new ArrayList<>();

        for(int i = 0; i < R; i++) {
            s = br.readLine();
            for(int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
                if(map[i][j] == 'O') {
                    starts.add(new int[] {i, j});
                }
            }
        }

        /**
         * N이 1이 들어오면 그대로 출력
         * 전체 폭찬이 있을때 N%2==0
         * 초기 폭탄 폭발 N%4==3
         * 나중 폭탄 폭발 N%4==1
         */
        if(N == 1) {
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }
        else if(N % 2 == 0) {
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    sb.append("O");
                }
                sb.append("\n");
            }
        } else if(N % 4 == 1) {
            bomb(starts);
            List<int[]> list = new ArrayList<>();
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    if(map[i][j] == 'O'){
                        list.add(new int[]{i, j});
                    }
                }
            }
            bomb(list);
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        } else if(N % 4 == 3) {
            bomb(starts);
            for(int i = 0; i < R; i++) {
                for(int j = 0; j < C; j++) {
                    sb.append(map[i][j]);
                }
                sb.append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }

    static void bomb(List<int[]> list) {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                map[i][j] = 'O';
            }
        }

        for(int[] arr : list) {
            map[arr[0]][arr[1]] = '.';
            for(int i = 0; i < dr.length; i++) {
                int nr = arr[0] + dr[i];
                int nc = arr[1] + dc[i];
                if(0 <= nr && nr < R && 0 <= nc && nc < C) {
                    map[nr][nc] = '.';
                }
            }
        }
    }
}


