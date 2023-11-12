package inflearn.queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SavePrincess {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        Queue<Integer> princes = new ArrayDeque<>();

        for(int i = 1; i <= n; i++){
            princes.offer(i);
        }

        while(!princes.isEmpty()) {
            for (int i = 0; i < k - 1; i++) {
                princes.offer(princes.poll());
            }
            princes.poll();
            if(princes.size()==1){
                System.out.println(princes.poll());
                return;
            }
        }
    }
}
