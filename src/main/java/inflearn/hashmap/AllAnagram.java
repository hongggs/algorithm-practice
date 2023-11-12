package inflearn.hashmap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class AllAnagram {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();
        char[] t = br.readLine().toCharArray();

        HashMap<Character, Integer> mapS = new HashMap<>();
        HashMap<Character, Integer> mapT = new HashMap<>();

        for(char c : t) {
            mapT.put(c, mapT.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < t.length - 1; i++) {
            mapS.put(s[i], mapS.getOrDefault(s[i], 0) + 1);
        }

        int lt = 0;
        int rt = t.length - 1;
        int ans = 0;
        while(rt < s.length) {
            mapS.put(s[rt], mapS.getOrDefault(s[rt], 0) + 1);
            if(mapS.equals(mapT)) {
                ans++;
            }
            if(mapS.get(s[lt]) == 1) {
                mapS.remove(s[lt]);
            } else {
                mapS.put(s[lt], mapS.get(s[lt]) - 1);
            }
            rt++;
            lt++;
        }
        System.out.println(ans);
    }
}
