package swea;

import java.io.*;
import java.util.*;

public class S1859 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int max = arr[n - 1];
            long ans = 0;
            for(int i = n - 2; i >= 0; i--) {
                if(max > arr[i]) {
                    ans += (max - arr[i]);
                } else {
                    max = arr[i];
                }
            }

            sb.append(ans).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}