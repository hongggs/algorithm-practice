import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        boolean[] arr = new boolean[n+1];
        getPrime(arr, n);
        for (int i = m; i <= n; i++) {
          if(!arr[i]) {
              System.out.println(i);
          }
        }
    }

    public static void getPrime(boolean[] arr, int n) {
        arr[0] = arr[1] = true;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if(arr[i]) continue;
            for (int j = i * i; j <= n; j += i) {
                arr[j] = true;
            }
        }
    }
}
