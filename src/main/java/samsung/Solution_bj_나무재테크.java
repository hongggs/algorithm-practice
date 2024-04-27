package samsung;

/**
 * N*N 땅
 * r,c 1부터 시작
 *
 * 로보이 해당 땅의 양분을 조사해 상도에게 전송
 * 가장 처음에는 양분은 모든 칸의 양분은 5만큼 있음
 *
 * 모묙을 구매해 어느정도 키운 후 팔아서 수익을 얻는 재태크
 *
 * M개의 나무를 구매
 * 봄에는 나무가 "자신의 나이만큼 양분을 먹고, 나이가 1 증가"
 * 하나의 칸에 여러개의 나무가 있다면, 어린 나무부터 양분을 먹는다.
 * 만약, 땅에 양분이 부족해 자신의 나이만큼 양분을 먹을 수 없으면 즉시 죽는다.
 *
 * 여름에는 봄에 죽은 나무가 양분으로 변한다.
 * 죽은 나무 나이를 2로 나눈 값이 나무가 있던 칸에 양분으로 추가, 소수점 아래는 버린다.
 *
 * 가을에는 나무가 번식한다.
 * 번식하는 나무는 나이가 5의 배수여야 하며, 인접한 8개의 칸에 나이가 1인 나무가 생긴다.
 *
 * 겨울에는 로봇이 땅의 양분을 추가 각 칸에 추가되는 양분의 양은 A[r][c]이고 입력으로 주어진다.
 */
import java.util.*;
import java.io.*;
public class Solution_bj_나무재테크 {
    static int N, M, K;
    static int[][] A;
    static int[][] map;
    static int[] dr = {-1,-1,-1,0,0,1,1,1};
    static int[] dc = {-1,0,1,-1,1,-1,0,1};

    static LinkedList<Tree> trees;
    static class Tree implements Comparable<Tree>{
        int r, c, old;

        public Tree(int r, int c, int old) {
            this.r = r;
            this.c = c;
            this.old = old;
        }
        public int compareTo(Tree t) {
            return old - t.old;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        A = new int[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = 5;
            }
        }

        trees = new LinkedList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int old = Integer.parseInt(st.nextToken());
            trees.add(new Tree(r, c, old));
        }

        System.out.println(solution());
    }

    static int solution() {
        while(K-- > 0) {
            //봄
            Queue<Tree> deads = new LinkedList<>();
            int size = trees.size();
            for(int i = 0; i < size; i++) {
                Tree t = trees.poll();
                if(t.old <= map[t.r][t.c]) {
                    map[t.r][t.c] -= t.old;
                    t.old++;
                    trees.add(t);
                } else {
                    deads.offer(t);
                }
            }
            //여름
            while(!deads.isEmpty()) {
                Tree t = deads.poll();
                map[t.r][t.c] += (t.old/2);
            }

            //가을
            size = trees.size();
            LinkedList babies = new LinkedList<>();
            for(Tree t : trees) {
                if(t.old % 5 == 0) {
                    for(int d = 0;  d < 8; d++) {
                        int nr = t.r + dr[d];
                        int nc = t.c + dc[d];
                        if(0 <= nr && nr < N && 0 <= nc && nc < N) {
                            babies.add(new Tree(nr, nc, 1));
                        }
                    }
                }
            }
            trees.addAll(0, babies);

            //겨울
            for(int i = 0;  i < N; i++) {
                for(int j = 0; j < N; j++) {
                    map[i][j] += A[i][j];
                }
            }
        }
        return trees.size();
    }
}
