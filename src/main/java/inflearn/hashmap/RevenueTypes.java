package inflearn.hashmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class RevenueTypes {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st =  new StringTokenizer(br.readLine());
        int arr[] = new int[n];
        for(int i = 0; i< n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> map = new HashMap<>();

        int cnt = 0;
        for(int i = 0; i < k; i++) {
            if(!map.containsKey(arr[i])) {
                map.put(arr[i], 1);
                cnt++;
            } else {
                int v = map.get(arr[i]);
                map.put(arr[i], ++v);
            }
        }
        sb.append(cnt+ " ");

        for(int i = 1; i <= n - k; i++) {
            if (map.get(arr[i - 1]) == 1) {
                map.remove(arr[i - 1]);
                cnt--;
            } else {
                int v = map.get(arr[i - 1]);
                map.put(arr[i - 1], --v);
            }

            if(!map.containsKey(arr[i + k - 1])) {
                map.put(arr[i + k - 1], 1);
                cnt++;
            } else {
                int v = map.get(arr[i + k - 1]);
                map.put(arr[i + k - 1], ++v);
            }

            sb.append(cnt + " ");
        }

        System.out.println(sb);
    }
}
