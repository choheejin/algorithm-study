import java.util.*;

class Solution {
    public String solution(String[] cards1, String[] cards2, String[] goal) {
        String answer = "Yes";
        
        Queue<String> q1 = new ArrayDeque<>();
        Queue<String> q2 = new ArrayDeque<>();
        
        for(String card1: cards1) {
            q1.offer(card1);
        }
        
        for(String card2: cards2) {
            q2.offer(card2);
        }
        
        
        for(String g: goal) {
            if(!q1.isEmpty() && q1.peek().equals(g)) {
                q1.poll();
                continue;
            } else if (!q2.isEmpty() && q2.peek().equals(g)) {
                q2.poll();
                continue;
            }
            else {
                answer = "No";
                break;
            }
        }
        
        
        return answer;
    }
}