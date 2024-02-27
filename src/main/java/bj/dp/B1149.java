package bj.dp;

import java.util.*;
import java.io.*;

/**
 * <RGB거리>
 * N개의 집을 칠하려고 한다.
 * 각 집을 빨강, 초록, 파랑 중 하나의 색으로 칠하는 비용이 주어졌다.
 * 이때 **옆집**과 색이 같지 않게 칠하는 비용의 최솟값을 구하는 문제!
 * 옆집: i와 i + 1의 색이 같지 않아야한다.
 */
public class B1149 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //0. 입력
        int N = Integer.parseInt(br.readLine()); //칠해야하는 집의 수
        int[][] arr =  new int[N][3]; //각 집을 R, G, B로 칠했을 때의 비용을 저장할 배열
        for(int i = 0; i < N; i++) { //R: 0, G: 1, B: 2
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 3; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        //1. 계산
        /**
         * 0번째 부터 N-1번째 집을 차례로 칠한다고 가정하자
         * 0번째 집을 빨강색으로 칠했을때의 최소값은 26이다.
         * 왜냐하면 아직 다른 집을 칠하지 않아서 그냥 자기 자신의 값이 최솟값이기 때문이다.
         * 1번째 집을 빨강색으로 칠했을때의 최솟값은
         * - 0번째 집을 초록색으로 칠한 값의 최솟값 + 1번째 집을 빨강색으로 칠하는 값
         * - 0번째 집을 파랑색으로 칠한 값의 최솟값 + 1번째 집을 빨강색으로 칠하는 값
         * 위 두 값 중 더 작은 것이 최솟값이 된다.
         * 이런 방식으로 모든 dp배열을 채울 수 있다.
         */

        /**
         *
         <arr>		<dp>
         26 40 83	26 40 83
         49 60 57	89 86 83
         13 89 99	96 172 185
         */
        //0번째 집은 아직 다른 집을 고려할 필요 없으므로 자기 자신의 값만 메모할 배열인 dp배열에 할당
        int[][] dp = new int[N][3];
        for(int i = 0; i < 3; i++) {
            dp[0][i] = arr[0][i];
        }

        for(int i = 1; i < N; i++) {
            for(int j = 0; j < 3; j++) {
                //자기 자신의 값 + 자기색을 제외한 다른 색으로 칠한 값들 중 최솟값
                dp[i][j] = arr[i][j] + Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]);
            }
        }

        //2. N-1열에서 가장 작은 값이 전체 집을 칠하는데 최솟값이므로 출력해준다.
        int ans = dp[N - 1][0];
        for(int i = 1; i < 3; i++) {
            ans = Math.min(ans, dp[N - 1][i]);
        }

        System.out.println(ans);
        br.close();
    }
}

