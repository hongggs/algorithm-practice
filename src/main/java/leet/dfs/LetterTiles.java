package leet.dfs;

import java.util.HashSet;

class LetterTiles {
    public int numTilePossibilities(String tiles) {
        HashSet<String> set = new HashSet<>();
        int n = tiles.length();
        boolean[] visit = new boolean[n];
        for(int m = 1; m <= n; m++) {
            solution("", set, visit, tiles, n);
        }
        return set.size() - 1;
    }

    public void solution(String str, HashSet<String> set, boolean[] visit, String tiles, int n) {
        set.add(str);
        for(int i = 0; i < n; i++) {
            if(visit[i]) {
                continue;
            }
            visit[i] = true;
            solution(str+tiles.charAt(i), set, visit, tiles, n);
            visit[i] = false;
        }
    }
}