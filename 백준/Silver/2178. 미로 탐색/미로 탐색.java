import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, cnt;
	static int[][] adjMatrix;
	static boolean[][] visited;
	static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N][M];
		visited = new boolean[N][M];
		
		for(int i = 0; i< N; i++) {
			String[] inputs = bf.readLine().split("");
			for(int j = 0; j < M; j++) {
				adjMatrix[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		
		bfs(0,0);
		System.out.println(adjMatrix[N-1][M-1]);
	}
	
	static void bfs(int x, int y) {
		Queue<int[]> q = new ArrayDeque<>();
		
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		cnt = 1;
		
		while(!q.isEmpty()){
			int size = q.size();
			while(size-- > 0) {
				int[] curr = q.poll();
				adjMatrix[curr[0]][curr[1]] = cnt;

				for(int i = 0; i < 4; i++) {
					
					int nx = dx[i] + curr[0], ny = dy[i] + curr[1];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
					if(visited[nx][ny]) continue;
					if(adjMatrix[nx][ny] == 0) continue;
					
					visited[nx][ny] = true;
					q.offer(new int[] {nx, ny});
				}
			}
			cnt++;
		}
	}
}