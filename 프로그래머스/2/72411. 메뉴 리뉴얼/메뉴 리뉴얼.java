import java.util.*;

class Solution {
    static String[] orders;
    static HashMap<String, Integer> combiResult;
    static int max;
    
    // ** 내가 생각못했던 제약사항은 가장 많이 주문된 횟수라는 것
    // 1. 가능한 조합을 모두 만든다. -> 단, 만들때 중복 없애고 만들자~;
    // 2. 다 만든 조합에 대해 order.contains() 확인하고, hashmap에 저장해준다
    public String[] solution(String[] orders, int[] course) {
        List<String> answer = new ArrayList<>();
        
        // System.out.println(String.valueOf("A".charAt(0) + 1));
        // combi(0, 0, 3, "");
        int prev = -1;

        for(int c: course) {
            if(prev == c) continue;
            
            max = Integer.MIN_VALUE;
            combiResult = new HashMap<>();
            combi(0, 0, c, "", orders);
            
            for(String key: combiResult.keySet()) {
                if(combiResult.get(key) != max || combiResult.get(key) < 2) continue;
                answer.add(key);
            }
                
            prev = c;
        }
        
        Collections.sort(answer);
        
        return answer.toArray(new String[answer.size()]);
    }
    
    public static void combi(int start, int cnt, int n, String value, String[] orders) {
        if(cnt == n) {
            A: for(String order: orders) {
                
                for(int i = 0; i < value.length(); i++) {
                    if(!order.contains(String.valueOf(value.charAt(i)))) continue A;
                }
                
                max = Math.max(max, combiResult.getOrDefault(value, 0) + 1);
                combiResult.put(value, combiResult.getOrDefault(value, 0) + 1);
            }
            return;
        }
        
        for(int i = start; i < 26; i++) {
            combi(i+1, cnt+1, n, value + String.valueOf((char) ("A".charAt(0) + i)), orders);
        }
    }
}