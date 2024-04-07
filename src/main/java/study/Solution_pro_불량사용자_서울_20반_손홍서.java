package study;

import java.util.*;
class Solution_pro_불량사용자_서울_20반_손홍서 {
    static int M;
    static Set<Integer> ansSet;
    static ArrayList<Integer>[] ableList;
    public int solution(String[] user_id, String[] banned_id) {
        M = banned_id.length;
        ableList = new ArrayList[M];
        for(int i = 0; i < M; i++) {
            ableList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            for(int j = 0; j < user_id.length; j++) {
                if(isValid(banned_id[i], user_id[j])) {
                    ableList[i].add(j);
                }
            }
        }

        ansSet = new HashSet<>();
        countAns(0, 0);
        return ansSet.size();
    }

    static boolean isValid(String banned, String s) {
        if(banned.length() != s.length()) {
            return false;
        }

        for(int i = 0; i < banned.length(); i++) {
            if(banned.charAt(i) != '*' && banned.charAt(i) != s.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    static void countAns(int cnt, int v) {
        if(cnt == M) {
            ansSet.add(v);
            return;
        }
        for(int i = 0; i < ableList[cnt].size(); i++) {
            int now = ableList[cnt].get(i);
            if((v & (1 << now)) != 0) {
                continue;
            }
            countAns(cnt + 1, (v | (1 << now)));
        }
    }
}
