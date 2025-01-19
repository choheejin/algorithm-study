import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
/**
 * 문제 파악하기
 * - dfs를 이용한 사이클 탐색
 * 
 *  dfs 를 이용하여, 같은 색의 점들만 방문하게 하는 dfs 코드 작성 필요 
 * 
 */
	
	static char[][] map;
	static boolean isFind;
	static int n, m;
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		map = new char[n][];
		
		for(int i = 0; i < n; i++) {
			map[i] = bf.readLine().toCharArray();
		}
		
		
		String result = "No";
		A : for(int i = 0; i < n; i++) {
			for(int j = 0; j < m; j++) {
				isFind = false;
				dfs(i, j, map[i][j], new boolean[n][m], i, j, 0);
				if(isFind) {
					result = "Yes";
					break A;
				}
			}
		}
		System.out.println(result);
	}
	
	public static void dfs(int x, int y, char color, boolean[][] visited, int targetX, int targetY, int cnt) {		
		
		if(cnt >= 4 && targetX == x && targetY == y) {
			isFind = true;
			return;
		}
		
		if(0 > x || x >= n || 0 > y || y >= m) return;
		
		if(map[x][y] == color && !visited[x][y]) {
			visited[x][y] = true;
			
			dfs(x - 1, y, color, visited, targetX, targetY, cnt+1);
			dfs(x, y + 1, color, visited, targetX, targetY, cnt+1);
			dfs(x + 1, y, color, visited, targetX, targetY, cnt+1);
			dfs(x, y - 1, color, visited, targetX, targetY, cnt+1);
					
			visited[x][y] = false;
		}
		
	}

}