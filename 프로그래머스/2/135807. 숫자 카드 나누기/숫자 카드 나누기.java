/*
가장 큰 양의 정수 a 값 구하기
arrayA의 공약수
arrayB의 
*/
class Solution {
    public int solution(int[] arrayA, int[] arrayB) {
        int answer = 0;
        
        int gcd = arrayA[0];
        for(int i = 1; i < arrayA.length; i++) {
            gcd = getGcd(Math.max(gcd, arrayA[i]), Math.min(gcd, arrayA[i]));
        }
        
        for(int i = 1; i < gcd; i++) {
            int temp = gcd / i;
            boolean flag = true;
            for(int j = 0; j < arrayB.length; j++) {
                if(arrayB[j] % temp == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                answer = temp;
                break;
            }
        }
        
        gcd = arrayB[0];
        for(int i = 1; i < arrayB.length; i++) {
            gcd = getGcd(Math.max(gcd, arrayB[i]), Math.min(gcd, arrayB[i]));
        }
        
        for(int i = 1; i < gcd; i++) {
            int temp = gcd / i;
            boolean flag = true;
            for(int j = 0; j < arrayA.length; j++) {
                if(arrayA[j] % temp == 0) {
                    flag = false;
                    break;
                }
            }
            if(flag) {
                answer = Math.max(answer, temp);
                break;
            }
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