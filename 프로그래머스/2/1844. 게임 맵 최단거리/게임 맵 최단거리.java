import java.util.*;

class Solution {
    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    
    public int solution(int[][] maps) {
        int answer = bfs(maps);
             
        return answer;
    }
    
    public static int bfs(int[][] maps) {
        int n = maps.length;
        int m = maps[0].length;
        
        Queue<int[]> q = new ArrayDeque();
        
        q.offer(new int[] {0, 0});
        maps[0][0] = 0;
        
        int cnt = 1;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                int[] curr = q.poll();

                if(curr[0] == n - 1 && curr[1] == m - 1) {
                    return cnt;
                }
                
                for(int i = 0; i < 4; i++) {
                    int nx = curr[0] + dx[i];
                    int ny = curr[1] + dy[i];
                    
                    if(0 > nx || 0 > ny || n <= nx || m <= ny ) continue;
                    if(maps[nx][ny] != 1) continue;
                    
                    q.offer(new int[] {nx, ny});
                    maps[nx][ny] = 0;                    
                }
            }
            
            cnt++;
        }
        
        return -1;
    }
    
}