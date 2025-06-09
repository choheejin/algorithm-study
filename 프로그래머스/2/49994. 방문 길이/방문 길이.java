class Solution {
    static int[] dx = {0, 0, -2, 2}, dy = {2, -2, 0, 0}; // u, d, l, r
    static boolean[][] map = new boolean[21][21];
    static int nx = 10, ny = 10;
    
    public int solution(String dirs) {
        int answer = 0;
        
        for(int i = 0; i < dirs.length(); i++) {
            int idx = getPosition(dirs.charAt(i));
            int x = dx[idx] + nx;
            int y = dy[idx] + ny;
            
            int pointX = (nx + x) / 2;
            int pointY = (ny + y) / 2;
            
            if(!check(x, y)) continue;
            
            if(!map[pointX][pointY]) {
                map[pointX][pointY] = true;
            }
            nx = x;
            ny = y;
        }
        

        for(int i = 0; i < 21; i++) {
            for(int j = 0; j < 21; j++) {
                if(map[i][j]) {
                    answer++;
                }
            }
        }
        
        return answer;
    }
    
    public boolean check(int x, int y) {
        if(x < 0 || y < 0 || x > 20 || y > 20) return false;
        return true;
    }
    
    public int getPosition(char op) {
        if(op == 'U') {
            return 0;
        }
        if(op == 'D') {
            return 1;
        }
        if(op == 'L') {
            return 2;
        }
        return 3;
    }
}