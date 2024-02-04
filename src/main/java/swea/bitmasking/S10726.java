package swea.bitmasking;

import java.io.*;
import java.util.*;

public class S10726 {
    public static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            n = (1 << n) - 1;
            int m = Integer.parseInt(st.nextToken());
            if ((m & n) == n) {
                sb.append("ON").append("\n");
            } else {
                sb.append("OFF").append("\n");
            }
        }
        System.out.println(sb);
    }
}
