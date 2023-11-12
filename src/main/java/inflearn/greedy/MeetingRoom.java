package inflearn.greedy;

import java.util.*;
import java.io.*;

class MeetingRoom {
    static int N;
    static ArrayList<Time> meetings;
    static class Time implements Comparable<Time>{
        int start;
        int end;
        Time(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Time t) {
            if(end > t.end) {
                return 1;
            } else if(end == t.end) {
                if(start > t.start) {
                    return 1;
                }
            }
            return -1;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        meetings = new ArrayList<>();
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            meetings.add(new Time(start, end));
        }

        Collections.sort(meetings);
        System.out.println(solution());
    }

    public static int solution() {
        int curr = meetings.get(0).end;
        int ans = 1;
        for(int i = 1; i < N; i++) {
            if(curr <= meetings.get(i).start) {
                curr = meetings.get(i).end;
                ans++;
            }
        }

        return ans;
    }
}