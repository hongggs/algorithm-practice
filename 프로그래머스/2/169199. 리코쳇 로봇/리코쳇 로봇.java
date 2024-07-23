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
        boolean[][] v = new boolean[N][M];
        int[] start = new int[2];
        flag: for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(board[i].charAt(j) == 'R') {
                    start[0] = i;
                    start[1] = j;
                    break flag;
                }
            }
        }
        q.offer(new int[]{start[0], start[1], 0});
        v[start[0]][start[1]] = true;
        while(!q.isEmpty()) {
            int[] now = q.poll();
            int r, c;
            for(int i = 0; i < 4; i++) {
                r = now[0];
                c = now[1];
                while(true) {
                if(0 > (r + dr[i]) || (r + dr[i]) >= N || 
                   0 > (c + dc[i]) || (c + dc[i]) >= M || 
                  board[r + dr[i]].charAt(c + dc[i]) == 'D') {
                    break;
                }
                    r += dr[i];
                    c += dc[i];
                }
                if(board[r].charAt(c) == 'G') {
                    return now[2] + 1;
                }
                
                if(!v[r][c]) {
                    v[r][c] = true;
                    q.offer(new int[]{r, c, now[2] + 1});
                }
            }
        }
        return -1;
    }
    
}