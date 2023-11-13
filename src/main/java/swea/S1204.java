package swea;

import java.io.*;
import java.util.*;

public class S1204 {
    public static final int STUDENT_CNT = 1000;
    public static final int HUNDRED = 100;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            br.readLine();
            int[] arr = new int[HUNDRED + 1];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < STUDENT_CNT; i++) {
                int score = Integer.parseInt(st.nextToken());
                arr[score]++;
            }
            int ans = 0;
            int max = 0;
            for(int i = 0; i <= HUNDRED; i++) {
                if(arr[i] >= max) {
                    max = arr[i];
                    ans = i;
                }
            }

            sb.append(ans).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
