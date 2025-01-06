class Solution {
    public int solution(int[][] triangle) {
        int answer = Integer.MIN_VALUE;
        
        int lv = triangle.length;
        
        for(int row = 1; row < lv; row++) {            
            triangle[row][0] += triangle[row - 1][0];
            triangle[row][row] += triangle[row - 1][row - 1];
            
            for(int col = 1; col < row; col++) {
                triangle[row][col] += Math.max(triangle[row - 1][col], triangle[row - 1][col - 1]);
                
                // 최대 누적값 구해야 함
                if(row == lv - 1) {
                    answer = Math.max(triangle[row][col], answer);
                }
                
            }
            
        }
        
        
        
        return answer;
    }
}