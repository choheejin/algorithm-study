class Solution {
    public int solution(int[][] info, int n, int m) {
        int answer = 0;
        int len = info.length;
        int maxA = 0;
        
        for(int i = 0; i < len; i++) {
            maxA += info[i][0];
        }
        
        int[] dp = new int[m];
        
        for(int i = 0; i < len; i++) { // 배낭의 개수 만큼
            for(int j = m-1; j >= info[i][1]; j--) {
                dp[j] = Math.max(dp[j], dp[j - info[i][1]] + info[i][0]);
            }
        }
                
        return maxA - dp[m-1] >= n ? -1 : maxA - dp[m-1];
    }
}