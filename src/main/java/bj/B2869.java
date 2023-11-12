package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B2869 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        v = v - b; // 정점에 도달하면 미끄러지지 않는다는 조건을 충족시키기 위해 미리 한 번 미끄러지는 경우를 뺀다.
        int cnt = v / (a - b);
        if (v % (a - b) != 0) {
            cnt++;
        }
        System.out.print(cnt);
    }
}
