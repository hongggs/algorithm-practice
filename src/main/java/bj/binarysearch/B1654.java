package bj.binarysearch;

import java.util.*;
import java.io.*;
public class B1654 {
    static int ans;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        ArrayList<Integer> nums = new ArrayList<>();
        for(int i = 0; i < k; i++) {
            nums.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(nums);

        search(n, nums);
        System.out.println(ans);
    }

    public static void search(int k, ArrayList<Integer> nums) {
        long start = 1;
        long end = nums.get(nums.size() - 1);
        while(start <= end) {
            int sum = 0;
            long mid =  (start + end)/ 2;
            for(int num : nums) {
                sum += num / mid;
            }
            if(sum < k) {
                end = mid - 1;
            } else {
                ans = (int)mid;
                start = mid + 1;
            }
        }
    }
}

