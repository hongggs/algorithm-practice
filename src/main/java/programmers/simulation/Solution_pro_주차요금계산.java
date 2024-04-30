package programmers.simulation;

import java.util.*;

public class Solution_pro_주차요금계산 {
/*
    1. 각 자동차의 누적 주차 시간 구한다.
        당일 23:59분까지의 시간 -> 입차 후 출차 기록이 없으면 23:59분이 출차시간
    2. 주차 요금 구하기
        누적 주차 시간이 기본 시간이하라면, 기본 요금을 청구
    3. 누적 주차 시간이 기본 시간을 초과하면, 기본 요금에 더해서, 초과한 시간에 대해서 단위 시간 마다 단위 요금을 청구합니다.
        초과한 시간이 단위 시간으로 나누어 떨어지지 않으면, 올림합니다.
        ⌈a⌉ : a보다 작지 않은 최소의 정수를 의미합니다. 즉, 올림을 의미합니다.
*/
    class Solution {
        public int[] solution(int[] fees, String[] records) {
            HashMap<String, Queue<Integer>> timeMap = new HashMap<>();
            for(int i = 0; i < records.length; i++) {
                String[] s = records[i].split(" ");

                int h = Integer.parseInt(s[0].split(":")[0]) * 60;
                int m = Integer.parseInt(s[0].split(":")[1]);

                timeMap.putIfAbsent(s[1], new ArrayDeque<>());
                timeMap.get(s[1]).offer(h + m);
            }

            int[] ans = new int[timeMap.size()];
            int index = 0;
            ArrayList<String> keys = new ArrayList<>(timeMap.keySet());
            Collections.sort(keys);
            for(String k : keys) {
                if(timeMap.get(k).size() % 2 != 0) {
                    timeMap.get(k).offer((23 * 60 + 59));
                }
                Queue<Integer> q = timeMap.get(k);
                int time = 0;
                while(!q.isEmpty()) {
                    time += Math.abs(q.poll() - q.poll());
                }
                time-=fees[0];

                if(time <= 0) {
                    ans[index] = fees[1];
                } else {
                    if(time % fees[2] == 0) {
                        ans[index] = fees[1] + (time/fees[2])*fees[3];
                    } else {
                        ans[index] = fees[1] + (time/fees[2] + 1)*fees[3];
                    }
                }
                index++;
            }

            return ans;
        }
    }
}
