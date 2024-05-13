package bj.twopointer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution_bj_다이어트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int G = Integer.parseInt(br.readLine());

        ArrayList<Integer> ans = new ArrayList<>();

        int old = 1;
        int now = 2;
        while(old < now) {
            int diff = (now * now) - (old * old);

            if(diff == G) {
                ans.add(now);
                old++;
            } else if(diff < G) {
                now++;
            } else {
                old++;
            }
        }

        if(ans.isEmpty()) {
            System.out.println("-1");
            return;
        }

        for(int i = 0; i < ans.size(); i++) {
            sb.append(ans.get(i)).append("\n");
        }

        System.out.println(sb);
    }
}
