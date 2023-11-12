package inflearn.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentPresident {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        int n = in.nextInt();
        char[] vote = in.next().toCharArray();
        Map<Character, Integer> result = new HashMap<>();
        result.put('A', 0);
        result.put('B', 0);
        result.put('C', 0);
        result.put('D', 0);
        result.put('E', 0);
        for(char v : vote) {
            result.put(v, result.getOrDefault(v, 0) + 1);
        }

        char ans = 'N';
        int total = 0;

        for(char k : result.keySet()) {
            if(result.get(k) > total) {
                ans = k;
                total = result.get(k);
            }
        }

        System.out.println(ans);
    }
}
