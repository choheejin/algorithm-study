import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static int N;
	static char[][] adjMatrix;
	static boolean[][] visited;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		adjMatrix = new char[N][];
		for(int i = 0; i < N; i ++) {
			adjMatrix[i] = bf.readLine().toCharArray();
		}
		
		int resultA = 0, resultB = 0;
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dfs(i, j, 'R')) resultA++;
				if(dfs(i, j, 'B')) resultA++;
				if(dfs(i, j, 'G')) resultA++;
			}
		}
		
		visited = new boolean[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(dfs(i, j, 'R', 'G')) resultB++;
				if(dfs(i, j, 'B')) resultB++;
			}
		}
		
		System.out.println(resultA + " " + resultB);
	}
	
	static boolean dfs(int x, int y, char target) {
		if(x < 0 || y < 0 || x >= N || y >= N) return false;
		
		if(!visited[x][y] && adjMatrix[x][y] == target) {
			visited[x][y]=true;
			dfs(x + 1, y, target);
			dfs(x - 1, y, target);
			dfs(x, y + 1, target);
			dfs(x, y - 1, target);
			return true;
		}
		
		return false;
	}
	
	static boolean dfs(int x, int y, char target1, char target2) {
		if(x < 0 || y < 0 || x >= N || y >= N) return false;
		
		if(!visited[x][y] && (adjMatrix[x][y] == target1 || adjMatrix[x][y] == target2)) {
			visited[x][y]=true;
			dfs(x + 1, y, target1, target2);
			dfs(x - 1, y, target1, target2);
			dfs(x, y + 1, target1, target2);
			dfs(x, y - 1, target1, target2);
			return true;
		}
		
		return false;
	}

}