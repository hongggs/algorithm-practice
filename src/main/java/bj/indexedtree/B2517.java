package bj.indexedtree;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

/**
 * 달리기
 */
public class B2517 {
    static int N, offset;

    static PriorityQueue<Player> pq = new PriorityQueue<>();
    static int[] tree;
    static int[] result;
    static class Player implements Comparable<Player>{
        int index, grade;

        public Player(int index, int grade) {
            this.index = index;
            this.grade = grade;
        }

        @Override
        public int compareTo(Player p) {
            if(p.grade > grade) {
                return 1;
            }
            return -1;
        }
    }
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());

        initTree();

        for (int i = 0; i < N; i++) {
            int inputGrade = Integer.parseInt(br.readLine());
            pq.add(new Player(i, inputGrade));
        }

        result = new int[N];
        while(!pq.isEmpty()) {
            Player p = pq.poll();
            update(p.index, 1);
            result[p.index] = query(0, p.index);
        }
        for(int ans : result) {
            sb.append(ans+ "\n");
        }
        System.out.println(sb.toString());
    }

    static void initTree() {
        for (offset = 1; offset < N; offset *= 2);
        tree = new int[offset * 2 + 1];
    }

    static void update(int index, int value) {
        int realIndex = offset + index;
        tree[realIndex] = value;
        while(realIndex > 0) {
            realIndex /= 2;
            tree[realIndex] = tree[realIndex * 2] + tree[realIndex * 2 + 1];
        }
    }

    static int query(int start, int end) {
        int l = start + offset;
        int r = end + offset;
        int result = 0;
        while(l <= r) {
            if(l % 2 == 1) {
                result += tree[l++];
            }
            if(r % 2 == 0) {
                result += tree[r--];
            }
            l /= 2;
            r /= 2;
        }
        return result;
    }
}
