/*
최단거리=>bfs
1. 출발 지점에서 레버를 당기는 칸으로 이동해 레버당김
    1-1. 이때 출구를 지나칠 수 있다.
2. 문이 있는 칸으로 가기
*/
import java.util.*;
class Solution {
    String[] maps;
    public int solution(String[] maps) {
        this.maps = maps;
        int temp = bfs(getStart('S'), 'L');
        if(temp == -1) {
            return -1;
        }    
        int answer = temp;
        temp = bfs(getStart('L'), 'E');
        if(temp == -1) {
            return -1;
        }
        answer += temp;
  
        return answer;
    }
    
    int[] getStart(char c) {
        int[] result = new int[3];
        for(int i = 0; i < maps.length; i++) {
            for(int j = 0; j < maps[0].length(); j++) {
                if(maps[i].charAt(j) == c) {
                    result[0] = i;
                    result[1] = j;
                    result[2] = 0;
                    return result;
                }
            }
        }
        return result;
    }
    
    int bfs(int[] start, char end) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(start);
        boolean[][] v = new boolean[maps.length][maps[0].length()];
        v[start[0]][start[1]] = true;
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            for(int i = 0; i < 4; i++) {
                int nr = now[0] + dr[i];
                int nc = now[1] + dc[i];
                if(0 <= nr && nr < maps.length && 0 <= nc && nc < maps[0].length() 
                   && !v[nr][nc] && maps[nr].charAt(nc) != 'X') {
                    if(maps[nr].charAt(nc) == end) {
                        return now[2] + 1;
                    }
                    q.offer(new int[]{nr, nc, now[2] + 1});
                    v[nr][nc] =true;
                }
            }
        }
        return -1;
    }
        
}