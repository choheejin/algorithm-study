
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
	
		int[] dp = new int[N];
		int[] dp2 = new int[N];

		Arrays.fill(dp, 1);

		for(int i = 0; i<N; i ++) {
			for(int j = 0; j<i; j++) {
				if(arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
		}
//		System.out.println(Arrays.toString(dp));

		
		for(int i = N - 1; i >= 0; i--) {
			for(int j = i + 1; j < N; j++) {
				if(arr[j] < arr[i]) {
					dp2[i] = Math.max(dp2[i], dp2[j] + 1);
				}
			}
		}
		
		int maxValue = 0;
		for(int i = 0; i <N; i++) {
			maxValue = Math.max(maxValue, dp[i] + dp2[i]);
		}
		
//		System.out.println(Arrays.toString(dp2));
		System.out.println(maxValue);
	}
}
