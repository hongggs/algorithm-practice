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
            result++;
            if(String.valueOf(result).contains("666")) {
                cnt++;
            }
        }
        System.out.print(result);
    }
}
