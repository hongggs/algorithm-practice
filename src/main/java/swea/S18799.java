package swea;

import java.io.*;
import java.util.*;

class S18799 {
    static int T;
    static int N;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());
            double sum = 0.0;
            for(int i = 0; i < N; i++) {
                sum += Double.parseDouble(st.nextToken());
            }

            sb.append("#").append(t).append(" ");
            if (isInteger(sum / N)) {
                sb.append((int) sum / N).append("\n");
            } else {
                sb.append(sum / N).append("\n");
            }
        }
        System.out.println(sb);
    }

    private static boolean isInteger(double num) {
        return num % 1 == 0.0;
    }
}