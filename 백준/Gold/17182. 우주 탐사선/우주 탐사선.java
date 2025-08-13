import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {	
    static int best = Integer.MAX_VALUE;
    static boolean[] visited;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[][] adjMatrix = new int[N][N];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			
			for(int j = 0; j < N; j++) {
				int cost = Integer.parseInt(st.nextToken());
				adjMatrix[i][j] = cost;
			}
		}
		
				
		int ans = floydWarshall(adjMatrix, N, K);
		System.out.println(ans);
	}
	
	public static int floydWarshall(int[][] adjMatrix, int N, int K) {
		int answer = 0;

		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(adjMatrix[i][k] + adjMatrix[k][j] < adjMatrix[i][j]) {
						adjMatrix[i][j] = adjMatrix[i][k] + adjMatrix[k][j];
					}
				}
			}
		}
		
		visited = new boolean[N];
		visited[K] = true;
		dfs(adjMatrix, N, K, 1, 0);
		
		
		return best;
	}
	
	public static void dfs(int[][] adjList, int N, int u, int cnt, int cost) {
		if(cost >= best) return;
		
		if(cnt == N) {
			if(cost < best) {
				best = cost;
				return;
			}
		}
		
		for(int v = 0; v < N; v++) {
			if(visited[v]) continue;
			visited[v] = true;
			dfs(adjList, N, v, cnt+1, cost + adjList[u][v]);
			visited[v] = false;
		}
	}
	
}
