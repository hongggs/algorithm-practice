package inflearn.array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LongestContiguousSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n + 1];
        ArrayList<Integer> zeroList = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] == 0) {
                zeroList.add(i);
            }
        }
        // 모든 0을 1로 바꿀 수 있을때, n을 출력
        if(zeroList.size() <= k) {
            System.out.println(n);
            return;
        }

        int start = 0;
        int end = start + k - 1;
        int ans = 0;
        while(end < zeroList.size()) {
            int temp = 0;
            if(end == zeroList.size() - 1) {
                temp += n;
            } else {
                temp += zeroList.get(end + 1) - 1;
            }

            if(start != 0) {
                temp -= zeroList.get(start - 1);
            }
            if(temp > ans) {
                ans = temp;
            }

            start++;
            end++;
        }

        System.out.println(ans);
    }
}
