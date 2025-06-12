import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int DIVISION = 1_000_000_000;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		int[][] dp = new int[101][10];
		
		Arrays.fill(dp[1], 1);
		dp[1][0] = 0;
		
		for(int i = 2; i <= N; i++) {
			for(int j = 0; j < 10; j++) {
				if(j > 0) {
					dp[i][j] += dp[i-1][j-1];
				}
				if(j < 9) {
					dp[i][j] += dp[i-1][j+1];
				}
				dp[i][j] %= DIVISION;
			}
		}
		int answer = 0;
		for(int i = 0; i < 10; i++) {
			answer = (answer + dp[N][i]) % DIVISION;
		}
		
		System.out.println(answer);
	}
}
