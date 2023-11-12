package inflearn.stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Stick {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();
        Stack<Character> stack = new Stack<>();

        int ans = 0;
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] == '(')
                stack.push('(');
            else {
                stack.pop();
                if(arr[i-1] == '(') {
                    ans += stack.size();
                } else {
                    ans++;
                }
            }
        }

        System.out.println(ans);

    }
}
