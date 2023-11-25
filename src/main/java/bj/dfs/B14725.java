package bj.dfs;

import java.io.*;
import java.util.*;

public class B14725 {
    static int N;

    static class Node {
        HashMap<String, Node> childs = new HashMap<>();
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        Node root = new Node();

        StringTokenizer st;
        for(int i = 0; i < N; i++) {
            Node cur = root;
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for (int j = 0; j < n; j++) {
                String value = st.nextToken();
                if(!cur.childs.containsKey(value)) {
                    cur.childs.put(value, new Node());
                }
                cur = cur.childs.get(value);
            }
        }

        print(root, "");

        br.close();
    }

    public static void print(Node root, String bar) {
        Object[] keys = root.childs.keySet().toArray();
        Arrays.sort(keys);
        for(Object key : keys) {
            System.out.println(bar + key);
            print(root.childs.get(key), bar + "--");
        }
    }
}