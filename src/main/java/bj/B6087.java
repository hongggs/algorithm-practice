package bj;

import java.io.*;
import java.util.*;

public class B6087 {
    static int W,H;
    static char[][] map;
    static int[][][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static Point start;
    static Point end;
    static class Point implements Comparable<Point>{
        int r, c, d, sum;

        public Point(int r, int c, int d, int sum) {
            this.r = r;
            this.c = c;
            this.d = d;
            this.sum = sum;
        }

        public int compareTo(Point p) {
            return sum - p.sum;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        visited = new int[4][H][W];

        boolean flag = true;
        for(int i = 0; i < H; i++) {
            String str = br.readLine();
            for(int j = 0; j < W; j++) {
                map[i][j] = str.charAt(j);
                if(map[i][j] == 'C') {
                    if(flag) {
                        start = new Point(i, j, -5, -1);
                        flag = false;
                    } else {
                        end = new Point(i, j, -5, -1);
                    }
                }
            }
        }

        bw.write(solution() + "");
        br.close();
        bw.close();
    }

    public static int solution() {
        int ans = Integer.MAX_VALUE;
        PriorityQueue<Point> q = new PriorityQueue<>();

        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < H; j++) {
                Arrays.fill(visited[i][j], W * H);
            }
        }
        q.offer(start);

        while(!q.isEmpty()) {
            Point p = q.poll();

            if (p.r == end.r && p.c == end.c) {
                ans = Math.min(ans, p.sum);
            }

            for(int i = 0; i < 4; i++) {
                int nr = p.r + dr[i];
                int nc = p.c + dc[i];
                int nextSum = (p.d == i) ? p.sum : p.sum + 1;
                if (isValid(nr, nc) && Math.abs(p.d - i) != 2) {
                    if (visited[i][nr][nc] > nextSum) {
                        q.offer(new Point(nr, nc, i, nextSum));
                        visited[i][nr][nc] = nextSum;
                    }
                }
            }
        }
        return ans;
    }

    private static boolean isValid(int r, int c) {
        return 0 <= r && r < H && 0 <= c && c < W && map[r][c] != '*';
    }
}
