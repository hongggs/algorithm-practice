package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B4949 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();

    }

    private static String check(String str) {
        Deque<Character> stack1 = new ArrayDeque<>(); // (
        Deque<Character> stack2 = new ArrayDeque<>(); // [

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(') {
                stack1.push('(');
            } else if (str.charAt(i) == '[') {
                stack2.push('[');
            } else if (str.charAt(i) == ')') {
                if (!stack1.isEmpty()) {
                    stack1.pop();
                } else {
                    return "no";
                }
            } else if (str.charAt(i) == ']') {
                if (!stack2.isEmpty()) {
                    stack2.pop();
                } else {
                    return "no";
                }
            }
        }

        if (stack1.isEmpty() && stack2.isEmpty()) {
            return "yes";
        } else {
            return "no";
        }
    }
}
