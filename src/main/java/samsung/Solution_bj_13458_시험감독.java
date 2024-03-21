package samsung;

import java.util.*;
import java.io.*;

public class Solution_bj_13458_시험감독 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int classes[] = new int[N];
        for(int i = 0; i < N; i++) {
            classes[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int index = 0;
        long ans = 0;
        while(index < N) {
            classes[index] -= B;
            ans++;
            if(classes[index] > 0) {
                ans += classes[index] / C;
                if (classes[index] % C != 0) {
                    ans++;
                }
            }
            index++;
        }

        System.out.println(ans);
    }
}
