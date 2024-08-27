import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
	static int[][] adjMatrix;
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			adjMatrix = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int maxValue = 0;
			for(int t = 0; t <= 100; t++) {
				int cnt = 0;
				visited = new boolean[N][N];
				if(t != 0) {
					// 갉아먹기
					for(int i = 0; i < N; i++) {
						for(int j = 0; j < N; j++) {
							adjMatrix[i][j] -= 1;
						}
					}
				}
				
				// 덩어리 찾기
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						if(dfs(i, j)) cnt++;
					}
				}
				
				maxValue = Math.max(maxValue, cnt);
			}
			sb.append("#").append(tc).append(" ").append(maxValue).append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean dfs(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) return false;		
		
		if(!visited[x][y] && adjMatrix[x][y] > 0) {
			visited[x][y] = true;
			dfs(x + 1, y);
			dfs(x - 1, y);
			dfs(x, y + 1);
			dfs(x, y - 1);
			return true;
		}
		return false;
	}
}