package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            String x = br.readLine();
            sb.append(isVPS(x)).append("\n");
        }

        System.out.println(sb);

    }

    private static String isVPS(String x) {
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0; i < x.length(); i++) {
            if(x.charAt(i) == ')') {
                if(!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return "NO";
                }
            } else {
                stack.push(x.charAt(i));
            }
        }
        if(stack.isEmpty()){
            return "YES";
        } else {
            return "NO";
        }
    }

}
