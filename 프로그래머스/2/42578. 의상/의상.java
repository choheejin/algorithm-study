import java.util.*;

class Solution {    
    static boolean[] isSelected;
    public int solution(String[][] clothes) {
        int answer = 1;
        
        isSelected = new boolean[clothes.length];
        
        HashMap<String, String> map = new HashMap();
        for(String[] cloth : clothes) {
            String value = cloth[0] + " " + map.getOrDefault(cloth[1], "");
            
            map.put(cloth[1], value);
        }
        
        List<Integer> list = new ArrayList<>();
        for(String key: map.keySet()) {
            int cnt = map.get(key).trim().split(" ").length;
            answer *= (cnt + 1);
        }
        
        
        return answer - 1;
    }
    
}