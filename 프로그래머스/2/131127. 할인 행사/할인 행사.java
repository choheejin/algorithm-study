import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        HashMap<String, Integer> wants = new HashMap<>();
        
        for(int i = 0; i < want.length; i++) {
            wants.put(want[i], number[i]);
        }
        
        for(int i = 0; i <= discount.length - 10; i++) {
            HashMap<String, Integer> compare = new HashMap<>();
            boolean flag = false;
            
            for(int j = i; j < i+10; j++) {
                compare.put(discount[j], compare.getOrDefault(discount[j], 0) + 1);
            }
            
            for(String key: wants.keySet()) {
                if(wants.get(key) > compare.getOrDefault(key, 0)) {
                    flag = true;
                }
            }
            
            if(!flag) answer++;
        }
        
        return answer;
    }
}