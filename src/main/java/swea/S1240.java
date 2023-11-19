package swea;

import java.io.*;
import java.util.*;

public class S1240 {
    static int n;
    static int m;
    static char[] secretCode;
    static int startIndex;

    static Map<String, Integer> numberMap = new HashMap<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            boolean hasSecretCode = false;
            startIndex = -1;
            for(int i = 0; i < n; i++) {
                if(hasSecretCode) {
                    br.readLine();
                    continue;
                }
                char[] temp = br.readLine().toCharArray();
                for(int j = 0; j < m; j++) {
                    if(temp[j] == '1') {
                        hasSecretCode = true;
                        secretCode = temp;
                        startIndex = j - 1;
                        break;
                    }
                }
            }

            int ans = solution();
            sb.append(ans).append("\n");
        }
        bw.write(sb.toString());
        br.close();
        bw.close();
    }

    public static int solution() {
        int lastIndex = -1;
        initMap();
        //마지막 '1'위치 찾기
        for(int i = m - 1; i >= 0; i--) {
            if(secretCode[i] == '1') {
                lastIndex = i;
                break;
            }
        }

        //암호 시작 위치 알아내기
        //1) 0이 1개인 경우
        int len = lastIndex - startIndex + 1;
        if (len == 55) {
            //2) 0이 2개인 경우
            startIndex -= 1;
        } else if(len == 54) {
            //3) 0이 3개인 경우
            startIndex -= 2;
        }

        int[] num = new int[8];
        int odd = 0;
        int even = 0;
        for(int i = 0; i < 8; i++) {
            int s = startIndex + (i * 7);
            String temp = "";
            for(int j = s; j < s + 7; j++) {
                temp += secretCode[j];
            }
            num[i] = numberMap.get(temp);
            if(i % 2 == 1) {
                even += num[i];
            } else {
                odd += num[i];
            }
        }

        if((odd * 3 + even) % 10 == 0) {
            return odd + even;
        } else {
            return 0;
        }
    }

    private static void initMap() {
        numberMap.put("0001101", 0);
        numberMap.put("0011001", 1);
        numberMap.put("0010011", 2);
        numberMap.put("0111101", 3);
        numberMap.put("0100011", 4);
        numberMap.put("0110001", 5);
        numberMap.put("0101111", 6);
        numberMap.put("0111011", 7);
        numberMap.put("0110111", 8);
        numberMap.put("0001011", 9);
    }
}