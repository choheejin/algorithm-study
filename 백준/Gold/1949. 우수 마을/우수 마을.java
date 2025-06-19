import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static List<Integer>[] adjList;
	static int[] nums, dp[];
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		nums = new int[N+1];
		dp = new int[N+1][2];
		visited = new boolean[N+1];
		adjList = new ArrayList[N+1];
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 1; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			adjList[i] = new ArrayList<>();
		}
		
		for(int i = 1; i <= N-1; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from].add(to);
			adjList[to].add(from);
		}
		
		dfs(1);
		System.out.println(Math.max(dp[1][0], dp[1][1]));
	}
	
	static void dfs(int node) {
		visited[node] = true;
		dp[node][1] = nums[node];
		
		for(int child: adjList[node]) {
			if(visited[child]) continue;
			
			dfs(child);
			
			dp[node][0] += Math.max(dp[child][0], dp[child][1]);
			dp[node][1] += dp[child][0];
		}
	}
}
