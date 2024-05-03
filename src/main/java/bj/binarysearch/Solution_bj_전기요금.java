package bj.binarysearch;

import java.util.*;
import java.io.*;
public class Solution_bj_전기요금 {
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while(true) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if(a == 0) {
                break;
            }
            int total = getWatt(a);
            int l = 0;
            int r = total / 2; //무조건 상근이가 적게 냈으니깐
            while(l <= r) { //상근이가 낸 값이 mid
                int mid = (l + r) / 2;

                int myPrice = getPrice(mid);
                int otherPrice = getPrice(total - mid);
                int diff = otherPrice - myPrice;
                if(diff < b) { //차이가 더 크니깐 상근이 값이 줄어들어 차이를 크게 해야 함 ->mid가 줄어들어야 됨
                    r = mid - 1;
                } else if(diff > b) { //차이가 더 작으니깐 상근이 값이 커져서 차이를 작게 해야 함 ->mid가 크게해야 됨
                    l = mid + 1;
                } else {
                    sb.append(getPrice(mid)).append("\n");
                    break;
                }
            }

        }
        System.out.println(sb);
    }

    static int getWatt(int price) {
        if (price <= 200) {
            return price / 2;
        } else if (price <= (9900 * 3) + 200) {
            return 100 + (price - 200) / 3;
        } else if (price <= (990000 * 5) + (9900 * 3) + 200) {
            return 10000 + (price - ((9900 * 3) + 200)) / 5;
        } else {
            return 1000000 + ((price - ((990000 * 5) + (9900 * 3) + 200)) / 7);
        }
    }

    static int getPrice(int watt) {
        if(watt <= 100) {
            return watt * 2;
        } else if(watt <= 10000) {
            return 200 + (watt - 100) * 3;
        } else if(watt <= 1000000) {
            return 200 + (9900 * 3) + (watt - 10000) * 5;
        } else {
            return 200 + (9900 * 3) + (990000 * 5 ) + (watt - 1000000) * 7;
        }
    }
}
