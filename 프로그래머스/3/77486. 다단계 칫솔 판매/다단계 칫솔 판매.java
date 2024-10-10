import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = {};
        
        int[] total = new int[enroll.length];
        int N = enroll.length + 1;
        HashMap<String, Integer> idxs = new HashMap<>();
        int[][] adjMatrix = new int[N][N];        
        
        for(int i = 0; i < N - 1; i++) {
            idxs.put(enroll[i], i + 1);
        }
        
        // 1. 유향 그래프 그리기
        for(int i = 0; i < N - 1; i++) {
            int from = i + 1;
            // 부모노드가 민호이다.
            if(referral[i].equals("-")) {
                adjMatrix[from][0] = 1;
            }
            else {
                int to = idxs.get(referral[i]);   
                adjMatrix[from][to] = 1;
            }
        }
        
        Queue<Integer> q = new ArrayDeque<>();
        // BFS
        for(int i = 0; i < seller.length; i++) {
            q.offer(idxs.get(seller[i]));
            double profit = amount[i] * 100.0;
            
            while(!q.isEmpty()) {
                int from = q.poll();
                if(from - 1 >= 0) {
                    if(profit * 0.1 < 1.0){
                        total[from - 1] += profit;
                        break;
                    }
                    total[from - 1] += (int) (profit - (int)(profit * 0.1));
                    profit = profit * 0.1;
                }
                for(int to = 0; to < N; to++) {
                    if(adjMatrix[from][to] != 1) continue;
                    q.offer(to);
                }
            }
        }
        
        return total;
    }
}