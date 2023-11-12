package inflearn.sort;

import java.io.*;
import java.util.*;

public class LRU {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        int[] cache = new int[s];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int num : arr) {
           int index = -1;
           for(int i = 0; i < s; i++) { //isHit
               if(num == cache[i]) {
                   index = i;
                   break;
               }
           }

           if(index == -1) { // hit x
               for(int i = s - 1; i >= 1; i--) {
                   cache[i] = cache[i - 1];
               }
           } else { //hit o
               for(int i = index; i >= 1; i--) {
                   cache[i] = cache[i - 1];
               }
           }
           cache[0] = num;
        }

        for(int i = 0; i < s; i++) {
            System.out.print(cache[i] + " ");
        }
    }

}
