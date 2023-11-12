package inflearn.binarysearch;

import java.util.*;
import java.io.*;

class BinarySearch {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i =1; i <= n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        System.out.println(binarySearch(1, n, k, arr));


    }
    public static int binarySearch(int s, int e, int k, int[] arr) {
        int mid;
        int ans = -1;
        while (s <= e) {
            mid = (s + e) / 2;
            if(arr[mid] < k) {
                s = mid + 1;
            } else { //만약 동일한 숫자가 연속해서 존재한다면 가장 앞에있는 수의 인덱스를 출력한다.
                e = mid - 1;
                ans = mid;

            }
        }
        return ans;
    }

}