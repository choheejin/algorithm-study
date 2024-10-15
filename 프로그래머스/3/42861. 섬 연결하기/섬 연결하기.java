// 최소신장트리임
import java.util.*;

class Solution {
    static List<int[]>[] adjList;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        adjList = new ArrayList[n];
        
        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < costs.length; i++) {
            adjList[costs[i][0]].add(new int[] {costs[i][1], costs[i][2]});
            adjList[costs[i][1]].add(new int[] {costs[i][0], costs[i][2]});
        }
        
        answer = prim(costs, n);
        
        return answer;
    }
    
    public static int prim(int[][] costs, int n) {
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n];
        int cost = 0;
        
        // 1. 첫 방문 노드 선정
        q.offer(new int[] {0, 0});
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            
            if(visited[curr[0]]) continue;
            
            cost += curr[1];
            
            // 방문하지 않은 인접 노드들을 기준으로 최소비용 구한다~
            for(int i = 0; i < adjList[curr[0]].size(); i++) {
                if(visited[adjList[curr[0]].get(i)[0]]) continue;
                q.offer(adjList[curr[0]].get(i));
            }
            visited[curr[0]] = true;
        }
        return cost;

    }
    
}