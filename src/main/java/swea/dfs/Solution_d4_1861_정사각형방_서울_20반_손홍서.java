package swea.dfs;

import java.io.*;
import java.util.*;

/**
 * <문제: 정사각형 방>
 * 2차원 배열에서 상하좌우 방향으로 현재 방에 적힌 숫자(방 번호)보다 정확히 1 큰 곳으로 이동할 수 있다.
 * 처음 어떤 방 번호에 있어야 가장 많은 개수의 방에 이동할 수 있는지를 찾아야 하는 문제!
 * 
 * 1. 전체 탐색하면서, 해당 좌표에서 이동할 수 있는 방의 개수 구하기
 * 2. 탐색한 이동 가능한 방의 개수가 기록해 놓은 최댓값보다 크다면 방 번호와, 최댓값 변경
 * 3. 만약 기록해 놓은 최댓값과 탐색 값이 같으면 방 번호 변경
 */
public class Solution_d4_1861_정사각형방_서울_20반_손홍서 {

	static final int[] dr = {-1,1,0,0}; //상하좌우
	static final int[] dc = {0,0,-1,1};
	
	static int N;
	static int[][] map;
	static int[][] dp;
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("res/input_d4_1861.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			
			/**
			 * 0. 입력
			 */
			//static 변수 초기화
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			dp = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			/**
			 * 1. 전체 탐색
			 */
			int maxMoveCnt = 0; // 최대 이동한 방 개수
			int roomNum = 0; //방 번호
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					/**
					 * 1. 방 개수 구하기
					 */
					int cnt = dfs(i, j);
//					int cnt = bfs(i, j);
//					int cnt = dfs_dp(i, j);
					
					if(cnt > maxMoveCnt) {
						/**
						 *  2. 탐색한 이동 가능한 방의 개수가 기록해 놓은 최댓값보다 크다면 방 번호와, 최댓값 변경
						 */
						roomNum = map[i][j];
						maxMoveCnt = cnt;
					} else if(cnt == maxMoveCnt && roomNum > map[i][j]) { 
						/**
						 * 3. 만약 기록해 놓은 최댓값과 탐색 값이 같으면 방 번호 변경
						 */
						roomNum = map[i][j];
					}
				}
			}
			sb.append(roomNum).append(" ").append(maxMoveCnt).append("\n");
		}
		System.out.println(sb);
	}
	
	
	static int dfs(int r, int c) {	
		for(int d = 0; d < dr.length; d++) { // 상하좌우 방면 탐색
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] - map[r][c] == 1) {
				// 좌표가 범위 안에 있고 & 현재 방에 적힌 숫자보다 1크다면 이동
				return 1 + dfs(nr, nc);
			}
		}
		return 1;
	}
	
	static int bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[]{r, c});
		
		int cnt = 0;
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			r = curr[0];
			c = curr[1];
			cnt++;
			for(int i = 0; i < dr.length; i++) { // 상하좌우 방면 탐색
				int nr = r + dr[i];
				int nc = c + dc[i];
				if(0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] - map[r][c] == 1) {
					// 좌표가 범위 안에 있고 & 현재 방에 적힌 숫자보다 1크다면 이동
					q.offer(new int[] {nr, nc});
				}
			}
		}
		return cnt;
	}
	
	/**
	 * 위의 dfs는 각 위치에서의 최대 이동 가능한 방의 개수를 구해야 하므로 방문 체크하지 않는다.
	 * 하지만 방문한 곳의 리턴값을 저장해 놓으면 중복 탐색할 필요가 없다.
	 */
	static int dfs_dp(int r, int c) {
		if(dp[r][c] != 0) { //중복 탐색 방지
			return dp[r][c];
		}
		
		for(int d = 0; d < dr.length; d++) { // 상하좌우 방면 탐색
			int nr = r + dr[d];
			int nc = c + dc[d];
			if(0 <= nr && nr < N && 0 <= nc && nc < N && map[nr][nc] - map[r][c] == 1) {
				// 좌표가 범위 안에 있고 & 현재 방에 적힌 숫자보다 1크다면 이동
				return dp[r][c] = 1 + dfs(nr, nc);
			}
		}
		return 1;
	}
}
