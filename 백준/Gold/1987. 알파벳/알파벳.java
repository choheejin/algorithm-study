import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int R, C, maxResult;
	static boolean[] alpha;
	static boolean[][] visited;
	static char[][] adjMatrix;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		visited = new boolean[R][C];
		alpha = new boolean['Z'+1];
		adjMatrix = new char[R][];
		for(int r = 0; r < R; r++) {
			adjMatrix[r] = bf.readLine().toCharArray();
		}
		
		dfs(0, 0, 1);
		System.out.println(maxResult);
	}
	
	static void dfs(int x, int y, int currCnt) {
		if(x < 0 || y < 0 || x >= R || y >= C) return;
		if(visited[x][y]) return;
		if(alpha[adjMatrix[x][y]]) return;
		
		
		
		visited[x][y] = true;
		alpha[adjMatrix[x][y]] = true;
		maxResult = Math.max(maxResult, currCnt);
		
		dfs(x + 1, y, currCnt + 1);
		dfs(x - 1, y, currCnt + 1);
		dfs(x, y + 1, currCnt + 1);
		dfs(x, y - 1, currCnt + 1);
		
		visited[x][y] = false;
		alpha[adjMatrix[x][y]] = false;
	}
}