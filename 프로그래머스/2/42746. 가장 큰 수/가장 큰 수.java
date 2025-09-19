import java.util.*;
import java.lang.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        int n = numbers.length;
        
        Integer[] nums = new Integer[n];
        
        for(int i = 0; i < n; i++) {
            nums[i] = numbers[i];    
        }
        
        Arrays.sort(nums, (a, b) -> {
            String s1 = "" + a + "" + b;
            String s2 = "" + b + "" + a;
            

            return Integer.compare(Integer.parseInt(s2), Integer.parseInt(s1));
        });
        
        // System.out.println(Arrays.toString(nums));
        
        int maxValue = 0;
        for(int i : nums) {
            answer += i;
            maxValue = Math.max(maxValue, i);
        }
        
        // 2, 20, 22
        // long tmp = Long.parseLong(answer);
        
        return maxValue == 0 ? "0" : answer;
    }
}