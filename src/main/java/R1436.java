import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class R1436 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int cnt = 1;
        int result = 666;
        while(cnt < n) {
            if(String.valueOf(result).contains("666")) {
                cnt++;
            }
            result++;
        }
        System.out.print(result);
    }
}
