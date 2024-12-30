import java.util.*;

class Solution {
    static int startX, startY;
    static int targetX, targetY;
    static int leverX, leverY;
    static int n, m;
    static int[] dx = {0, 0, -1, 1}, dy = {-1, 1, 0, 0};
    
    public int solution(String[] maps) {
        int answer = 0;
        
        n = maps.length;
        m = maps[0].length();
        
        initTargetPoints(maps);
        
        answer = shortestPathLen(startX, startY, leverX, leverY, maps);
        
        if(answer == -1) {
            return -1;
        }
        
        int tmp = shortestPathLen(leverX, leverY, targetX, targetY, maps);
        if(tmp == -1) {
            return -1;
        }
        
        answer += shortestPathLen(leverX, leverY, targetX, targetY, maps);
        
        return answer;
    }
    
    public static void initTargetPoints(String[] maps) {
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < m; y++) {
                if(maps[x].charAt(y) == 'E') {
                    targetX = x;
                    targetY = y;
                }
                else if(maps[x].charAt(y) == 'L') {
                    leverX = x;
                    leverY = y;
                }
                else if(maps[x].charAt(y) == 'S') {
                    startX = x;
                    startY = y;
                }
            }
        }
    }
    
    public static int shortestPathLen(int startX, int startY, int endX, int endY, String[] maps) {
        Queue<int[]> q = new ArrayDeque();
        boolean[][] visited = new boolean[n][m];
        
        q.offer(new int[] {startX, startY});
        visited[startX][startY] = true;
        
        int cnt = 0;
        
        while(!q.isEmpty()) {
            int size = q.size();
            
            while(size-- > 0) {
                int[] curr = q.poll();
                
                if(curr[0] == endX && curr[1] == endY) {
                    return cnt;
                }
                
                for(int i = 0; i < 4; i++) {
                    int nx = dx[i] + curr[0];
                    int ny = dy[i] + curr[1];
                
                    if(0 > nx || 0 > ny || nx >= n || ny >= m) continue;
                    if(visited[nx][ny]) continue;
                    if(maps[nx].charAt(ny) == 'X') continue;
                    
                    q.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;
                }
                
            }
            
            cnt++;
        }
        
        return -1;
    }
}