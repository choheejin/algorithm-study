import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int maxCosts = 0;
		int[] memories = new int[N];
		int[] swapCosts = new int[N];
		
		StringTokenizer st1 = new StringTokenizer(bf.readLine());
		StringTokenizer st2 = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < N; i++) {
			memories[i] = Integer.parseInt(st1.nextToken());
			swapCosts[i] = Integer.parseInt(st2.nextToken());
			maxCosts += swapCosts[i];
		}
		
		int[] dp = new int[maxCosts + 1];
		
		for(int i = 0; i < N; i++) {
			for(int m = maxCosts; m >= swapCosts[i]; m-- ) {
				dp[m] = Math.max(dp[m], dp[m - swapCosts[i]] + memories[i]);
			}
		}
		
		for(int i = 0; i <= maxCosts; i++) {
			if(dp[i] >= M) {
				System.out.println(i);
				return;
			}
		}
		
	}
}
