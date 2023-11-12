package bj.simulation;

import java.util.*;
import java.io.*;

class B14890 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for(int i = 0; i < N; i++) {
            if (checkPath(i, 0, true, map, N, L)) {
                ans++;
            }
            if (checkPath(0, i, false, map, N, L)) {
                ans++;
            }
        }

        System.out.println(ans);

    }

    public static boolean checkPath(int x, int y, boolean flag, int[][] map, int N, int L) {
        int[] height = new int[N];
        boolean[] visit = new boolean[N];

        for(int i = 0; i < N; i++) { // 행이나 열을 다른 배열을 생성해 계산 -> 행과 열 함수를 따로 만들 필요 없어 중복 코드 생략 가능
            if(flag) { // 행
                height[i] = map[x][i];
            } else { // 열
                height[i] = map[i][y];
            }
        }

        for(int i = 0; i < N - 1; i++) {
            // 높이가 같을 때
            if(height[i] == height[i + 1]) {
                continue;
            }
            // 내려가는 경사
            else if(height[i] - height[i + 1] == 1) {
                for(int j = i + 1; j <= i + L; j++) {
                    if(j >= N || height[i + 1] != height[j] || visit[j]) { // 옆사람과 높이가 같은지 확인하는 것이 아닌 기준을 정해서 확인하니 코드가 더 깔끔
                        return false;
                    }
                    visit[j] = true;
                }
            }
            // 올라가는 경사
            else if(height[i] - height[i + 1] == -1) {
                for(int j = i; j > i - L; j--) {
                    if(j <0 || height[i] != height[j] || visit[j]) {
                        return false;
                    }
                    visit[j] = true;
                }
            }
            //높이가 2칸 이상 차이 남
            else {
                return false;
            }
        }
        return true;
    }
    public static int solution(int[][] map, int N, int L) {
        int ans = 0;
        //겹치면 안됨
        boolean[][] visit = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j == N - 1) { // 끝에 도착!
                    ans++;
                }
                else if(map[i][j] != map[i][j + 1]) { //경사로를 놔야함
                    if(map[i][j] - map[i][j + 1] == 1) { // 내려갈때
                        boolean isValid = true;
                        if(j + L >= N) { // 경사로를 놓을 수 있는 길이가 존재하지 않는다. -> 불가능하므로 카운트 x
                            break;
                        }
                        if(visit[i][j + 1]) { // 겹치게 경사로를 놓아야하는 경우 -> 불가능하므로 카운트 x
                            break;
                        }

                        // 내려가는 경사로 놓기 시작
                        visit[i][j + 1] = true;
                        for(int z = j + L; z > j + 1; z--) {
                            if(visit[i][z]) { // 겹치면 경사로 불가
                                break;
                            }
                            if(map[i][z] != map[i][z - 1]) { // 높이가 달라 경사로를 놓을 수 없음 -> 카운트 x
                                isValid = false;
                                for(int t = z + 1; t <= j + L; t++) { // 이전에 놓았던 경사로를 없앤다.
                                    visit[i][t] = false;
                                }
                                visit[i][j + 1] = false;
                                break;
                            }
                            visit[i][z] = true;
                        }
                        if(!isValid) {
                            break;
                        }
                    } else if(map[i][j + 1] - map[i][j] == 1) { //올라갈때
                        boolean isValid = true;
                        if(j - L + 1 < 0) { // 경사로를 놓을 수 있는 길이가 존재하지 않는다. -> 불가능하므로 카운트 x
                            break;
                        }
                        if(visit[i][j - L + 1]) { // 겹치게 경사로를 놓아야하는 경우 -> 카운트 x
                            break;
                        }

                        //올라가는 경사로 놓기 시작
                        visit[i][j - L + 1] = true;
                        for(int z = j; z > j - L + 1; z--) {
                            if(visit[i][z]) {
                                break;
                            }
                            if(map[i][z] != map[i][z - 1]) { // 높이가 달라 경사로를 놓을 수 없음 -> 카운트 x
                                isValid = false;
                                for(int t = j; t > z; t--) { // 만들었던 경사로 없애기
                                    visit[i][t] = false;
                                }
                                visit[i][j - L + 1] = false;
                                break;
                            }
                            visit[i][z] = true;
                        }
                        if(!isValid) {
                            break;
                        }
                    } else { // 높이차이가 1이 아닌 경우 경사로 놓기 불가 -> 카운트 x
                        break;
                    }
                }
            }
        }

        visit = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(j == N - 1) { // 끝에 도착
                    ans++;
                }
                else if(map[j][i] != map[j + 1][i]) { //경사로를 놔야함
                    if(map[j][i] - map[j + 1][i] == 1) { // 내려갈때
                        boolean isValid = true;
                        if(j + L >= N) { // 경사로를 놓을 수있는 길이가 존재하지 않는다. -> 불가능하므로 카운트 x
                            break;
                        }
                        if(visit[j + 1][i]) { // 겹치게 경사로를 놓아야하는 경우 -> 카운트 x
                            break;
                        }

                        // 내려가는 경사로 놓기 시작
                        visit[j + 1][i] = true;
                        for(int z = j + L; z > j + 1; z--) {
                            if(visit[z][i]) { // 겹치면 경사로 불가 -> 카운트 x
                                break;
                            }
                            if(map[z][i] != map[z - 1][i]) { // 높이 차이로 걍사로 불가
                                isValid = false;
                                for(int t = z + 1; t <= j + L; t++) { // 놓았던 경사 초기화
                                    visit[t][i] = false;
                                }
                                visit[j + 1][i] = false;
                                break;
                            }
                            visit[z][i] = true;
                        }
                        if(!isValid) {
                            break;
                        }
                    } else if(map[j+1][i] - map[j][i] == 1) { //올라갈때
                        boolean isValid = true;
                        if(j - L + 1 < 0) { // 경사로를 놓을 수있는 길이가 존재하지 않는다. -> 불가능하므로 카운트 x
                            break;
                        }
                        if(visit[j - L + 1][i]) { // 겹치게 경사로를 놓아야하는 경우 -> 카운트 x
                            break;
                        }

                        // 올라가는 경사로 놓기 시작
                        visit[j - L + 1][i] = true;
                        for(int z = j; z > j - L + 1; z--) {
                            if(visit[z][i]) { // 겹치면 경사로 불가 -> 카운트 x
                                break;
                            }
                            if(map[z][i] != map[z - 1][i]) { // 높이 차이로 걍사로 불가
                                isValid = false;
                                for(int t = j; t > z; t--) { // 놓았던 경사 초기화
                                    visit[t][i] = false;
                                }
                                visit[j - L + 1][i] = false;
                                break;
                            }
                            visit[z][i] = true;
                        }
                        if(!isValid) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return ans;
    }

}