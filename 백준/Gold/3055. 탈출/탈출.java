import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int startX, startY;
	static char[][] map;
	static Queue<int[]> water;
	static Queue<int[]> move;
	static boolean[][] visited;
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		water = new ArrayDeque<>();
		map = new char[R][];
		
		for(int r = 0; r < R; r++) {
			map[r] = bf.readLine().toCharArray();
			for(int c = 0; c < C; c++) {
				if(map[r][c] == '*') water.offer(new int[] {r, c}); 
				if(map[r][c] == 'S') {
					startX = r;
					startY = c;
				}
			}
		}
		
		visited = new boolean[R][C];
		move = new ArrayDeque<>();
		
		move.offer(new int[] {startX, startY});
		visited[startX][startY] = true;
		
		int cnt = 0;
		boolean flag = false;
		
		A : while(!move.isEmpty()) {
			cnt++;
			int size = move.size();
			
			// 물 확장
			int w_size = water.size();
			
			while(w_size-- > 0) {
				int[] curr = water.poll();
				
				for(int i = 0; i < 4; i++) {
					int nx = curr[0] + dx[i];
					int ny = curr[1] + dy[i];
					if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
					if(map[nx][ny] == '.') {
						map[nx][ny] = '*';
						water.offer(new int[] {nx, ny});
					}
				}
			}
			
			
			while(size-- > 0) {
				int[] curr = move.poll();
				
				for(int i = 0; i < 4; i++) {
					int nx = curr[0] + dx[i], ny = curr[1] + dy[i];
					if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
					if(visited[nx][ny]) continue;
					if(map[nx][ny] == 'D') {
						flag = true;
						break A;
					}
					if(map[nx][ny] == '.') {
						move.offer(new int[] {nx, ny});
						visited[nx][ny] = true;
					}
				}
			}
		}
		System.out.println(!flag ? "KAKTUS" : cnt);
	}
	
}