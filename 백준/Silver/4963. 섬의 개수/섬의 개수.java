import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int[][] adjMatrix;
	static boolean[][] visited;
	static int[] dx = {1, -1, 0, 0, 1, 1, -1, -1}, dy = {0, 0, 1, -1, 1, -1, 1, -1};
	static int W, H;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		while(true) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			
			if(W == 0 && H == 0) break;
			
			adjMatrix = new int[H][W];
			visited = new boolean[H][W];
			
			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < W; j++) {
					adjMatrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			for(int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					if(!visited[i][j] && adjMatrix[i][j] == 1) {
						bfs(i, j);
						result++;
					}
				}
			}
			
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] node = q.poll();
			for(int i = 0; i < 8; i++) {
				int nx = dx[i] + node[0], ny = dy[i] + node[1];
				if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue;
				if(visited[nx][ny]) continue;
				if(adjMatrix[nx][ny] == 0) continue;
				
				q.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
			}
		}
	}
	
	static boolean dfs() {
		return false;
	}
}