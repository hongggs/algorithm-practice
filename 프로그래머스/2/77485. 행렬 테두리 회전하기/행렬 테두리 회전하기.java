import java.util.*;
class Solution {
    int[][] map;
    public int[] solution(int rows, int columns, int[][] queries) {
        map = new int[rows][columns];
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                map[i - 1][j - 1] = j + (i - 1) * columns;
            }
        }
        
        ArrayList<Integer> ans = new ArrayList<>();
        for(int i = 0; i < queries.length; i++) {
            ans.add(rotate(queries[i]));
        }
        
        return ans.stream().mapToInt(Integer::intValue).toArray();
    }
    
    int rotate(int[] arr) {
        for(int i = 0; i < 4; i++) {
            arr[i]--;
        }
        
        int h = arr[2] - arr[0] + 1;
        int w = arr[3] - arr[1] + 1;
        
        int temp = map[arr[0]][arr[1]];
        int result = temp;
        //h1(상)
        for(int i = 0; i < h - 1; i++) {
            map[arr[0] + i][arr[1]] = map[arr[0] + i + 1][arr[1]];
            result = Math.min(result, map[arr[0] + i][arr[1]]);
        }
        
        //w2(왼)
        for(int i = 0; i < w - 1; i++) {
            map[arr[2]][arr[1] + i] = map[arr[2]][arr[1] + i + 1];
            result = Math.min(result, map[arr[2]][arr[1] + i]);
        }
        
        //h2(하)
        for(int i = 0; i < h - 1; i++) {
            map[arr[2] - i][arr[3]] = map[arr[2] - i - 1][arr[3]];
            result = Math.min(result, map[arr[2] - i][arr[3]]);
        }
        
        //w1(오)
        for(int i = 0; i < w - 2; i++) {
            map[arr[0]][arr[3] - i] = map[arr[0]][arr[3] - i - 1];
            result = Math.min(result, map[arr[0]][arr[3] - i]);
        }
        
        map[arr[0]][arr[3] - w + 2] = temp;
        return result;
    }
}