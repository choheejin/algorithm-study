import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
        // 남은 작업의 수
        Queue<Integer> q = new ArrayDeque<>();
        
        for(int i = 0; i < progresses.length; i++) {
            int tmp = (100 - progresses[i]) / speeds[i];
            // 나눠 떨어지지 않는 경우 1을 더해준다
            
            q.offer((100 - progresses[i]) / (float) speeds[i] != (float) tmp ? tmp + 1 : tmp);
        }

        while(!q.isEmpty()) {
            int cnt = 1;
            int front = q.poll();

            while(!q.isEmpty() && front >= q.peek()) {
                q.poll();
                cnt++;
            }
            answer.add(cnt);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}