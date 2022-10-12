import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class B2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine(), " ");

        int left = 0;
        int right = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            right = Math.max(right, arr[i]);
        }

        while(left <= right) {
            int mid = (left + right) / 2;

            long sum = 0;

            for (int i = 0; i < n; i++) {
                if(arr[i] > mid)
                    sum += arr[i] - mid;
            }

            if(m <= sum)
                left = mid + 1;
            else
                right = mid - 1;
        }

        System.out.println(right);

    }


}
