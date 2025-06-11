import java.util.*;

class Solution {
    static class Node {
        int to, cost;
        
        public Node (int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
    
    static List<Node>[] adjList;
    
    public int solution(int n, int[][] costs) {
        init(n, costs);
        
        int answer = prim(n);

        return answer;
    }
    
    public int prim (int N) {
        boolean[] visited = new boolean[N];        
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        
        int minCost = 0;
        
        q.offer(new int[] {0, 0});
        
        while(!q.isEmpty()) {
            int[] currNode = q.poll();
            
            int from = currNode[0];
            int cost = currNode[1];
            
            if(visited[from]) continue;
            visited[from] = true;
            minCost += cost;
            
            for(Node n : adjList[from]) {
                if(visited[n.to]) continue;
                q.offer(new int[] {n.to, n.cost});
            }
        }
        
        return minCost;
    }
    
    public static void init (int n, int[][] costs) {
        adjList = new ArrayList[n];
        
        for(int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }
        
        for(int i = 0; i < costs.length; i++) {
            int from = costs[i][0];
            int to = costs[i][1];
            int cost = costs[i][2];
            
            adjList[from].add(new Node(to, cost));
            adjList[to].add(new Node(from, cost));
        }
    }
}