package samsung;

import java.io.*;
import java.util.*;

public class Solution_모의_5658_보물상자비밀번호_서울_20반_손홍서 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            char[] num = br.readLine().toCharArray();

            Set<String> set = new HashSet<>();
            String temp = "";
            int X = N / 4;
            for(int s = 0; s < X; s++) {
                for(int i = s; i < N + s; i++) {
                    if((i - s) % X == 0) {
                        temp = num[i % N] + "";
                    } else if((i - s) % X == X - 1){
                        temp += num[i % N];
                        set.add(temp);
                    } else {
                        temp += num[i % N];
                    }
                }
            }

            ArrayList<Integer> list = new ArrayList<>();
            for(String x : set) {
                int v = Integer.parseInt(x, 16);
                list.add(v);
            }
            Collections.sort(list);
            sb.append(list.get(list.size() - K)).append("\n");
        }
        System.out.println(sb);
    }

}

