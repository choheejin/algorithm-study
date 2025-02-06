import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(bf.readLine());
		int[][] arr = new int[N][2];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			arr[i][0] = Integer.parseInt(st.nextToken());
			arr[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr, Comparator.comparingInt((a) -> a[0]));
		
		int[] dp = new int[N];
		int max = 0;
		for(int i = 0; i < N; i++) {
			dp[i] = 1;
			for(int j = 0; j < i; j++) {
				if(arr[i][1] > arr[j][1]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
			max = Math.max(dp[i], max);
		}
		
		System.out.println(N - max);
	}
}