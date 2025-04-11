import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(bf.readLine());
			
			int[] arr = new int[K+1];
			int[][] dp = new int[K+1][K+1];
			int[] sum = new int[K+1];

			st = new StringTokenizer(bf.readLine());
			for(int i = 1; i <= K; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + arr[i];
			}
			
			
			for(int len = 2; len <= K; len++) {
				for(int i = 1; i <= K - len + 1; i++) {
					int end = i + len - 1;
					dp[i][end] = Integer.MAX_VALUE;
					
					for(int j = i; j < end; j++) {
						int tmp = dp[i][j] + dp[j+1][end] + (sum[end] - sum[i-1]);
						dp[i][end] = Math.min(dp[i][end], tmp);
					}
				}
			}
			
			sb.append(dp[1][K]).append("\n");
		}
		System.out.println(sb);
		
	}
}
