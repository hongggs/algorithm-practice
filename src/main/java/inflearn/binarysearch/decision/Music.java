package inflearn.binarysearch.decision;

import java.util.*;
import java.io.*;

class Music {
    public static int count(int[] arr, int capacity) {
        int cnt = 1;
        int sum = 0;
        for(int x : arr) {
            if (sum + x > capacity) {
                cnt++;
                sum = x;
            } else {
                sum += x;
            }
        }
        return cnt;
    }

    public static int solution(int n, int m, int[] arr) {
        int ans = 0;
        int start = Arrays.stream(arr).max().getAsInt();
        int end = Arrays.stream(arr).sum();

        while(start <= end) {
            int mid = (start + end) / 2;
            if(count(arr, mid) <= m) {
                ans = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution(n, m, arr));

    }

}