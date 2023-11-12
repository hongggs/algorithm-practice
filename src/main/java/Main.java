import java.io.*;
import java.util.*;

class Main {
    static int T;

    static int[][] map;
    static int er;
    static int ec;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for(int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            er = Integer.parseInt(st.nextToken());
            ec = Integer.parseInt(st.nextToken());

            int ans = 0;
            if (er % 3 == 0 && ec % 3 == 0) {
                map = new int[er / 3 * 2][ec / 3 * 2];
                ans = solution();
            }
            sb.append("#").append(t).append(" ").append(ans).append("\n");
        }
        System.out.println(sb);
    }

    private static int solution() {

        return 0;
    }
}