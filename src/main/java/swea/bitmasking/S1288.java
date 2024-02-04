package swea.bitmasking;

import java.io.*;
import java.util.*;


public class S1288 {
    public static int N;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            int n = Integer.parseInt(br.readLine());

            int num = 0;
            int x = 1;
            int bit = 0;
            while(true) {
                if(bit == ((1 << 10) - 1)) {
                    break;
                }
                num = x * n;
                for(char c : String.valueOf(num).toCharArray()) {
                    bit = bit | (1 << (c - '0'));
                }
                x++;

            }

            sb.append(num).append("\n");

        }
        System.out.println(sb);
    }
}