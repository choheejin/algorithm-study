import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, L, R;
	static int[][] map, newMap;
	static int[] dx = new int[] {-1, 1, 0, 0}, dy = new int[] {0, 0, -1, 1};	
	static List<Point> points;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int result = 0;
		while(true) {
			visited = new boolean[N][N];
			newMap = new int[N][N]; 
			int maxC = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {					
					if(visited[i][j]) continue;
					points = new ArrayList<>();
					
					int[] tmp = bfs(i, j);
					
					rewrite((int) (tmp[1] / tmp[0]));
					maxC = Math.max(maxC, tmp[0]);
				}
			}
			
			if(maxC <= 1) {
				break;
			}
			
			rewrite();
			result++;
		}
		
		System.out.println(result);
	}
	
	// 덮어쓰기
	public static void rewrite(int num) {
		for(Point p : points) {
			newMap[p.x][p.y] = num;
		}
	}
	
	public static void rewrite() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(newMap[i][j] != 0) {
					map[i][j] = newMap[i][j];
				}
			}
		}
	}
	
	// 칸, 인구 수 
	public static int[] bfs (int sx, int sy) {
		Queue<int[]> q = new ArrayDeque<>();
		
		int c = 0;
		int p = 0;
		
		q.offer(new int[] {sx, sy});
		visited[sx][sy] = true;
		points.add(new Point(sx, sy));
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();

			int x = curr[0];
			int y = curr[1];
			
			c++;
			p += map[x][y];
			
			for(int i = 0; i < 4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				
				if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				if(visited[nx][ny]) continue;
				
				// 인구 이동 불가 
				int gap = Math.abs(map[x][y] - map[nx][ny]);
				if(gap > R || gap < L) continue;
				
				q.offer(new int[] {nx, ny});
				visited[nx][ny] = true;
				points.add(new Point(nx, ny));
			}
		}
		
		return new int[] {c, p};
 	}
	
}
