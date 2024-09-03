import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
	static boolean[][][] visited;
	static int[][] map, cnt;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
	
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
	
		map = new int[N][M];
		cnt = new int[N][M];
		visited = new boolean[N][M][2];
		
		for(int i = 0; i < N; i++) {
			String inputs = bf.readLine();
			
			for(int j = 0; j < M; j++) {
				map[i][j] = inputs.charAt(j) - '0';
			}
		}
		
		bfs();
		
		System.out.println(cnt[N-1][M-1] == 0 ? -1 : cnt[N-1][M-1]);
	}
	
	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[0][0][0] = true;
		q.offer(new int[] {0, 0, 0});
		cnt[0][0] = 1;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int x = curr[0];
			int y = curr[1];
			int chance = curr[2];
			
			if(x == N-1 && y == M-1) {
				return;
			}
			
			for(int i = 0; i < 4; i++) {
				int nx = dx[i] + x, ny = dy[i] + y;
			
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				
				
				// 벽이라면
				if(map[nx][ny] == 1) {
					
					if(chance == 1) continue;
					if(visited[nx][ny][chance + 1]) continue;
					
					visited[nx][ny][chance + 1] = true;
					cnt[nx][ny] = cnt[x][y] + 1;
					
					q.offer(new int[] {nx, ny, chance + 1});
				}	
				
				// 벽이 아니라면
				else {
					if(visited[nx][ny][chance]) continue;
					
					visited[nx][ny][chance] = true;
					cnt[nx][ny] = cnt[x][y] + 1;
					q.offer(new int[] {nx, ny, chance});
				}
//				if(nx == N-1 && ny == M-1) {
//					return;
//				}
			}			
		}
		
	}

}