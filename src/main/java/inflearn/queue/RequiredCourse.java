package inflearn.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class RequiredCourse {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();
        Queue<Character> required = new ArrayDeque<>();

        for (char c : arr1) {
            required.offer(c);
        }

        String ans = "YES";
        for(char c : arr2) {
            if (required.contains(c)) {
                if(required.poll() != c) {
                    ans = "NO";
                    break;
                }
            }
        }

        if(!required.isEmpty()) {
            ans = "NO";
        }

        System.out.println(ans);
    }
}
