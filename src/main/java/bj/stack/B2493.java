package bj.stack;

import java.util.*;
import java.io.*;
public class B2493 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int arr[] = new int[N];
        ArrayDeque<Integer> s = new ArrayDeque<>();
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            while(!s.isEmpty() && arr[s.peek()] < arr[i]) {
                s.pop();
            }
            if(s.isEmpty()) {
                sb.append("0 ");
            } else {
                sb.append(s.peek() + 1).append(" ");
            }
            s.push(i);
        }

        System.out.println(sb);
    }
}
