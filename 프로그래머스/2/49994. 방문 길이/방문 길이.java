import java.util.*;

class Solution {
    static int N = 5;
    
    public static int makePositive(int a) {
        if(a == 0) return 0;
        return a < 0 ? Math.abs(a) : a + N;
    }
    
    public static boolean check(int x, int y) {
        return x <= 5 && x >= -5 && y <= 5 && y >= -5;
    }
    
    public int solution(String dirs) {
        int nx = 0, ny = 0;
        int answer = 0;
        
        HashMap<String, Boolean> visited = new HashMap<>();
        
        int tmpX = 0;
        int tmpY = 0;
        
        for(int i = 0; i < dirs.length(); i++) {
            char cmd = dirs.charAt(i);
            
            int x = tmpX, y = tmpY;            
            
            switch(cmd) {
                case 'U':
                    if(!Solution.check(nx - 1, ny)) continue;
                    nx -= 1;
                    tmpX = Solution.makePositive(nx);
                    break;
                case 'D':
                    if(!Solution.check(nx + 1, ny)) continue;
                    nx += 1;
                    tmpX = Solution.makePositive(nx);
                    break;
                case 'L':
                    if(!Solution.check(nx, ny - 1)) continue;
                    ny -= 1;
                    tmpY = Solution.makePositive(ny);
                    break;
                case 'R':
                    if(!Solution.check(nx, ny + 1)) continue;
                    ny += 1;
                    tmpY = Solution.makePositive(ny);
                    break;
            }
            
            visited.put(x + "," + y + "|" + tmpX + "," + tmpY, true);
            visited.put(tmpX + "," + tmpY + "|" + x + "," + y, true);
        }
        return visited.size() / 2;
    }
}