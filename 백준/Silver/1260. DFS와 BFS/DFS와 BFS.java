import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, V;
	static int[][] adjMatrix;
	static boolean[] visited;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		sb = new StringBuilder();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		adjMatrix = new int[N+1][N+1];
		
		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjMatrix[from][to] = adjMatrix[to][from] = 1;
		}
		
		visited = new boolean[N+1];
		dfs(V);
		sb.append("\n");
		visited = new boolean[N+1];
		bfs(V);
		System.out.println(sb);
	}
	
	static void dfs(int from) {
		visited[from] = true;
		sb.append(from).append(" ");
		
		for(int v = 0; v <=N; v++) {
			if(!visited[v] && adjMatrix[from][v] == 1) {
				dfs(v);
			}
		}
	}
	
	static void bfs(int start) {
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		deque.offer(start);
		sb.append(start).append(" ");
		visited[start] = true; 
		while(!deque.isEmpty()) {
			int from = deque.poll();
			for(int v = 0; v <=N; v++) {
				if(!visited[v] && adjMatrix[from][v] == 1) {
					sb.append(v).append(" ");
					visited[v] = true; 
					deque.offer(v);
				}
			}
		}
	}
}