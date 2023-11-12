package bj.simulation;

import java.util.*;
import java.io.*;

class B3190 {
    static int[] dr = {-1,0,1,0}; //상우하좌
    static int[] dc = {0,1,0,-1}; //상우하좌

    static class Point {
        int r;
        int c;
        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int k = Integer.parseInt(br.readLine());

        int[][] map = new int[n + 1][n + 1];
        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 5;
        }

        int L = Integer.parseInt(br.readLine());
        int[] gameT = new int[L];
        char[] gameD = new char[L];
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            gameT[i] = Integer.parseInt(st.nextToken());
            gameD[i] = st.nextToken().charAt(0);
        }

        System.out.println(solution(map, gameT, gameD, L, n));
    }

    public static int solution(int[][] map, int[] gameT, char[] gameD, int L, int n) {
        int time = 0;

        int i = 0;

        Queue<Point> q = new LinkedList<>();
        int r = 1;
        int c = 1;

        map[r][c] = 1;
        q.offer(new Point(r,c));

        int d = 1;

        while(true) {
            // 1. 시간 재기
            time++;

            // 2. 뱀 이동
            r += dr[d];
            c += dc[d];

            //3 - 1. 범위 벗어나면 종료
            if(0 >= r || r > n || 0 >= c || c > n) {
                break;
            }

            // 3 - 2. 뱀 몸 만나면 종료
            if(map[r][c] == 1) {
                return time;
            }

            // 4. 사과 먹지 않았을때는 꼬리를 움직여야 하므로 queue에서 poll함
            if(map[r][c] != 5) {
                Point p = q.poll();
                map[p.r][p.c] = 0;
            }

            // 5. queue에 현재 head 위치 추가
            q.offer(new Point(r,c));
            map[r][c] = 1;

            // 6. 방향 변경
            if(i < L && gameT[i] == time) { //HashMap을 사용하면 더 좋았을듯
                if(gameD[i] == 'L') {
                    d -= 1;
                    if(d < 0) {
                        d = 3;
                    }
                } else if(gameD[i]=='D') {
                    d += 1;
                    if(d > 3) {
                        d = 0;
                    }
                }
                i++;
            }

        }
        return time;
    }
}
