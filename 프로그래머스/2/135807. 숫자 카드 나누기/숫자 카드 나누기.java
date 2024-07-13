class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int gcd = arrayA[0];
        for(int i = 1; i < arrayA.length; i++) {
            gcd = getGcd(Math.max(gcd, arrayA[i]), Math.min(gcd, arrayA[i]));
        }
        
        boolean flag = true;
        for(int j = 0; j < arrayB.length; j++) {
            if(arrayB[j] % gcd == 0) {
                flag = false;
                break;
            }
        }
        
        if(flag) {
            answer = gcd;
        }
        
        gcd = arrayB[0];
        for(int i = 1; i < arrayB.length; i++) {
            gcd = getGcd(Math.max(gcd, arrayB[i]), Math.min(gcd, arrayB[i]));
        }
        
        flag = true;
        for(int j = 0; j < arrayA.length; j++) {
            if(answer >= gcd) {
                flag = false;
                break;
            }
            if(arrayA[j] % gcd == 0) {
                flag = false;
                break;
            }
        }
        
        if(flag) {
            answer = gcd;
        }
        
        return answer;
    }
    
    int getGcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return getGcd(b, a%b);
    }
}