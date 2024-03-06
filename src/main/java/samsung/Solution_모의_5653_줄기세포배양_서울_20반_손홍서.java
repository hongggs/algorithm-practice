package samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.concurrent.ConcurrentHashMap;

public class Solution_모의_5653_줄기세포배양_서울_20반_손홍서 {
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(c, r);
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            Point other = (Point) obj;
            return c == other.c && r == other.r;
        }

        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }


    }

    static class Status {
        int startTime;
        int life;
        int end;

        public Status(int startTime, int life, int end) {
            this.startTime = startTime;
            this.life = life;
            this.end = end;
        }

        @Override
        public String toString() {
            return "Status [startTime=" + startTime + ", life=" + life + ", end=" + end + "]";
        }

    }

    static final int[] dr = {-1, 0, 1, 0};
    static final int[] dc = {0, 1, 0, -1};
    static int N, M, K;
    static int[][] map;
    static Map<Point, Status> cells;

    public static void main(String[] args) throws Exception {
//        System.setIn(new FileInputStream("res/input_모의_5653.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            map = new int[N][M];
            cells = new ConcurrentHashMap<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < M; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if (map[i][j] > 0) {
                        cells.put(new Point(i, j), new Status(map[i][j], map[i][j], 0));
                    }
                }
            }
            solution();

            int ans = 0;
            for (Point p : cells.keySet()) {
                if (cells.get(p).end == cells.get(p).life) {
                    continue;
                }
                ans++;
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void solution() {
        int time = 1;

        Queue<Point> q = new ArrayDeque<>();

        int nr, nc;
        while (time <= K) {
            for (Point p : cells.keySet()) {
                Status curr = cells.get(p);

                if (curr.end == curr.life) {
                    continue;
                }
                if (time - curr.startTime == 1) { //세포증식 시작
                    q.offer(p);
                    curr.end += curr.life;
                }

            }
            while (!q.isEmpty()) {
                Point p = q.poll();
                Point np;
                Status curr = cells.get(p);
                for (int i = 0; i < dr.length; i++) {
                    nr = p.r + dr[i];
                    nc = p.c + dc[i];
                    np = new Point(nr, nc);
                    cells.putIfAbsent(np, new Status(time + curr.life, curr.life, 0));
                }
            }
            time++;
        }
    }

}
