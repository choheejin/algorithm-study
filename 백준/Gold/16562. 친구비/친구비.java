import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static int[] costs;
	
	static ArrayList<Integer>[] list;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		costs = new int[N + 1];
		list = new ArrayList[N+1];
		
		st = new StringTokenizer(bf.readLine());
		
		for(int i = 1; i <= N; i++) {
			costs[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList<Integer>();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			list[a].add(b);
			list[b].add(a);
		}
		
		isVisited = new boolean[N+1];
		int result = 0;

		for(int i = 1; i <= N; i++) {
			if(isVisited[i]) continue;
			result += dfs(i);
		}
		
		if(result <= K) {
			System.out.println(result);
		} else {
			System.out.println("Oh no");
		}
	}
	
	public static int dfs(int v) {
		isVisited[v] = true;
		
		int minCost = costs[v];
		
		for(int nxt : list[v]) {
			if(isVisited[nxt]) continue;
			minCost = Math.min(minCost, dfs(nxt));
		}
		
//		isVisited[v] = false;
		
		return minCost;
	}
}
