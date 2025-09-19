import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 1;
        
        Queue<Integer> q = new ArrayDeque();
        
        int idx = 0; 
        int w = 0;
        
        // 기준을 잘 잡자. time 이 기준
        int time = 0;
        int remain = truck_weights.length;
        while(remain > 0) {
            if(q.size() >= bridge_length) {
                int remove = q.poll();
                w -= remove;
                if(remove != 0) remain--;
            }
            
            // if(idx >= truck_weights.length) break;
            time++;

            if(idx < truck_weights.length && w + truck_weights[idx] <= weight) {
                q.offer(truck_weights[idx]);
                w += truck_weights[idx];
                idx++;
            } else {
                q.offer(0);
            }
            
            // System.out.println(q);
        }
        
        // if(remain > 0) {
        //     // System.out.println(time);
        //     // System.out.println(q.size());
        //     // // System.out.println(answer);
        //     // System.out.println(remain);
        //     time += bridge_length - q.size() + (1 * remain);
        // }
        
                
        return time;
    }
}