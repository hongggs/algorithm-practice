package study;

import java.util.*;

class Solution_pro_디스크컨트롤러_서울_20반_손홍서 {
    public int solution(int[][] jobs) {
        int N = jobs.length;
        boolean[] v = new boolean[N];

        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

        int t = jobs[0][0];
        int ans = 0;
        int n = 0;
        while(n < N) {
            int min = Integer.MAX_VALUE;
            int minI = -1;
            for(int i = 0; i < N; i++) {
                if(v[i]) {
                    continue;
                }
                if(jobs[i][0] <= t) {
                    if(min > jobs[i][1]) {
                        min = jobs[i][1];
                        minI = i;
                    }
                } else {
                    break;
                }
            }
            if(minI == -1) {
                t++;
                ans++;
                continue;
            }
            v[minI] = true;
            ans += (t - jobs[minI][0] + jobs[minI][1]);
            t += min;
            n++;
        }

        return ans/N;
    }
}
