
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] dp = new int[N + 1][K + 1];

		int[][] products = new int[N + 1][2];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			products[i][0] = Integer.parseInt(st.nextToken());
			products[i][1] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 1; i <= K; i++) {
			for(int n = 1; n <= N; n++) {
				if(products[n][0] <= i) {
					dp[n][i] = Math.max(dp[n-1][i],dp[n-1][i - products[n][0]] + products[n][1]);
				} else {
					dp[n][i] = dp[n- 1][i];
				}
			}
		}
		
		System.out.println(dp[N][K]);
	}
}
