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
				dp[i][j] = (dp[i-1][j] + dp[i][j]) % MOD;
				for(int num : nums[i]) {
					if(num == 0) break;
					if(j >= num) {
						dp[i][j] = (dp[i][j] + dp[i - 1][j - num]) % MOD;
					}
				}
			}
//			System.out.println(Arrays.toString(dp[i]));
		}
		
		System.out.println(dp[N][H]);
	}
	
}
