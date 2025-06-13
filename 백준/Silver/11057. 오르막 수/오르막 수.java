import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int MOD = 10_007;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		int[][] dp = new int[N+1][10];
		
		Arrays.fill(dp[1], 1);
		
		for(int i = 2; i <= N; i++) {
			dp[i][0] = 1;
			for(int j = 1; j < 10; j++) {
				dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % MOD;
			}
		}
		
		int answer = 0;
		for(int i = 0; i < 10; i++) {
			answer = (answer + dp[N][i]) % MOD;
		}
		
		System.out.println(answer);
	}
}
