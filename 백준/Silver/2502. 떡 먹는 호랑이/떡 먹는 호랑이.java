import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int D = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dp = new int[D+1];
		
		for(int i = 1; i <= K/2; i++) {
			A: for(int j = i; j <= K; j++) {
				
				dp[1] = i;
				dp[2] = j;
				
				for(int d = 3; d <= D; d++) {
					dp[d] = dp[d-1] + dp[d-2];
					
					if(dp[d] > K) break A;
				}
				
				if(dp[D] == K) {
					System.out.println(dp[1]);
					System.out.println(dp[2]);
					return;
				}
			}
		}
		
	}
}