import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N;
	static int cnt;
	static int[][] map;
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
		
		dfs(0, 1, 1);
		System.out.println(cnt);
	}

	// 이동 이후의 일은 모두 떠넘긴다~
	static void dfs(int x, int y, int dir) {
		if(x < 0 || y < 0 || x >= N || y >= N) return;
		
		if(map[x][y] == 1) return;
		if(dir == 3 && (map[x-1][y] == 1 || map[x][y-1] == 1)) return;
		
		if(x == N - 1 && y == N-1) cnt++;
		
		switch (dir) {
		case 1:
			// 가로로 이동
			dfs(x, y + 1, 1);
			// 대각선으로 이동
			dfs(x + 1, y + 1, 3);
			break;
		case 2:
			// 세로로 이동
			dfs(x + 1, y, 2);
			// 대각선으로 이동
			dfs(x + 1, y + 1, 3);
			break;
		case 3:
			// 가로로 이동
			dfs(x, y + 1, 1);
			// 세로로 이동
			dfs(x + 1, y, 2);
			// 대각선으로 이동
			dfs(x + 1, y + 1, 3);
			break;
		}
	}
	
}