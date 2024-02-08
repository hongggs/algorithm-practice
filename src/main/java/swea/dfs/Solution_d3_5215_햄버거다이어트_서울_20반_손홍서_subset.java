package swea.dfs;
import java.io.*;
import java.util.*;

public class Solution_d3_5215_햄버거다이어트_서울_20반_손홍서_subset {
    static class Food {
        int score;
        int calorie;

        public Food(int score, int calorie) {
            this.score = score;
            this.calorie = calorie;
        }
    }

    static int N;
    static int L;
    static Food[] foods;
    static int maxScore;
    static int maxCal;

    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_d3_5215.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            foods = new Food[N];

            maxScore = 0;
            maxCal = 0;
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int score = Integer.parseInt(st.nextToken());
                int calorie = Integer.parseInt(st.nextToken());
                foods[i] = new Food(score, calorie);
            }
            subset(0, 0, 0);
            sb.append(maxScore).append("\n");
        }
        System.out.println(sb);
        br.close();
    }

    static void subset(int cnt, int sumS, int sumC) {
        if(sumC > L) {
            return;
        }

        if(cnt == N) {
            if(sumS > maxScore) {
                maxScore = sumS;
            }
            return;
        }

        subset(cnt + 1, sumS + foods[cnt].score, sumC + foods[cnt].calorie);
        subset(cnt + 1, sumS, sumC);
    }

}
