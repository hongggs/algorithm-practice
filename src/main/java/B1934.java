import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1934 {
    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            int num3 = gcd(num2, num1);
            sb.append(num1 * num2 / num3).append("\n");
        }

        System.out.println(sb);

    }

    public static int gcd(int num1, int num2) { // 유클리드 호제법 (Euclidean algorithm) 사용
        while (num2 != 0) {
            int num3 = num1 % num2;
            num1 = num2;
            num2 = num3;
        }
        return num1;
    }
}
