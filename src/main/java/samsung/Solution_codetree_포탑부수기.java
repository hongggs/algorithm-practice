package samsung;

/**
 *
 * 1. 공격자 선정
 *     1)부서지지 않은 포탑 중 가장 약한 포탑 선택
 *         - 공격력 0은 이미 부서진 포탑
 *         - 2개이상이면, 가장 최근에 공격한 포탑
 *         - 2개이상이면, 각 포탑의 행과 열의 합이 가장 큰 포탑
 *         - 2개이상이면, 열값이 가장 큰 포탑
 *  2) 선택된 포탑 공격력 증가 : original + N + M
 * 2. 공격
 *  1)자신을 제외한 가장 강한 포탑 선택
 *      - 2개이상이면, 공격한지 가장 오래된 포탑
 *      - 2개이상이면, 행과 열의 합이 가장 작은 포탑
 *      - 2개이상이면 열 값이 가장 작은 포탑
 *  2)레이저공격
 *      a.경로정하기
 *              -상하좌우
 *          -부서진포탑(0) 위치는 못지나감
 *          - 반대편이랑 이어짐!!!!! n = 3, i = 2 -> i = 0 모듈러 연산
 *          - 최단경로(bfs)
 *          - 경로없으면 포탄 공격
 *          - 2개이상이면, 우하좌상 순서
 *      b.공격대상에 공격자의 공격력만큼 피해입히기
 *          - 공격대상을 제외한 레이저 경로에 있는 포탑도 공격 받음 (공격자 공격력/2)
 *  3) 포탄 공격
 *      a.공격대상에 공격자의 공격력만큼 피해입히기
 *          - 주위 8개방향(대각선까지)에 있는 포탑도 피해입히기 (공격자공격력 1/2) 자기 자신은 안받게 주의하자!
 *          - 추가피해 반대편이랑 이어짐
 * 3. 0이된 포탑들 부서짐
 * 4. 포탑 정비
 *         1)공격과 무관했던 포탑은 공격력이 1씩 올라갑니다.
 *             - 공격자도 아니고, 피해입은 포탑도 아님
 *
 */
import java.io.*;
import java.util.*;
public class Solution_codetree_포탑부수기 {
    static final int[] dr = {0, 1, 0, -1, -1, -1, 1, 1,}; //우하좌상
    static final int[] dc = {1, 0, -1, 0, 1, -1, 1, -1};
    static int N, M, K;
    static int[][][] map;
    static class Point {
        int r, c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Point [r=" + r + ", c=" + c + "]";
        }

    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M][2];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j][0] = Integer.parseInt(st.nextToken());
            }
        }
        solution();
        int ans = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                ans = Math.max(ans, map[i][j][0]);
            }
        }
        System.out.println(ans);

    }

    static void solution() {
        int t = 1;
        while(t <= K) {
            boolean[][] v = new boolean[N][M];
            // 1. 공격자 선정
            Point ap = getAttacker();
            // 2. 공격
            //1)자신을 제외한 가장 강한 포탑 선택
            Point cp = getCompetitor();
            if(ap.r == cp.r && ap.c == cp.c) {
                return;
            }
            v[ap.r][ap.c] = true;
            v[cp.r][cp.c] = true;
            map[ap.r][ap.c][0] += (N + M);
            map[ap.r][ap.c][1] = t;
            int damage = map[ap.r][ap.c][0];
            System.out.println(ap+","+ cp);

            // 2)레이저공격
            Node laserNode = doLaserAttack(new Node(ap.r, ap.c, null), cp);
            System.out.println(laserNode);
            if(laserNode != null) {
                map[laserNode.r][laserNode.c][0] = Math.max(map[laserNode.r][laserNode.c][0] - damage, 0); //laserNode == cp
                damage /= 2;
                laserNode = laserNode.prev;
                while(laserNode.prev != null) {
                    v[laserNode.r][laserNode.c] = true;
                    map[laserNode.r][laserNode.c][0] = Math.max(map[laserNode.r][laserNode.c][0] - damage, 0);
                    laserNode = laserNode.prev;
                }
            } else {
                map[cp.r][cp.c][0] = Math.max(map[cp.r][cp.c][0] - damage, 0);
                damage /= 2;
                for(int i = 0; i < 8; i++) {
                    int nr = validatePoint(cp.r + dr[i], N);
                    int nc = validatePoint(cp.c + dc[i], M);
                    if(!v[nr][nc] && map[nr][nc][0] > 0) {
                        v[nr][nc] = true;
                        map[nr][nc][0] = Math.max(map[nr][nc][0]- damage, 0);
                    }
                }
            }

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(!v[i][j] && map[i][j][0] > 0) {
                        map[i][j][0]++;
                    }
                }
            }
            print_map();

            t++;
        }


    }

    static class Node {
        int r, c;
        Node prev;
        public Node(int r, int c, Node prev) {
            this.r = r;
            this.c = c;
            this.prev = prev;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "r=" + r +
                    ", c=" + c +
//                    ", prev" + prev +
                    '}';
        }
    }
    static Node doLaserAttack(Node start, Point target) { //bfs
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        v[start.r][start.c] = true;
        q.offer(start);
        while(!q.isEmpty()) {
            Node now = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = validatePoint(now.r + dr[i], N);
                int nc = validatePoint(now.c + dc[i], M);

                if(!v[nr][nc] && map[nr][nc][0] > 0) {
                    v[nr][nc] = true;
                    if(nr == target.r && nc == target.c) {
                        return new Node(nr, nc, now);
                    }
                    q.offer(new Node(nr, nc, now));
                }
            }
        }

        return null;
    }

    static Point getAttacker() {
        Point ap = new Point(-1, -1);
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(0 < map[i][j][0] && map[i][j][0] < min) {
                    min = map[i][j][0];
                    ap.r = i;
                    ap.c = j;
                } else if(0 < map[i][j][0] && map[i][j][0] == min) {
                    if(map[ap.r][ap.c][1] < map[i][j][1]) {
                        min = map[i][j][0];
                        ap.r = i;
                        ap.c = j;
                    } else if (map[ap.r][ap.c][1] == map[i][j][1]) {
                        if(ap.r + ap.c < i + j) {
                            min = map[i][j][0];
                            ap.r = i;
                            ap.c = j;
                        } else if(ap.r + ap.c == i + j) {
                            if(ap.c < j) {
                                min = map[i][j][0];
                                ap.r = i;
                                ap.c = j;
                            }
                        }
                    }
                }
            }
        }

        return ap;
    }

    static Point getCompetitor() {
        Point cp = new Point(-1, -1);
        int max = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(0 < map[i][j][0] && map[i][j][0] > max) {
                    max = map[i][j][0];
                    cp.r = i;
                    cp.c = j;
                } else if(0 < map[i][j][0] && map[i][j][0] == max) {
                    if(map[cp.r][cp.c][1] > map[i][j][1]) {
                        max = map[i][j][0];
                        cp.r = i;
                        cp.c = j;
                    } else if (map[cp.r][cp.c][1] == map[i][j][1]) {
                        if(cp.r + cp.c > i + j) {
                            max = map[i][j][0];
                            cp.r = i;
                            cp.c = j;
                        } else if(cp.r + cp.c == i + j) {
                            if(cp.c > j) {
                                max = map[i][j][0];
                                cp.r = i;
                                cp.c = j;
                            }
                        }
                    }
                }
            }
        }
        return cp;
    }

    static int validatePoint(int x, int N) {
        if(x < 0) {
            return N - 1;
        } else if(x >= N) {
            return 0;
        }
        return x;
    }

    static void print_map() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                System.out.print(map[i][j][0] + " ");
            }
            System.out.println();
        }
    }
}
