import java.util.*;

class Solution {
    // 완탐으로 하면 되...?려나?
    // 하나를 포함시키지 않은채로 BFS 돌리면?
    
    static int[][] adjMatrix;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        // 하나씩 빼고 연결시키기
        adjMatrix = new int[n+1][n+1];
        for(int i = 0; i < wires.length; i++) {
            adjMatrix[wires[i][0]][wires[i][1]] = 1; 
            adjMatrix[wires[i][1]][wires[i][0]] = 1; 
        }
        
        for(int i = 0; i < wires.length; i++) {
            adjMatrix[wires[i][0]][wires[i][1]] = 0; 
            adjMatrix[wires[i][1]][wires[i][0]] = 0; 
                        
            int[] cnt = new int[] {-1, -1};
            visited = new boolean[n + 1];
            
            for(int j = 1; j <= n; j++) {
                if(visited[j]) continue;
                if(cnt[0] == -1) cnt[0] = bfs(adjMatrix, n, j);
                else cnt[1] = bfs(adjMatrix, n, j);
            }
            int tmp = Math.abs(cnt[0] - cnt[1]);
            answer = Math.min(tmp, answer);
            
            adjMatrix[wires[i][0]][wires[i][1]] = 1;
            adjMatrix[wires[i][1]][wires[i][0]] = 1;
        }
        
        return answer;

    
    }
    
    public static void print(int[][] arr, int n) {
        for(int i = 1; i <= n; i++) {
            System.out.println(Arrays.toString(arr[i]));
        }
    }

    public static int bfs(int[][] adjMatrix, int n, int start) {
        int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;
        
        while(!q.isEmpty()) {
            int size = q.size();
            while(size-- > 0) {
                int from = q.poll();
                cnt++;
                for(int i = 1; i <= n; i++) {
                    if(adjMatrix[from][i] != 1) continue;
                    if(visited[i]) continue;
                    q.offer(i);
                    visited[i] = true;
                }
            }
        }
        return cnt;
    }

}