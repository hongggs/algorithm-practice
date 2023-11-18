package swea;

import java.io.*;
import java.util.*;

public class S1208 {
    static int n = 100;
    static int[] arr;
    static int[] top;
    static int count;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = 10;

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            count = Integer.parseInt(br.readLine());
            arr = new int[n];

            top = new int[n + 1];
            int max = -1;
            int min = 101;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
                if(max < arr[i]) {
                    max = arr[i];
                }
                if (min > arr[i]) {
                    min = arr[i];
                }
                top[arr[i]]++;
            }

//            int ans = solution();
            int ans = optimizedSolution(max, min);
            sb.append(ans).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    public static int solution() {
        int diff = Integer.MAX_VALUE;
        ArrayList<Integer> maxNums = new ArrayList<>();
        ArrayList<Integer> minNums = new ArrayList<>();

        while(diff > 1 && count > 0) {
            int max = 0;
            int min = 101;

            maxNums.clear();
            minNums.clear();
            for(int i = 0; i < n; i++) {
                if(arr[i] > max) {
                    max = arr[i];
                    maxNums.clear();
                    maxNums.add(i);
                } else if(arr[i] == max){
                    maxNums.add(i);
                }

                if(arr[i] < min) {
                    min = arr[i];
                    minNums.clear();
                    minNums.add(i);
                } else if(arr[i] == min){
                    minNums.add(i);
                }
            }

            while (count > 0 && !minNums.isEmpty() && !maxNums.isEmpty()) {
                arr[minNums.get(0)]++;
                arr[maxNums.get(0)]--;
                minNums.remove(0);
                maxNums.remove(0);
                count--;
            }

            if(count == 0 && !minNums.isEmpty() && !maxNums.isEmpty()) {
                diff = max - min;
            }
            else if(!minNums.isEmpty() || !maxNums.isEmpty()) {
                diff = max - min - 1;
            } else {
                diff = (max - 1) - (min + 1);
            }
        }
        return diff;
    }

    public static int optimizedSolution(int max, int min) {
        int diff = 0;
        while(count > 0) {
            count--;
            if(max == min) {
                return 0;
            }
            if (max - min == 1) {
                return 1;
            }

            diff = max - min;

            top[max]--;
            top[min]--;
            top[(max - 1)]++;
            top[(min + 1)]++;

            if(top[max] <= 0) {
                int nextMax = max;
                while(true) {
                    nextMax--;
                    if(top[nextMax] != 0) {
                        max = nextMax;
                        break;
                    }
                }
            }

            if(top[min] <= 0) {
                int nextMin = min;
                while(true) {
                    nextMin++;
                    if(top[nextMin] != 0) {
                        min = nextMin;
                        break;
                    }
                }
            }
        }
        return diff;
    }
}