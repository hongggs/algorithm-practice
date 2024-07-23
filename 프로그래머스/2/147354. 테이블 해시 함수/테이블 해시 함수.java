import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        Arrays.sort(data, (a, b) -> {
            if(a[col - 1] == b[col - 1]) {
                return b[0] - a[0];
            } else {
                return a[col - 1] - b[col - 1];
            }
        });
        
        int ans = 0;
        for(int j = 0; j < data[0].length; j++) {
            ans += (data[row_begin - 1][j]  % row_begin);
        }
        for(int i = row_begin; i < row_end; i++) {
            int temp = 0;
            for(int j = 0; j < data[0].length; j++) {
                temp += (data[i][j] % (i + 1));
            }
            ans ^= temp;        
        }
        return ans;
    }
}