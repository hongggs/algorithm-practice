class Solution {
    int[] nums;
    int ans;
    public int solution(int[] nums) {
        this.nums = nums;
        ans = 0;
        
        comb(0, 0, 0);
        
        return ans;
    }
    
    void comb(int s, int cnt, int sum) {
        if(cnt == 3) {
            for(int i = 2; i <= Math.sqrt(sum); i++) {
                if(sum % i == 0) {
                    return;
                }
            }
            ans++;
            return;
        }
        for(int i = s; i < nums.length; i++) {
            comb(i + 1, cnt + 1, sum + nums[i]);
        }
    }
}