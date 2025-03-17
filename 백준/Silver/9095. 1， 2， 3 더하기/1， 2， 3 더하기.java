import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(bf.readLine());
			
			int[] dp = new int[12];
			
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for(int i = 4; i <= N; i++) {
				dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
			}
			
			sb.append(dp[N]).append("\n");
		}
		System.out.println(sb);
	}
}