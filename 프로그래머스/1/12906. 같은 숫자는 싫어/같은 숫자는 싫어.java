import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        Stack<Integer> stack = new Stack();
        
        for(int num : arr) {
            if(!stack.isEmpty() && stack.peek() == num) {
                continue;
            }
            else {
                stack.push(num);
            }
            // System.out.println(stack);
        }
        
        
        
        return stack.stream().mapToInt(Integer::intValue).toArray();
    }
}