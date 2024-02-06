package swea.tree;

import java.io.*;
import java.util.*;

public class S1233 {

    static int N;
    static String[] tree;
    public static void main(String[] args) throws Exception{
        System.setIn(new FileInputStream("res/input_d4_1233.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

//		int T = Integer.parseInt(br.readLine());
        int T = 10;
        for(int t = 1; t <= T; t++) {
            sb.append("#").append(t).append(" ");

            N = Integer.parseInt(br.readLine());
            tree = new String[N + 1];

            for(int i = 1; i <= N; i++) {

                st = new StringTokenizer(br.readLine());
                st.nextToken();
                for(int j = 0; j < st.countTokens(); j++) {
                    switch(j) {
                        case 0:
                            tree[i] = st.nextToken();
                            break;
                        case 1:
                            tree[i * 2] = st.nextToken();
                            break;
                        case 2:
                            tree[i * 2 + 1] = st.nextToken();
                            break;
                    }
                }
            }

            int ans = 1;
            if(N == 1 && isOperator(tree[1])) {
                sb.append("1\n");
                continue;
            }
            if(N % 2 == 0 || !isOperator(tree[1])) {
                sb.append("0\n");
                continue;
            }

            for(int i = N - 1; i >= 2; i-=2) {
                if(isOperator(tree[i]) || isOperator(tree[i + 1]) || !isOperator(tree[i/2])) {
                    ans = 0;
                    break;
                }
                tree[i/2] = "0";
            }

            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }

    static boolean isOperator(String s) {
        return s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/");
    }
}

