import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1037 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while(n-- > 0) {
            int number = Integer.parseInt(st.nextToken());
            if (min > number) {
                min = number;
            }
            if (max < number) {
                max = number;
            }
        }
        System.out.print(max * min);
    }
}
