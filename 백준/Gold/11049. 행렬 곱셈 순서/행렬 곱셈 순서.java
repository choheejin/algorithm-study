import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int N = Integer.parseInt(bf.readLine());
		
		int[][] arr = new int[N+1][];
		
		int[][] dp = new int[N+1][N+1];
		
		
		for(int i = 1; i<= N; i++) {
			st = new StringTokenizer(bf.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			arr[i] = new int[] {x, y};
		}
		
		for(int len = 2; len <= N; len++) {
			for(int i = 1; i <= N - len + 1; i++) { // i -> 연속 시작 포인트 
				int end = i + len - 1;
                dp[i][end] = Integer.MAX_VALUE;

				for(int k = i; k < end; k++) { // k -> i ~ end 사이에서 자르는 지점
					dp[i][end] = Math.min(dp[i][k] + dp[k+1][end] + (arr[i][0] * arr[end][1] * arr[k][1]), dp[i][end]);
				}
			}
		}
		
		System.out.println(dp[1][N]);
	}
}
