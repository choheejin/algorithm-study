import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, startP, maxValue, graph[][];
	static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};
	static boolean visited[][];
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			graph = new int[N][N];
			maxValue = Integer.MIN_VALUE;
			startP = Integer.MAX_VALUE;
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					visited = new boolean[N][N];
					dfs(i, j, 1, graph[i][j]);
				}
			}
			sb.append("#").append(tc).append(" ").append(startP).append(" ").append(maxValue).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void dfs(int x, int y, int total, int startPoint) {	
		visited[x][y] = true;
		for(int i = 0; i < 4; i++) {
			int nx = dx[i] + x, ny = dy[i] + y;
//			System.out.printf("%d, %d\n", x, y);
//			System.out.printf("%d, %d\n", nx, ny);
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) {
//				System.out.println();
				continue;
			}
			if(!visited[nx][ny] && graph[nx][ny] - graph[x][y] == 1) {
//				System.out.printf("%d %d \n", graph[x][y], graph[nx][ny]);
//				System.out.println(startPoint);
				visited[nx][ny] = true;
				dfs(nx, ny, total+1, startPoint);
			}
		}
		
		if(maxValue > total) return;
		
		if(maxValue == total) {
			startP = Math.min(startP, startPoint);
		} else {
			maxValue = Math.max(maxValue, total);	
			startP = startPoint;
		}
	}
}
