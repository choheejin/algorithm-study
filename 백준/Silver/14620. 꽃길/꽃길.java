import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[][] map;
	static int[] dx = {0, 1, -1, 0, 0}, dy = {0, 0, 0, -1, 1};
	static int minValue = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(bf.readLine());
		map = new int[N][N];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		List<Point>[] selected = new ArrayList[N];
		
		for(int i = 0; i < N; i++) {
			selected[i] = new ArrayList<>();
		}
		
		pick(0, 0, selected, 0);
	
		System.out.println(minValue);
	}
	
	public static void pick(int n, int col, List<Point>[] selected, int cost) {
		if(n == 3) {
			minValue = Math.min(minValue, cost);
			return;
		}
		
		for(int i = col; i < N * N; i++) {
			int c = i % N;
			int r = i / N;
			
			int tmp = isValid(n, c, r, selected);
			if(tmp == -1) {
				continue;
			}			
			
			for(int j = 0; j < 5; j++) {
				selected[n].add(new Point(r + dx[j], c + dy[j]));
			}
			
			pick(n + 1, i + 1, selected, cost + tmp);
			
			selected[n] = new ArrayList<>();
			
//			for (int j = 4; j >= 0; j--) { 
//	            selected[n].remove(selected[n].size() - 1);
//	        }
}
	}
	
	public static int isValid(int n, int col, int row, List<Point>[] selected) {
		int total = 0;
		
		for(int i = 0; i < 5; i++) {
			int r = row + dx[i];
			int c = col + dy[i];
			
			if(r < 0 || r >= N || c < 0 || c >= N) {
				return -1;
			}

			total += map[r][c];
			
			for(int j = 0; j < n; j++) { 
				for(Point point: selected[j]) {
					if(point.getX() == r && point.getY() == c) {
						return -1;
					}
				}
			}
		}
		
		return total;
	}
}