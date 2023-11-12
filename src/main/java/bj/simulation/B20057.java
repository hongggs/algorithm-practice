package bj.simulation;

import java.util.*;
import java.io.*;

class B20057 {
    static int N;

    // 좌하우상
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {-1, 0, 1, 0};

    // 모래의 이동
    static int[] tdr = {0, -1, 1, -1, 1, -2, 2, -1, 1, 0}; // 5, 10, 10, 7, 7, 2, 2, 1, 1, 알파
    static int[] tdc = {-2, -1, -1, 0, 0, 0, 0, 1, 1, -1}; // 5, 10, 10, 7, 7, 2, 2, 1, 1, 알파
    static int[] tp = {5, 10, 10, 7, 7, 2, 2, 1, 1, 0}; // 5, 10, 10, 7, 7, 2, 2, 1, 1, 알파

    static int[][] map;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        StringTokenizer st;
        for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(solution((N + 1) / 2, (N + 1) / 2));
    }

    // - 토네이도는 2번 같은 s로 불고 방향을 바꾼다.
    public static int solution(int r, int c) {

        int ans = 0;

        // "좌"로 가능 방향으로 시작
        int d = 0;
        // 현재 얼마나 움직였는지를 표시
        int pt = 0;
        // 방향을 바꾸기 전 움직여야하는 길이
        int step = 1;
        // 토네이도는 2번만 같은 길이로 움직이고 이후는 step에 +1 해야하므로 필요
        int cnt = 0;

        while(true) {

            // 탈출조건
            if(r == 1 && c == 1) {
                return ans;
            }

            // step 길이만큼 움직이면 바람의 방향을 바꾼다.
            if(step == pt) {
                pt = 0;
                d++;
                if(d >= 4) {
                    d = 0;
                }
                cnt++;

            }

            // 토네이도는 2번만 같은 길이로 움직이고 이후에는 +1한 길이로 움직인다. 이 과정을 계속 반복
            if(cnt == 2) {
                cnt = 0;
                step++;
            }

            // 토네이도 바람이 특정 방향으로 불어 y로 좌표 이동
            r = r + dr[d];
            c = c + dc[d];

            // 토네이도가 x에서 y로 이동하면, y의 모든 모래가 비율과 α가 적혀있는 칸으로 이동
            // 특정 비율에 바탕으로 바람에 따른 모래의 양 계산
            int sum = 0;
            for(int i = 0; i < tdr.length; i++) {
                int nr = 0;
                int nc = 0;
                if(d == 0) { // 좌
                    nr = r + tdr[i];
                    nc = c + tdc[i];
                } else if(d == 1) { // 하
                    nr = r - tdc[i];
                    nc = c + tdr[i];
                } else if(d == 2) { //  우
                    nr = r - tdr[i];
                    nc = c - tdc[i];
                } else if(d == 3) { // 상
                    nr = r + tdc[i];
                    nc = c + tdr[i];
                }
                // 알파를 위해 이동한 모래의 양을 다 더한다.
                sum +=  map[r][c] * tp[i] * 0.01;
                if(isInBoundary(nr, nc)) {
                    if(i == tdr.length - 1) { // 알파인 경우
                        map[nr][nc] += map[r][c] - sum;
                        map[r][c] = 0;
                    }
                    map[nr][nc] += map[r][c] * tp[i] * 0.01;
                } else { // 모래가 격자 밖으로 나가면 답에 더해준다.
                    if(i == tdr.length - 1) { // 알파인 경우
                        ans += map[r][c] - sum;
                        map[r][c] = 0;
                    } else {
                        ans += map[r][c] *tp[i] * 0.01;
                    }
                }
            }
            pt++;
        }
    }

    public static boolean isInBoundary(int r, int c) {
        return 1 <= r && r <= N && 1 <= c && c <= N;
    }
}
