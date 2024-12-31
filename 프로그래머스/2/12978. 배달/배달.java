import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = dijstra(N, road, K);
        
        return answer;
    }
    
    public static int dijstra(int n, int[][] road, int k) {
        PriorityQueue<long[]> pq = new PriorityQueue<>((a,b) -> Long.compare(a[1], b[1]));
        boolean[] visited = new boolean[n+1];
        long[] costs = new long[n+1];
        
        Arrays.fill(costs, Long.MAX_VALUE);
        
        costs[1] = 0;
        pq.offer(new long[] {1, 0});
        
        while(!pq.isEmpty()) {
            long[] curr = pq.poll();
            
            int from = Long.valueOf(curr[0]).intValue();
            long currCost = curr[1];
            
            if(visited[from]) continue;
            visited[from] = true;
            
            // 방문할 노드
            for(int i = 0; i < road.length; i++) {
                int to = 0;
                
                if(road[i][0] == from || road[i][1] == from) {
                    if(road[i][0] == from) {
                        to = road[i][1];
                    }
                    if(road[i][1] == from) {
                        to = road[i][0];
                    }
                    
                    if(visited[to]) continue;
                
                    // 경유해서 가는게 훨씬 이득인 경우!
                    if(costs[to] > currCost + road[i][2]) {
                        costs[to] = currCost + road[i][2];
                        pq.offer(new long[] {to, costs[to]});
                    }

                }
                
            }
            
        }
        
        int cnt = 0;
        for(int i = 1; i <= n; i++) {
            if(costs[i] <= k) {
                cnt++;
            }
        }
        
        return cnt;
    }
}