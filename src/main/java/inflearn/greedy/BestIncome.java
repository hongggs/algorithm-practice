package inflearn.greedy;

import java.util.*;
import java.io.*;

/**
 * 현수는 유명한 강연자이다. N개이 기업에서 강연 요청을 해왔다. 각 기업은 D일 안에 와서 강연을 해 주면 M만큼의 강연료를 주기로 했다.
 * 각 기업이 요청한 D와 M를 바탕으로 가장 많을 돈을 벌 수 있도록 강연 스케쥴을 짜야 한다.
 * 단 강연의 특성상 현수는 하루에 하나의 기업에서만 강연을 할 수 있다.
 *
 * 6
 * 50 2
 * 20 1
 * 40 2
 * 60 3
 * 30 3
 * 30 1
 *
 * 150
 *
 * 시간 기준 내림차순 정렬
 * max시간을 기준으로 해당 시간에 강연해서 얼마 벌지 정함
 * 해당 시간에 가능한 강의가 여러개 있을 수 있기에 pq에 넣어서 판단
 *
 */

class BestIncome {
    static int N;
    static int max;
    static class Lecture implements Comparable<Lecture> {
        int day;
        int money;
        Lecture(int day, int money) {
            this.day = day;
            this.money = money;
        }

        public int compareTo(Lecture l) {
            return l.day - day;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        ArrayList<Lecture> lectures = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            int day = Integer.parseInt(st.nextToken());
            lectures.add(new Lecture(day, money));
            if(day > max) {
                max = day;
            }
        }
        System.out.println(solution(lectures));
    }

    public static int solution(ArrayList<Lecture> lectures) {
        Collections.sort(lectures);
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        int ans = 0;

        int j = 0;
        for(int i = max; i >= 1; i--) {
            for (; j < N; j++) {
                if(lectures.get(j).day < i) {
                    break;
                }
                pq.offer(lectures.get(j).money);
            }
            if (!pq.isEmpty()) {
                ans += pq.poll();
            }
        }
        return ans;
    }
}
