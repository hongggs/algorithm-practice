package swea;

import java.io.*;
import java.util.*;

public class S1954 {
    static int[] dr = {0, 1, 0, -1};
    static int[] dc = {1, 0, -1, 0};
    static int[][] arr;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append("\n");
            int n = Integer.parseInt(br.readLine());
            arr = new int[n][n];
            solution(n);
            String ans = formatArr();
            sb.append(ans);
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    private static void solution(int n) {

        int r = 0;
        int c = 0;
        int d = 0;
        int num = 1;
        while (num <= (n * n)) {
            arr[r][c] = num;
            //작성할 숫자 키우기
            num++;
            // 방향 옮기기
            if (!isValid(r + dr[d], c + dc[d], n)) {
                d = (d + 1) % 4;
            }

            r = r + dr[d];
            c = c + dc[d];
        }
    }
    private static boolean isValid(int r, int c, int n) {
        return 0 <= r && r < n && 0 <= c && c < n && arr[r][c] == 0;
    }
    private static String formatArr() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
