import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		if(N == 1) {
			System.out.print(0);
			return;
		}
		
		if(N <= 3) {
			System.out.print(1);
			return;
		}
		
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		
		dp[1] = 1;
		dp[2] = 1;
		dp[3] = 1;

		
		for(int i = 4; i <= N; i++) {
			if(i % 3 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 3] + 1);
			}
			if (i % 2 == 0) {
				dp[i] = Math.min(dp[i], dp[i / 2] + 1);
			}
			dp[i] = Math.min(dp[i], dp[i - 1] + 1);
		}
		
//		System.out.println(Arrays.toString(dp));

		System.out.print(dp[N]);
	}
}
