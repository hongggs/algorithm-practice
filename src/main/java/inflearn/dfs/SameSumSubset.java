package inflearn.dfs;

import java.util.*;
import java.io.*;
public class SameSumSubset {

    static int n;
    static int sum = 0;
    static ArrayList<Integer> nums = new ArrayList<>();
    static String ans = "NO";
    static boolean flag = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
            sum += nums.get(i);
        }
        Collections.sort(nums);
        search(0, 0, sum);
        System.out.println(ans);
    }

    public static void search(int start, int sumA, int sumB) {
        if(flag) {
            return;
        }
        if(sumA > (sum/2)) {
            return;
        }
        if(sumA == sumB) {
            ans = "YES";
            flag = true;
        }
        for(int i = start; i < n; i++) {
            search(i + 1, sumA + nums.get(i), sumB - nums.get(i));
        }
    }
}

