// 조합
import java.util.*;
import java.io.*;

class Solution {
    static boolean[] isSelected;
    static int[] order, realOrder;
    static HashSet<Integer> set = new HashSet();
        
    public int solution(String numbers) {
        int answer = 0;
        
        int[] nums = Arrays.stream(numbers.split("")).mapToInt(Integer::parseInt).toArray();
        
        
        // perm(nums, 0);
        
        for(int i = 1; i <= nums.length; i++) {
            order = new int[i];
            combi(nums, nums.length, 0, i);
        }
        
        for(int n : set) {
            if(check(n)) answer++;
        }
        
        return answer;
    }
    
    public boolean check(int n) {
        if(n < 2) return false;
        for(int i = 2; i <= Math.sqrt(n); i++) {
            if(n % i == 0) return false;
        }
        return true;
    }
    
    public void perm (int cnt) {
        if(cnt == realOrder.length) {
            StringBuilder sb = new StringBuilder();
            
            for(int i = 0; i < realOrder.length; i++) {
                sb.append(realOrder[i]);    
            }
            
            // System.out.println(sb);
            set.add(Integer.parseInt(sb.toString()));
            return;
        }
        
        for(int i = 0; i < realOrder.length; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            realOrder[cnt] = order[i];
            perm(cnt+1);
            isSelected[i] = false;
        }
    }
    
    
    public void combi (int[] nums, int n, int start, int r) {
        if(r == 0) {
            isSelected = new boolean[order.length];
            realOrder = new int[order.length];
            perm(0);
            return;
        }
        
        for(int i = start; i < n; i++) {
            order[order.length - r] = nums[i];
            combi(nums, n, i + 1, r - 1);
        }
    }
    
}