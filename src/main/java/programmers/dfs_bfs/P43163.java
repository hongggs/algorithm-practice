package programmers.dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class P43163 {
    static class Node {
        String next;
        int edge;

        public Node(String next, int edge) {
            this.next = next;
            this.edge = edge;
        }
    }
    public int solution_better(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length;

        Queue<Node> q = new LinkedList<>();

        boolean[] visit = new boolean[n];
        q.add(new Node(begin, 0));

        while(!q.isEmpty()) {
            Node curr = q.poll();
            if(curr.next.equals(target)) {
                answer = curr.edge;
                break;
            }

            for(int i = 0; i < n; i++) {
                if(!visit[i] && isValid(curr.next, words[i])) {
                    visit[i] = true;
                    q.add(new Node(words[i], curr.edge + 1));
                }
            }
        }

        return answer;
    }

    public int solution(String begin, String target, String[] words) {
        if(!hasTarget(target, words)) {
            return 0;
        }

        int answer = Integer.MAX_VALUE;
        int temp = 0;
        String curr = begin;
        for(String word : words) {
            if(isValid(curr, target)) {
                temp++;
                answer = Math.min(answer, temp);
                curr = begin;
                continue;
            }

            if(isValid(curr, word)) {
                temp++;
                curr = word;
            } else {
                temp = 0;
                curr = begin;
            }
        }

        if(answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        return answer;
    }
    public boolean isValid(String a, String b) {
        int cnt = 0;
        for(int i = 0; i < a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                cnt++;
            }
        }
        if(cnt > 1) {
            return false;
        }
        return true;
    }

    public boolean hasTarget(String target, String[] words) {
        for(String word : words) {
            if(target.equals(word)) {
                return true;
            }
        }
        return false;
    }

}
