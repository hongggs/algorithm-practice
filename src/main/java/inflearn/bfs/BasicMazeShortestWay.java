package inflearn.bfs;

import java.util.*;
import java.io.*;

class BasicMazeShortestWay {
    static int[] dr = {-1, 1, 0, 0}; //상하좌우
    static int[] dc = {0, 0, -1, 1};
    static int[][] map = new int [7 + 1][7 + 1];
    static int[][] board = new int [7 + 1][7 + 1];

    static class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 1; i <= 7; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= 7; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solution(1, 1);
        if(board[7][7] == 0) {
            System.out.println("-1");
        } else {
            System.out.println(board[7][7]);
        }
    }

    public static void solution(int sr, int sc) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(sr, sc));

        while(!q.isEmpty()) {
            Point p = q.poll();

            if(p.r == 7 && p.c == 7) {
                return;
            }

            for(int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                if(isValid(nr, nc) && map[nr][nc] == 0) {
                    map[nr][nc] = 1;
                    q.offer(new Point(nr, nc));
                    board[nr][nc] = board[p.r][p.c] + 1; ;
                }
            }

        }
    }

    public static boolean isValid(int r, int c) {
        return 1 <= r && r <= 7 && 1 <= c && c <= 7;
    }

}