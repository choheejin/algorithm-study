import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K = 3;
	static int total, maxValue;
	
	static int[][] map, mapCopy, wall;
	static int[] dx = {-1, 1, 0, 0}, dy = {0 , 0 , 1, -1};
	static boolean[][] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		mapCopy = new int[N][M];
		
		wall = new int[K][2];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 0) total++;
			}
		}
		
		maxValue = Integer.MIN_VALUE;
		combination(0, 0);
		
		System.out.println(maxValue);
	}
	
	static int bfs(int x, int y) {
		int value = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		visited[x][y] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			for(int i = 0 ; i < 4; i++) {
				int nx = curr[0] + dx[i], ny = curr[1] + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
				if(visited[nx][ny]) continue;
				if(mapCopy[nx][ny] == 1) continue;
				if(mapCopy[nx][ny] == 0 && mapCopy[curr[0]][curr[1]] == 0) continue;
				
				if(mapCopy[nx][ny] == 0 && mapCopy[curr[0]][curr[1]] == 2) {
					mapCopy[nx][ny] = 2;
					value++;
				}
				
				visited[nx][ny] = true;
				q.offer(new int[] {nx, ny});
			}
		}
		
		return value;
	}
	
	
	static void combination(int cnt, int start) {
		if(cnt == K) {
			int result = total - 3;
			
			arrayCopy();
			
			for(int i = 0; i < K; i++) {
				mapCopy[wall[i][0]][wall[i][1]] = 1;
			}
			
			visited = new boolean[N][M];
			
			for(int i = 0; i< N; i++) {
				for(int j = 0; j < M; j++) {
					if(visited[i][j]) continue;
					if(mapCopy[i][j] != 2) continue;
					
					result -= bfs(i, j);
					
				}
			}
			maxValue = Math.max(maxValue, result);

			return;
		}
		
		
		for(int idx = start; idx < N * M; idx++) {
			int r = idx / M;
			int c = idx % M;
			
			if(map[r][c] != 0) continue;
			
			wall[cnt][0] = r;
			wall[cnt][1] = c;
			
			combination(cnt + 1, idx + 1);
		}
	}
	
	static void arrayCopy() {
		for(int i = 0; i < N; i++) {
			System.arraycopy(map[i], 0, mapCopy[i], 0, M);
		}
	}
	
	static void print() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(mapCopy[i]));
		}
		System.out.println("======");
	}
	
}