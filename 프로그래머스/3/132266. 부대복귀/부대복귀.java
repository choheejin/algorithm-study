import java.util.*;

class Solution {
    static List<Integer>[] list;
    static int[] memo;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer> answer = new ArrayList<>();
        
        initList(n, roads);
        
        bfs(n, destination);
        
        for(int i = 0; i < sources.length; i++) {
            int source = sources[i];
            answer.add(memo[source]);
        }
        
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public void initList (int n, int[][] roads) {
        list = new ArrayList[n + 1];
        
        for(int i = 1; i <= n; i++) {
            list[i] = new ArrayList();
        }
        
        for(int i = 0; i < roads.length ; i++) {
            int from = roads[i][0];
            int to = roads[i][1];
            
            list[from].add(to);
            list[to].add(from);
        }        
    }
    
    public static void bfs (int n, int dest) {
        ArrayDeque<Integer> q = new ArrayDeque<>();
        
        memo = new int[n+1];
        Arrays.fill(memo, -1);
        
        memo[dest] = 0;
        q.offer(dest);
        
        while(!q.isEmpty()) {
            int from = q.poll();
            
            for(int to : list[from]) {
                if(memo[to] != -1) continue;
                memo[to] = memo[from] + 1;
                q.offer(to);
            }
        }
    }
}