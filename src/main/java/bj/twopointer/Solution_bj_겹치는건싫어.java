package bj.twopointer;

import java.io.*;
import java.util.*;

public class Solution_bj_겹치는건싫어 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] nums =  new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        HashMap<Integer, Integer> countMap = new HashMap<>();
        int len = 1;
        int start = 0;
        for(int end = 0; end < N; end++) {
            countMap.put(nums[end], countMap.getOrDefault(nums[end], 0) + 1);
            if(countMap.get(nums[end]) > K) {
                len = Math.max(len, (end - 1) - start + 1);
                countMap.put(nums[end], countMap.get(nums[end]) - 1);
                for(int i = start; i < end; i++) {
                    if(nums[i] == nums[end]) {
                        start = i + 1;
                        break;
                    } else {
                        countMap.put(nums[i], countMap.get(nums[i]) - 1);
                    }
                }
            }
        }

        len = Math.max(len, N - start);

        System.out.println(len);
    }
}
