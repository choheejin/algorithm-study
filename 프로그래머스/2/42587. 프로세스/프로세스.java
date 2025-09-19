import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
                
        Queue<int[]> q = new ArrayDeque<int[]>();
        
        for(int i = 0; i < priorities.length; i++) {
            q.offer(new int[] {i, priorities[i]});
        }
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            boolean flag = false;
            for(int i = 0; i < priorities.length; i++) {
                if(curr[1] < priorities[i]) {
                    flag = true;
                    break;
                }
            }
            
            // 다시 집어 넣어야 함
            if(flag) {
                q.offer(curr);
                // System.out.println("다시 집어넣음");
            } 
            // 내가 최대
            else {
                priorities[curr[0]] = -1;
                cnt++;
                if(curr[0] == location) return cnt;
                // System.out.println(Arrays.toString(curr));
            }
        }
        
        return 0;
    }
}