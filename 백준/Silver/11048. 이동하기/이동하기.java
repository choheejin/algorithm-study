import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws Exception {
		int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
		int N, M;
		int[][] map;
		
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N+1][M+1];
		
		int[][] dp = new int[N+1][M+1];
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 1; j <= M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= M; j++) {
				int candy = Math.max(Math.max(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
				dp[i][j] = candy + map[i][j];
			}
		}
		
		System.out.println(dp[N][M]);
	}
}