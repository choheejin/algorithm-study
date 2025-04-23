import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int D = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[D + 1];
		
		for(int i = 0; i < P; i++) {
			st = new StringTokenizer(bf.readLine());
			int l = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			dp[0] = Integer.MAX_VALUE;
			for(int distance = D; distance >= l; distance--) {
				dp[distance] = Math.max(dp[distance], Math.min(dp[distance - l], c));
			}
		
		}
		
		System.out.println(dp[D]);
	}
}
