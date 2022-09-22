import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class R4948 {
    static boolean[] arr = new boolean[246913]; // 0부터 시작하므로 2n + 1

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        getPrime();

        StringBuilder sb = new StringBuilder();

        while(true) {
            int n = Integer.parseInt(br.readLine());
            if (n == 0) break;

            int count = 0;

            for(int i = n + 1; i <= 2 * n; i++) {
                if (!arr[i]) count++;
            }
            sb.append(count).append('\n');
        }
        System.out.print(sb);
    }

    public static void getPrime(){
        arr[0] = arr[1] = true;

        for (int i = 2; i < Math.sqrt(arr.length); i++) {
            if(arr[i]) continue; //만약 true이면 소수가 아니라는 뜻이므로 아래 문장 실행 X
            for (int j = i * i; j < arr.length; j += i) { //i의 배수는 arr에서 true로 변경
                arr[j] = true;
            }
        }
    }
}

