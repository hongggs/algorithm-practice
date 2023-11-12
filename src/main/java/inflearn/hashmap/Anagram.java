package inflearn.hashmap;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Anagram {
    public static void main(String[] args){
        Scanner in=new Scanner(System.in);
        char[] c1 = in.next().toCharArray();
        char[] c2 = in.next().toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for(char c : c1) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        String ans = "YES";
        for(char c : c2) {
            if(!map.containsKey(c) || map.get(c) == 0) {
                ans = "NO";
            } else {
                map.put(c, map.get(c) - 1);
            }

        }

        System.out.println(ans);
    }
}
