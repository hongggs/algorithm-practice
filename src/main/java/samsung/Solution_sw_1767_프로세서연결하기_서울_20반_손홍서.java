package a0228.camp;

import java.util.*;
import java.io.*;

public class Solution_sw_1767_프로세서연결하기_서울_20반_손홍서 {
    static final int[] dr = {-1, 0, 1, 0};//상좌하우
    static final int[] dc = {0, -1, 0, 1};

    static int N;
    static int[][] map;

    static int M;
    static int[] arr;
    static int[] vd;

    static ArrayList<Processor> p;
    static final int INF = 999;

    static int maxCnt;
    static int minValue;
    static class Processor {
        int r, c;
        int[] dir;

        public Processor(int r, int c) {
            this.r = r;
            this.c = c;
            dir = new int[4];
        }

        @Override
        public String toString() {
            return "Processor [r=" + r + ", c=" + c + ", dir=" + Arrays.toString(dir) + "]";
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            N = Integer.parseInt(br.readLine());

            map = new int[N][N];
            p = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                    if(map[i][j] == 1) {
                        p.add(new Processor(i, j));
                    }
                }
            }

            initDist();

            M = p.size();
            arr = new int[M];
            vd = new int[M];

            maxCnt = 0;
            minValue = Integer.MAX_VALUE;
            combi(0, 0);
            sb.append(minValue).append("\n");
        }

        System.out.println(sb);
        br.close();
    }

    static void initDist() {
        int r, c, nr, nc;
        for(int i = 0; i < p.size(); i++) {
            r = p.get(i).r;
            c = p.get(i).c;
            if(r == 0 || r == N - 1 || c == 0 || c == N - 1) {
                Arrays.fill(p.get(i).dir, 0);
                continue;
            }
            for(int d = 0; d < 4; d++) {
                int step = 1;
                while(true) {
                    nr = r + (dr[d] * step);
                    nc = c + (dc[d] * step);
                    if(0 > nr || nr >= N || 0 > nc || nc >= N) {
                        p.get(i).dir[d] = step - 1;
                        break;
                    }

                    if(map[nr][nc] == 1) {
                        p.get(i).dir[d] = INF;
                        break;
                    }
                    step++;
                }
            }
        }
    }

    static void combi(int cnt, int currProcessor) {
        if(maxCnt > currProcessor + (M - cnt)) {
            return;
        }
        if(cnt == M) {
            int processorCnt = 0;
            int sum = 0;

            for(int i = 0; i < M; i++) {
                if(arr[i] == INF) {
                    continue;
                }
                processorCnt++;
                sum += arr[i];
            }

            if(processorCnt > maxCnt) {
                maxCnt = processorCnt;
                minValue = sum;
            } else if(processorCnt == maxCnt) {
                minValue = Math.min(minValue, sum);
            }
            return;
        }

        flag : for(int i = 0; i < 4; i++) {
            if (p.get(cnt).dir[i] == 0) {//상좌하우
                arr[cnt] = 0;
                vd[cnt] = -1;
                combi(cnt + 1, currProcessor + 1);
                break;
            }

            if (p.get(cnt).dir[i] == INF) {//상좌하우
                arr[cnt] = INF;
                vd[cnt] = -1;
                combi(cnt + 1, currProcessor);
            }

            //교차 검사
            for(int j = 0; j < cnt; j++) {
                if(vd[j] == -1) {
                    continue;
                }
                //상
                switch(vd[j]) {
                    case 0:
                        if(i == 0) { //상
                            if(p.get(j).c  != p.get(cnt).c) {
                                break;
                            }
                        } else if(i == 1) { //좌
                            if(p.get(cnt).r > p.get(j).r || p.get(cnt).c < p.get(j).c) {
                                break;
                            }
                        } else if(i == 2) { //하
                            if(p.get(j).c  != p.get(cnt).c || p.get(cnt).r > p.get(j).r) {
                                break;
                            }
                        } else if(i == 3) { //우
                            if(p.get(cnt).r > p.get(j).r || p.get(cnt).c > p.get(j).c) {
                                break;
                            }
                        }
                        continue flag;
                        //좌
                    case 1:
                        if(i == 0) { //상
                            if(p.get(cnt).c > p.get(j).c || p.get(cnt).r < p.get(j).r) {
                                break;
                            }
                        } else if(i == 1) { //좌
                            if(p.get(cnt).r != p.get(j).r) {
                                break;
                            }
                        } else if(i == 2) { //하
                            if(p.get(cnt).c > p.get(j).c || p.get(cnt).r > p.get(j).r) {
                                break;
                            }
                        } else if(i == 3) { //우
                            if(p.get(cnt).r != p.get(j).r || p.get(cnt).c > p.get(j).c) {
                                break;
                            }
                        }
                        continue flag;
                        //하
                    case 2:
                        if(i == 0) { //상
                            if(p.get(j).c != p.get(cnt).c || p.get(cnt).r < p.get(j).r) {
                                break;
                            }
                        } else if(i == 1) { //좌
                            if(p.get(cnt).r < p.get(j).r || p.get(cnt).c < p.get(j).c) {
                                break;
                            }
                        } else if(i == 2) { //하
                            if(p.get(j).c  != p.get(cnt).c) {
                                break;
                            }
                        } else if(i == 3) { //우
                            if(p.get(cnt).r < p.get(j).r || p.get(cnt).c > p.get(j).c) {
                                break;
                            }
                        }
                        continue flag;
                        //우
                    case 3:
                        if(i == 0) { //상
                            if(p.get(cnt).c < p.get(j).c || p.get(cnt).r < p.get(j).r) {
                                break;
                            }
                        } else if(i == 1) { //좌
                            if(p.get(cnt).r != p.get(j).r || p.get(cnt).c < p.get(j).c) {
                                break;
                            }
                        } else if(i == 2) { //하
                            if(p.get(cnt).c < p.get(j).c || p.get(cnt).r > p.get(j).r) {
                                break;
                            }
                        } else if(i == 3) { //우
                            if(p.get(cnt).r != p.get(j).r) {
                                break;
                            }
                        }
                        continue flag;
                }
            }
            arr[cnt] = p.get(cnt).dir[i];
            vd[cnt] = i;
            combi(cnt + 1, currProcessor + 1);
        }
    }
}
