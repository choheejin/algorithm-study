import java.util.*;
import java.lang.Math;

class Solution {    
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        
        int start = 1;
        int cover = w * 2 + 1;
        
        for(int station: stations) {
            int left = Math.max(1, station - w);
            int right = Math.min(n, station + w);
            
            int gap = left - start;
            
            answer += (gap + cover - 1) / cover;
            start = right + 1;
            if(start > n) break;
        
        }
        
        if(start <= n) {
            int gap = n - start + 1;
            answer += (gap + cover - 1) /cover;
        }
        
        return answer;
    }
}