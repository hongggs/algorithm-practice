package swea.dfs;

import java.io.*;
import java.util.*;

public class Solution_d3_1289_원재의메모리복구하기_서울_20반_손홍서 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++)
        {
            sb.append("#").append(t).append(" ");
            String input = br.readLine();

            int ans = 0;
            char old = '0';
            for(char x: input.toCharArray()) {
                if(x != old) {
                    ans ++;
                    old = x;
                }
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
        br.close();

    }
}
