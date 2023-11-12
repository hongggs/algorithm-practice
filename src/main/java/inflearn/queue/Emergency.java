package inflearn.queue;

import java.util.*;
import java.io.*;

class Emergency {
    static class Patient{
        int index;
        int dangerLevel;

        Patient(int index, int dangerLevel) {
            this.index = index;
            this.dangerLevel = dangerLevel;}


    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Queue<Patient> q = new LinkedList<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            int dangerLevel = Integer.parseInt(st.nextToken());
            q.offer(new Patient(i, dangerLevel));
        }

        int ans = 1;
        while(!q.isEmpty()) {
            Patient p = q.poll();
            boolean isMax = true;
            for(Patient compare : q) {
                if(compare.dangerLevel > p.dangerLevel) {
                    q.offer(p);
                    isMax = false;
                    break;
                }
            }
            if(isMax && p.index == m) {
                System.out.println(ans);
                return;
            } else if(isMax) {
                ans++;
            }
        }

    }
}
