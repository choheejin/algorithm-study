import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int[] citations) {
        int n = citations.length;
        int h = 0;
        
        Arrays.sort(citations);
        
        for(int i = 0; i < n; i++) {
            int tmp = Math.min(citations[i], n - i);
            h = Math.max(tmp, h);
        }
        
        return h;
    }
}