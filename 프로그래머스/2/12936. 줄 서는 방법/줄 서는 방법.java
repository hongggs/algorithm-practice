import java.util.*;
class Solution {    
    public int[] solution(int n, long k) {
        long facN = 1;
        for(int i = n - 1; i >= 2; i--) {
            facN *= i;
        }
        
        int[] ans = new int[n];
        boolean[] v = new boolean[n + 1];
        int index = 0;
        int num = 1;
        while(index < n - 1) {
            for(int i = 1; i <= n; i++) {
                if(v[i]) continue;
                if(k - facN <= 0) {
                    ans[index] = i;
                    v[i] = true;
                    break;
                }
                k -= facN;
            }
            facN /= (n - 1 - index);
            index++;
            
        }
        for(int i = 1; i <= n; i++) {
            if(v[i]) continue;
            if(k - facN <= 0) {
                ans[index] = i;
                v[i] = true;
                break;
            }
        }
    
       
        return ans;
    }

}