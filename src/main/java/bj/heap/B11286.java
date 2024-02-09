package bj.heap;

import java.util.*;
import java.io.*;
public class B11286 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) ->
        {
            if(Math.abs(o1) > Math.abs(o2)) {
                return 1;
            } else if (Math.abs(o1) == Math.abs(o2)) {
                if(o1 > o2) {
                    return 1;
                }
            }
            return -1;
        });

        for(int n = 0; n < N; n++) {
            int x = Integer.parseInt(br.readLine());
            if(x == 0) {
                if(pq.isEmpty()) {
                    sb.append(0);
                } else {
                    sb.append(pq.poll());
                }
                sb.append("\n");
            } else {
                pq.offer(x);
            }
        }

        System.out.println(sb);
    }
}

