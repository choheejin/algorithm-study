import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배낭 문제
 * 중복 허용 X
 */

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		StringTokenizer st1 = new StringTokenizer(bf.readLine());
		StringTokenizer st2 = new StringTokenizer(bf.readLine());
		
		int[] lifes = new int[N];
		int[] joys = new int[N];
		
		for(int i = 0; i < N; i++) {
			lifes[i] = Integer.parseInt(st1.nextToken());
			joys[i] = Integer.parseInt(st2.nextToken());
		}
		
		int[] dp = new int[101];
		
		for(int i = 0; i < N; i++) {
			for(int j = 100; j > lifes[i]; j--) {
				dp[j] = Math.max(dp[j], dp[j - lifes[i]] + joys[i]);
			}
		}
		
		int max = 0;
		for(int i = 0; i <= 100; i++) {
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
	}
}
