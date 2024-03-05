package samsung;

import java.io.*;
import java.util.*;

/**
 * N은 0
 * S는 1
 * [1][3]/ [2][7], [2][3] / [3][7], [3][3]/ [4][7]
 */
public class Solution_bj_14891_톱니바퀴_서울_20반_손홍서 {
    static int[][] arr;
    static int ans;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        arr = new int[4 + 1][8 + 1];
        for(int i = 1; i <= 4; i++) {
            String s = br.readLine();
            for(int j = 1; j <= 8; j++) {
                arr[i][j] = s.charAt(j - 1) - '0';
            }
        }

        int m = Integer.parseInt(br.readLine());
        ans = 0;
        int[] order = new int[4];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            Arrays.fill(order, 0);
            switch(k) {
                case 1:
                    order[0] = d;
                    if(arr[1][3] != arr[2][7]) {
                        order[1] = -(order[0]);
                    }
                    if(arr[2][3] != arr[3][7]) {
                        order[2] = -(order[1]);
                    }
                    if(arr[3][3] != arr[4][7]) {
                        order[3] = -(order[2]);
                    }
                    break;
                case 2:
                    order[1] = d;
                    if(arr[1][3] != arr[2][7]) {
                        order[0] = -(order[1]);
                    }
                    if(arr[2][3] != arr[3][7]) {
                        order[2] = -(order[1]);
                    }
                    if(arr[3][3] != arr[4][7]) {
                        order[3] = -(order[2]);
                    }
                    break;
                case 3:
                    order[2] = d;
                    if(arr[2][3] != arr[3][7]) {
                        order[1] = -(order[2]);
                    }
                    if(arr[3][3] != arr[4][7]) {
                        order[3] = -(order[2]);
                    }
                    if(arr[1][3] != arr[2][7]) {
                        order[0] = -(order[1]);
                    }
                    break;
                case 4:
                    order[3] = d;
                    if(arr[3][3] != arr[4][7]) {
                        order[2] = -(order[3]);
                    }
                    if(arr[2][3] != arr[3][7]) {
                        order[1] = -(order[2]);
                    }
                    if(arr[1][3] != arr[2][7]) {
                        order[0] = -(order[1]);
                    }
                    break;

            }

            for(int j = 0; j < 4; j++) {
                move(j + 1, order[j]);
            }


        }
        for(int i = 1; i <= 4; i++) {
            if(arr[i][1] == 1) {
                ans += Math.pow(2, (i - 1));
            }
        }
        System.out.println(ans);
    }

    static void move(int r, int d) {
        if(d == -1) {
            int temp = arr[r][1];
            for(int i = 1; i < 8; i++) {
                arr[r][i] = arr[r][i + 1];
            }
            arr[r][8] = temp;
        } else if(d == 1) {
            int temp = arr[r][8];
            for(int i = 8; i > 1; i--) {
                arr[r][i] = arr[r][i - 1];
            }
            arr[r][1] = temp;
        }
    }
}