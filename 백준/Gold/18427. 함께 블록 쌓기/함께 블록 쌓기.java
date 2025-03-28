
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][H + 1];
		int[][] nums = new int[N + 1][M];
			
		for(int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = 0;
            while (st.hasMoreTokens()) {
                nums[i][idx++] = Integer.parseInt(st.nextToken());
            }
		}
		
        dp[0][0] = 1;
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j <= H; j++) {
				dp[i][j] += dp[i-1][j];
				dp[i][j] %= 10_007;
				
				for(int num : nums[i]) {
					if(num == 0) break;
					if(j >= num) {
						dp[i][j] += dp[i - 1][j - num];
						dp[i][j] %= 10_007;
					}
				}
			}
//			System.out.println(Arrays.toString(dp[i]));
		}
		
		System.out.println(dp[N][H]);
	}
	
}
