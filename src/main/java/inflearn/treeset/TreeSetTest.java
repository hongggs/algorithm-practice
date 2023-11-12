package inflearn.treeset;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class TreeSetTest {
    static TreeSet<Integer> Tset = new TreeSet<>(Collections.reverseOrder());
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i =0; i<n; i++) {
            for(int j = i+1; j<n; j++) {
                for(int l = j+1; l<n; l++) {
                    Tset.add(arr[i]+arr[j]+arr[l]);
                }
            }
        }

        int ans = -1;
        int cnt = 0;
        for(int x : Tset) {
            cnt++;
            if(cnt == k) {
                ans = x;
                break;
            }
        }
        System.out.println(ans);
    }
}
