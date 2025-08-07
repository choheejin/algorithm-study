import java.util.*;

class Solution {
    public static HashSet<Integer> nums = new HashSet();
    
    public int solution(int[] stones, int k) {
        int answer = 0;
        int[] dStones = new int[stones.length];
        
        System.arraycopy(stones, 0, dStones, 0, stones.length);
        Arrays.sort(dStones);
        
        int start = 0;
        int end = dStones.length - 1;
        
        while(start < end) {
            int target = (start + end) / 2;
            
            int cnt = 0;
            int tmp = 0;
            // dStones[target] 보다 같거나 작은 값들이 연속되는 횟수를 구한다.
            for(int i = 0; i < stones.length; i++) {
                if(stones[i] - dStones[target] > 0) {
                    cnt = Math.max(cnt, tmp);
                    tmp = 0;
                } else {
                    tmp++;
                }                
            }
            cnt = Math.max(cnt, tmp);
            
            // System.out.println(target + "," + cnt);
            
            if(cnt >= k) {
                end = target;
            } else {
                start = target + 1;
            }
        }
        
        return dStones[end];
    }
    
}