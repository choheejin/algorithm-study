import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		
		char[] nums = bf.readLine().trim().toCharArray();
		
//		System.out.println(Arrays.toString(nums));
		
		int N = nums.length;
		
		int[][] dp = new int[2][N];

		dp[0][0] = 1;
		
		
		for(int i = 1; i < N; i++) {
			int num = Integer.valueOf(nums[i-1] + "" + nums[i]);

			
			if(Integer.valueOf(String.valueOf(nums[i])) == 0 && (num > 26 || num <= 0)) { 
				System.out.println(0);
				return;
			}
			
			if(Integer.valueOf(String.valueOf(nums[i])) == 0) {
				dp[0][i] = 0;
				dp[1][i] = dp[0][i-1];
			}
			else if(Integer.valueOf(String.valueOf(nums[i-1])) == 0) {
				dp[0][i] = dp[1][i-1];
				dp[1][i] = 0;
			}
			else if(num > 26) {
				dp[0][i] = (dp[0][i-1] + dp[1][i-1]) % 1000000;
				dp[1][i] = 0;
			}
			else {
				dp[0][i] = (dp[0][i-1] + dp[1][i-1]) % 1000000;
				dp[1][i] = dp[0][i-1];
			}
		}
		
		long result = (dp[0][N-1] + dp[1][N-1]) % 1000000;
		if(N == 1 && Integer.valueOf(String.valueOf(nums[0])) <= 0) {
			System.out.println(0);
			return;
		}
		System.out.println(result);
//		System.out.println(Arrays.toString(dp[0]));
//		System.out.println(Arrays.toString(dp[1]));
	}
	
}
