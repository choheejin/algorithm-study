import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int t = 0; t < N; t++) {
			int num = Integer.parseInt(bf.readLine());
			
			int[] dp = new int[12];
			
			dp[1] = 1;
			dp[2] = 2;
			dp[3] = 4;
			
			for(int i = 4; i <= num; i++) {
				dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
			}
			
			sb.append(dp[num]).append("\n");
		}
		System.out.print(sb);
	}
}
