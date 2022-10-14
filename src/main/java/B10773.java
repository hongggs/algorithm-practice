import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class B10773 {
    public static void main(String[]  args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(br.readLine());
        Deque<Integer> stack = new ArrayDeque<>();

        while(k-- > 0) {
            int num = Integer.parseInt(br.readLine());
            if(num == 0) {
                stack.pop();
            } else {
                stack.push(num);
            }
        }

        int sum = stack.stream().mapToInt(i -> i)
                .sum();

        System.out.println(sum);

    }
}
