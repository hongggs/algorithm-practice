class Solution {
    public String solution(String p) {
        String answer = dfs(p);
        return answer;
    }
    
    
    String dfs(String w) {
        //1. 입력이 빈 문자열인 경우, 빈 문자열을 반환합니다. 
        if(w.equals("")) {
            return "";
        }
        // 2.문자열 w를 두 "균형잡힌 괄호 문자열" u, v로 분리
        // 단, u는 "균형잡힌 괄호 문자열"로 더 이상 분리할 수 없어야 하며, v는 빈 문자열이 가능
        int cnt = 0;
        String u = "";
        String v = "";
        
        for(int i = 0; i < w.length(); i++) {
            if(w.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            
            if(cnt == 0) {
                u = w.substring(0, i + 1);
                if(i < w.length() - 1) {
                    v = w.substring(i + 1, w.length());
                }
                break;
            }
        }
        
        System.out.println("u=" + u);
        System.out.println("v=" + v);
        
        //3. 문자열 u가 "올바른 괄호 문자열" 이라면, 문자열 v에 대해 1단계부터 다시 수행
        if(isValid(u)) { 
            //3-1. 수행한 결과 문자열을 u에 이어 붙인 후 반환합니다.
            return u + dfs(v);
        }
        //4. 문자열 u가 "올바른 괄호 문자열"이 아니라면
        else {
            //4-1. 빈 문자열에 첫 번째 문자로 '('를 붙입니다. 
            //4-2. 문자열 v에 대해 1단계부터 재귀적으로 수행한 결과 문자열을 이어 붙입니다. 
            //4-3. ')'를 다시 붙입니다.            
            String temp = "(" + dfs(v) + ")";
            //4-4. u의 첫 번째와 마지막 문자를 제거하고, 나머지 문자열의 괄호 방향을 뒤집어서 뒤에 붙입니다.
            u = u.substring(1, u.length() - 1);
                
            for(int i = 0; i < u.length(); i++) {
                if(u.charAt(i) == '(') {
                    temp += ")";
                } else {
                    temp += "(";
                }
            }
            
            return temp;
        }
    }
    
    boolean isValid(String u) {
        int cnt = 0;
        for(int i = 0; i < u.length(); i++) {
            if(u.charAt(i) == '(') {
                cnt++;
            } else {
                cnt--;
            }
            
            if(cnt < 0) {
                return false;
            }
        }
        
        return cnt == 0;
    }
}