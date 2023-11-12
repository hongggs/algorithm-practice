package inflearn.greedy;

import java.util.*;
import java.io.*;

class SelectPlayer {
    static int N;
    static ArrayList<Applicant>  applicants = new ArrayList<>();

    static class Applicant implements Comparable<Applicant>{
        int height, weight;
        Applicant (int height, int weight) {
            this.height = height;
            this.weight = weight;
        }

        public int compareTo(Applicant a) {
            if (a.height > height) {
                return 1;
            }
            return -1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            applicants.add(new Applicant(height, weight));
        }

        System.out.println(solution());


    }

    public static int solution() {
        int ans = 0;
        Collections.sort(applicants);
        int max = 0;
        for(Applicant a : applicants) {
            if(a.weight > max) {
                max = a.weight;
                ans++;
            }
        }

        return ans;
    }
}
