import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        ArrayList<Integer> list = new ArrayList<>();
        
        Queue<Integer> q = new ArrayDeque();
        
        for(int i = 0; i < progresses.length; i++) {
            int p = 100 - progresses[i];
            int s = speeds[i];
            
            int t = (p + s - 1) / s;
            
            if(!q.isEmpty() && q.peek() < t) {
                list.add(q.size());
                q = new ArrayDeque();
            }
            q.offer(t);
        }
        
        if(q.size() > 0) {
            list.add(q.size());
        }
        
        return list.stream().mapToInt(Integer::intValue).toArray();
    }
}