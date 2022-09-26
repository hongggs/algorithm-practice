import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B10250 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while(t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int h = Integer.parseInt(st.nextToken());
            st.nextToken();
            int n = Integer.parseInt(st.nextToken());

            int roomH = n % h;
            int roomN = n / h;
            if (roomH == 0) {
                System.out.println(h * 100 + roomN);
            } else {
                System.out.println(roomH * 100 + roomN + 1);
            }
        }
    }
}
