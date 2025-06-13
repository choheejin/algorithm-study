import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(bf.readLine());
			
			int[][] nums = new int[2][N+1];
			long[][] dp = new long[2][N+1];
			
			StringTokenizer st1 = new StringTokenizer(bf.readLine());
			StringTokenizer st2 = new StringTokenizer(bf.readLine());
			
			for(int j = 1; j <= N; j++) {
				nums[0][j] = Integer.parseInt(st1.nextToken());
				nums[1][j] = Integer.parseInt(st2.nextToken());
			}

			dp[0][1] = nums[0][1];
			dp[1][1] = nums[1][1];
			
			for(int i = 2; i <= N; i++) {
				long preMax = Math.max(dp[0][i-2], dp[1][i-2]);
				
				dp[0][i] = Math.max(dp[1][i-1], preMax) + nums[0][i];
				dp[1][i] = Math.max(dp[0][i-1], preMax) + nums[1][i];
 			}
			
			
			long maxValue = Math.max(dp[0][N], dp[1][N]);
			sb.append(maxValue).append("\n");
		}
		System.out.println(sb);
	}
}
