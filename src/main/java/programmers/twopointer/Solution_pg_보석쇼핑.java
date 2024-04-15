package programmers.twopointer;

import java.util.*;
public class Solution_pg_보석쇼핑 {
    public int[] solution(String[] gems) {
        int kind = new HashSet<>(Arrays.asList(gems)).size();

        int[] ans = new int[2];
        int len = Integer.MAX_VALUE;
        int start = 0;

        Map<String, Integer> map = new HashMap<>();

        for(int end = 0; end < gems.length; end++) {
            map.put(gems[end], map.getOrDefault(gems[end], 0) + 1);

            while(map.get(gems[start]) > 1) { //어차피 end가 포함하니깐 걍 map에서 하나 뺴고 start 늘리기
                map.put(gems[start], map.get(gems[start]) - 1);
                start++;
            }

            if(map.size() == kind && len > (end - start)) { //최솟값 갱신
                len = end - start;
                ans[0] = start + 1;
                ans[1] = end + 1;
            }
        }

        return ans;
    }
}
