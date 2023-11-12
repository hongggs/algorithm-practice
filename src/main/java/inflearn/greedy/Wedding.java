package inflearn.greedy;

/**
 * 현수는 다음 달에 결혼을 합니다.
 * 현수는 결혼식 피로연을 장소를 빌려 3일간 쉬지 않고 하려고 합니다.
 * 피로연에 참석하는 친구들 N명의 참석하는 시간정보를 현수는 친구들에게 미리 요구했습니다.
 * 각 친구들은 자신이 몇 시에 도착해서 몇 시에 떠날 것인지 현수에게 알려주었습니다.
 * 현수는 이 정보를 바탕으로 피로연 장소에 동시에 존재하는 최대 인원수를 구하여 그 인원을 수용할 수 있는 장소를 빌리려고 합니다. 여러분이 현수를 도와주세요.
 *
 * <입력>
 * 5
 * 14 18
 * 12 15
 * 15 20
 * 20 30
 * 5 14
 *
 * <출력>
 * 2
 **/
import java.util.*;
import java.io.*;

class Wedding {
    static int N;
    static ArrayList<Time> guests;
    static class Time implements Comparable<Time>{
        int time;
        char state;
        Time (int time, char state) {
            this.time = time;
            this.state = state;
        }
        public int compareTo(Time t) {
            if (time > t.time) {
                return 1;
            }
            if(time == t.time) {
                if(state != t.state && t.state == 'e') {
                    return 1;
                }
                //return state - t.state;
            }
            return -1;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        guests = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            guests.add(new Time(start, 's'));
            guests.add(new Time(end, 'e'));
        }

        System.out.println(solution());
    }

    public static int solution() {
        Collections.sort(guests);
        int ans = 0;
        int cnt = 0;
        for(Time t : guests) {
            if(t.state == 's') {
                cnt++;
                ans = Math.max(ans, cnt);
            } else {
                cnt--;
            }
        }
        return ans;
    }
}
