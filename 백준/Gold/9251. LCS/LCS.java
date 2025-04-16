import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		String cmd1 = bf.readLine();
		String cmd2 = bf.readLine();
	
		int len1 = cmd1.length();
		int len2 = cmd2.length();
				
		int[][] dp = new int[len1 + 1][len2 + 1];
		
		for(int i = 1; i <= len1; i++) {
			for(int j = 1; j <= len2; j++) {
				if(cmd1.charAt(i - 1) == cmd2.charAt(j - 1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
				}
			}
		}
		System.out.println(dp[len1][len2]);
	}
	
}
