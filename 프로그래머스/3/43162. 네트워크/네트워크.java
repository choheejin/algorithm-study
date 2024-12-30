import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] visited = new boolean[n];
        
        for(int i = 0; i < n; i++) {
            if(bfs(n, computers, visited, i)) {
                answer++;
            }
        }
        
        for(int i = 0; i< n; i++) {
            if(!visited[i]) {
                answer++;
            }
        }
        
        return answer;
    }
    
    public static boolean bfs(int n, int[][] computers, boolean[] visited, int start) {
        Queue<Integer> q = new ArrayDeque();
        
        q.offer(start);
        
        int cnt = 0;
        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                int node = q.poll();
                
                for(int i = 0; i < n; i++) {
                    if(node == i) continue;
                    if(computers[node][i] == 0) continue; 
                    if(visited[i]) continue;
                    
                    cnt++;
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
        
        return cnt > 0;
    }
}