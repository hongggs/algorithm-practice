import java.util.*;

class Solution {
    int[] dr = {0, 0, -1, 1};
    int[] dc = {-1, 1, 0, 0};
    boolean[][] v;
    String[] maps;
    int H, W;
    int sum;
    public int[] solution(String[] maps) {
        this.maps = maps;
        H = maps.length;
        W = maps[0].length();
        v = new boolean[H][W];
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < H; i++) {
            for(int j = 0; j < W; j++) {
                if(!v[i][j] && maps[i].charAt(j) != 'X') {
                    v[i][j] = true;
                    sum = (maps[i].charAt(j) - '0');
                    dfs(i, j);
                    ans.add(sum);
                }
            }
        }
        
        Collections.sort(ans);
        if(ans.isEmpty()) {
            return new int[]{-1};
        } else {
            return ans.stream().mapToInt(Integer::intValue).toArray();
        }
    }
    
    void dfs(int r, int c) {
        for(int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if(0 <= nr && nr < H && 0 <= nc && nc < W && !v[nr][nc] && maps[nr].charAt(nc) != 'X') {
                v[nr][nc] = true;
                sum += (maps[nr].charAt(nc) - '0');
                dfs(nr, nc);
            }
        }
    }
}