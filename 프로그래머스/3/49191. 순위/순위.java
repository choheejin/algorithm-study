import java.util.*;

class Solution {
    static int[][] dists;
    
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        dists = new int[n+1][n+1];
        for(int i = 1; i<= n; i++) Arrays.fill(dists[i], Integer.MAX_VALUE);
        
        init(results);
        floyd(n);
        
        for(int i = 1; i <= n; i++ ) {
            int cnt = 0;
            for(int j = 1; j <= n; j++) {
                if (i == j) continue;
                if(dists[i][j] != Integer.MAX_VALUE || dists[j][i] != Integer.MAX_VALUE) cnt++;
            }
            if(cnt == n-1) answer++;
        }
        
        return answer;
    }
    
    public static void init (int[][] results) {
        for(int i = 0; i < results.length; i++) {
            int win = results[i][0];
            int lose = results[i][1];
            
            dists[win][lose] = 1;
        }
    }
    
    public static void floyd (int n) {
        for(int k = 1; k <= n; k++) {
            for(int i = 1; i <= n; i++) {
                if(dists[i][k] == Integer.MAX_VALUE) continue;
                for(int j = 1; j <= n; j++) {
                    if (i == j) continue;
                    if(dists[k][j] == Integer.MAX_VALUE) continue;
                    
                    dists[i][j] = Math.min(dists[i][k] + dists[k][j], dists[i][j]);
                }
            }
        }
    }
}