import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		if(N == 1) {
			System.out.println("1");
			return;
		}

		int[] dp = new int[N + 1];
		
		dp[1] = 1;
		dp[2] = 2;
		
		for(int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 2] + dp[i - 1]) % 10_007;
		}
		
		System.out.print(dp[N]);
	}
}
