import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static long[] dp = new long[1023];
	static int cnt = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());
		
		if(N > 1023) {
			System.out.println(-1);
			return;
		}
		
		for(int i = 0; i <=9; i++) {
			dfs(i);
		}
		
		Arrays.sort(dp, 0, cnt);
		
		System.out.println(dp[N-1]);
	}
	
	public static void dfs (long num) {
		
		dp[cnt++] = num;
		
		long tmp = num % 10;
		
		for(int n = 0; n < tmp; n++) {
			dfs(num * 10 + n);
		}
	
 	}
}
