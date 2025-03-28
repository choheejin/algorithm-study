import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int MOD = 10_007;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][H + 1];
		
		dp[0][0] = 1;
		
		for(int i = 1; i <= N; i++) {
			int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			
			System.arraycopy(dp[i-1], 0, dp[i], 0, H + 1);

			for(int num : nums) {
				for(int j = 0; j <= H - num; j++) {
					if(dp[i - 1][j] >= 1) {
						dp[i][j + num] = (dp[i][j + num] + dp[i - 1][j]) % MOD;
					}
				}
			}
			
//			System.out.println(Arrays.toString(dp[i]));
		}
		
		System.out.println(dp[N][H]);
	}
	
}
