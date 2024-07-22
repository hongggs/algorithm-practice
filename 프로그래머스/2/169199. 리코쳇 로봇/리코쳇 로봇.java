import java.util.*;
class Solution {
    public int solution(String[] board) {
        return bfs(board);
    }
    
    public int bfs(String[] board) {
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};
        int N = board.length;
        int M = board[0].length();
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][][] v = new boolean[N][M][4];
        int[] start = new int[2];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i].charAt(j) == 'R') {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            q.offer(new int[]{start[0], start[1], 0, i});
            v[start[0]][start[1]][i] = true;
        }
        
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r = now[0];
            int c = now[1];
            
            while(true) {
                if(0 > (r + dr[now[3]]) || (r + dr[now[3]]) >= N || 
                   0 > (c + dc[now[3]]) || (c + dc[now[3]]) >= M || 
                  board[r + dr[now[3]]].charAt(c + dc[now[3]]) == 'D') {
                    break;
                }
                r += dr[now[3]];
                c += dc[now[3]];
            }
            for(int i = 0; i < 4; i++) {
                if(board[r].charAt(c) == 'G') {
                    return now[2] + 1;
                }
                if(!v[r][c][i]) {
                    v[r][c][i] = true;
                    q.offer(new int[]{r,c,now[2]+1,i});
                }
            }
        }
        return -1;
    }
    
}