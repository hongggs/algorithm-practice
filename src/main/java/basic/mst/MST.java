//import java.util.ArrayDeque;
//import java.util.ArrayList;
//import java.util.Queue;
//
//public class MST {
//    int n = 0;
//    int m = 0;
//    ArrayList<Integer> edgeList[] = new ArrayList[n];
//    Queue<Integer> q = new ArrayDeque <>();
//
//    int indegree[];
//
//    public void input() {
//        for (int i = 1; i <= n; i++) {
//            edgeList[i] = new ArrayList<Integer>();
//        }
//
//        for (int i = 1; i <= m; i++) {
//            int startId = 0;
//            int targetId = 0;
//            edgeList[startId].add(targetId);
//            indegree[targetId] ++;
//        }
//
//        for(int i =1; i <= n; i++) {
//            if(indegree[i] == 0) {
//                q.add(i);
//            }
//        }
//
//    }
//
//    public void mst() {
//        while(!q.isEmpty()) {
//            int startId = q.poll();
//            int size = edgeList[startId].size();
//            for (int i = 0; i < size; i++) {
//                int targetId = edgeList[startId].get(i);
//
//                indegree[targetId] --;
//
//                if (indegree[targetId] == 0) {
//                    q.add(targetId);
//                }
//            }
//        }
//
//    }
//}
