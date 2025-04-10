import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] dp = new int[1000001];
		
		dp[2] = 1;
		dp[3] = 1;
		
		for(int i = 4; i <= N; i++) {
			if(i % 2 == 0 && i % 3 == 0) {
				dp[i] = Math.min(dp[i / 3] + 1, dp[i / 2] + 1);
				dp[i] = Math.min(dp[i], dp[i-1] + 1);
			}
			else if(i % 3 == 0) {
				dp[i] = dp[i / 3] + 1;
				dp[i] = Math.min(dp[i], dp[i-1] + 1);
			}
			else if(i % 2 == 0) {
				dp[i] = dp[i / 2] + 1;
				dp[i] = Math.min(dp[i], dp[i-1] + 1);
			}
			else {
				dp[i] = dp[i-1] + 1;
			}
		}
		
		System.out.println(dp[N]);
	}
}