import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int maxCost = 0;
		int[] memories = new int[N];
		int[] costs = new int[N];
		
		StringTokenizer st1 = new StringTokenizer(bf.readLine());
		StringTokenizer st2 = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < N; i++) {
			memories[i] = Integer.parseInt(st1.nextToken());
			costs[i] = Integer.parseInt(st2.nextToken());
			maxCost += costs[i];
		}
		
		int[] dp = new int[maxCost + 1];
		
		for(int i = 0; i < N; i++) {
			for(int j = maxCost; j >= costs[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - costs[i]] + memories[i]);
			}
		}
		
		for(int i = 0; i <= maxCost; i++) {
			if(dp[i] >= M) {
				System.out.println(i);
				return;
			}
		}
	}
}
