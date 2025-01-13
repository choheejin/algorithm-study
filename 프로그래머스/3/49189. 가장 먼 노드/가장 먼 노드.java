import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = dijstra(n, edge);
        return answer;
    }
    
    public int dijstra(int n, int[][] edge) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> Integer.compare(a[1], b[1]));
        boolean[] visited = new boolean[n+1];
        int[] dist = new int[n+1];
        
        Arrays.fill(dist, Integer.MAX_VALUE);
        
        dist[1] = 0;
        pq.offer(new int[] {1, 0});
        
        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            
            int from = curr[0];
            
            if(visited[from]) continue;
            visited[from] = true;
            
            for(int i = 0; i < edge.length; i++) {
                // 연결되어 있지 않음
                if(edge[i][0] != from && edge[i][1] != from) continue;
                int to = edge[i][0] == from ? edge[i][1] : edge[i][0];
                
                if(visited[to]) continue;
                
                if(dist[to] > 1 + curr[1]) {
                    dist[to] = 1 + curr[1];
                    pq.offer(new int[] {to, dist[to]});
                }
            }
            
        }
        
        // System.out.println(Arrays.toString(dist));
        
        int maxV = 0;
        for(int i = 2; i <= n; i++) {
            maxV = Math.max(maxV, dist[i]);
        }
        
        // System.out.println(maxV);
        
        int result = 0;
        for(int i = 2; i<= n; i++) {
            if(maxV == dist[i]) result++;
        }
        
        return result;
    }
}