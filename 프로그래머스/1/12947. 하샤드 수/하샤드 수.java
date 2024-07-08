class Solution {
    public boolean solution(int x) {
        boolean answer = true;
        int len = String.valueOf(x).length();
        int num = x;
        int div = 0;
        for(double i = Math.pow(10, (len - 1)); num > 0; i /= 10) {
            div += (num / i);
            num %= i;
        }
        
        if(div != 0 && x % div != 0) {
            answer = false;
        }
        
        return answer;
    }
}