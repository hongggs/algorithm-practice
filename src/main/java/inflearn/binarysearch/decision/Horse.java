package inflearn.binarysearch.decision;

import java.util.*;
import java.io.*;

class Horse {
    public static int count(int[] arr, int dist) {
        int cnt = 1;
        int ep = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if(arr[i] - ep >= dist) {
                cnt++;
                ep = arr[i];
            }
        }
        return cnt;
    }

    public static int solution(int n, int c, int[] arr) {
        int ans = 0;
        int start = 1;
        int end = arr[n - 1];

        while(start <= end) {
            int mid = (start + end) / 2;
            if(count(arr, mid) >= c) {
                ans = mid;
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return ans;
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int ans = 0;
        if(c == 2) {
            ans = arr[n - 1] - arr[0];
        }

        System.out.println(solution(n, c, arr));

    }

}