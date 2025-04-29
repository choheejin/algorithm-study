import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[] costs = new int[N];
		int[] people = new int[N];
				
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			costs[i] = Integer.parseInt(st.nextToken());
			people[i] = Integer.parseInt(st.nextToken());
		}
		
		int MAX = 100_000;
		
		int[] dp = new int[MAX];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = people[i]; j < MAX; j++) {
				if(dp[j - people[i]] != Integer.MAX_VALUE) {
					dp[j] = Math.min(dp[j], dp[j - people[i]] + costs[i]);
				}
			}
		}
		
		int answer = Integer.MAX_VALUE;
		for(int i = C; i < MAX; i++) {
			answer = Math.min(answer, dp[i]);
		}
		
		System.out.println(answer);
	}
}
