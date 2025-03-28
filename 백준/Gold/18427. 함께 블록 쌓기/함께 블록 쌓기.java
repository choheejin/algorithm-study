import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, H;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int MOD = 10_007;
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[H+1];
				
		dp[0] = 1;
		
		for(int i = 1; i <= N; i++) {
			int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
			int[] next = new int[H+1];
			System.arraycopy(dp, 0, next, 0, H + 1);

			for(int num : nums) {
				for(int j = 0; j <= H - num; j++) {
					next[j + num] = (next[j + num] + dp[j]) % MOD;
				}
			}
			
			dp = next;
		}
		
		System.out.println(dp[H]);
	}
	
}
