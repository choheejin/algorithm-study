import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int len, start, size, N = 100;
	static int maxValue, maxLevel;
	static boolean[][] adjMatrix;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int tc =1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			len = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			size = len / 2;
			
			adjMatrix = new boolean[N+1][N+1];
			visited = new boolean[N+1];
			
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < size; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjMatrix[from][to] = true;
			}
			
			maxLevel = 1;
			maxValue = start;
			
			bfs(start);
			
			sb.append("#").append(tc).append(" ");
			sb.append(maxValue).append("\n"); 
		}
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start, 1});
		visited[start] = true;
		
		while(!q.isEmpty()) {
			 int[] curr = q.poll();
			 
			 int from = curr[0];
			 int currLevel = curr[1];
			 
			 if(maxLevel < currLevel) {
				 maxLevel = currLevel;
				 maxValue = from;
			 } else if(maxLevel == currLevel) {
				 maxValue = Math.max(maxValue, from);
			 }
			 
			 for(int to = 1; to <= N; to++) {
				 if(!adjMatrix[from][to]) continue;
				 if(visited[to]) continue;
				 visited[to] = true;
				 q.offer(new int[] {to, currLevel + 1});
			 }
		}
	}
}