import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Stack<Integer> stack = new Stack<>();
        int orderIndex = 0;
        int boxNum = 1;
        while(orderIndex < order.length) {
            if(order[orderIndex] == boxNum) {
                orderIndex++;
                boxNum++;
                answer++;
            } else if(!stack.isEmpty() && order[orderIndex] == stack.peek()) {
                orderIndex++;
                stack.pop();
                answer++;
            } else if (boxNum <= order.length) {
                stack.push(boxNum);
                boxNum++;
            } else {
                break;
            }
        }
        return answer;
    }
}