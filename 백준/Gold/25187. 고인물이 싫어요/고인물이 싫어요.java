import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static List<Integer>[] list;
	static int[] kind;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
		kind = new int[N + 1];
		list = new ArrayList[N + 1];
		
		st = new StringTokenizer(bf.readLine());
		
		for(int i = 1; i <= N; i++) {
			kind[i] = Integer.parseInt(st.nextToken());
			list[i] = new ArrayList();
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
		
			list[u].add(v);
			list[v].add(u);
		}
	
		StringBuilder sb = new StringBuilder();
		
		boolean[] visited = new boolean[N+1];
		Queue<Integer> q = new ArrayDeque();
		int[] results = new int[N + 1];
		
		for(int i = 1; i <= N; i++) {
			if(visited[i]) continue;
			ArrayList<Integer> tmp = new ArrayList<>();

			int p = 0, d = 0;
			visited[i] = true;
			q.offer(i);

	 		while(!q.isEmpty()) {
				int curr = q.poll();
				
				if(kind[curr] == 1) {
					p++;
				} else {
					d++;
				}
				
				tmp.add(curr);

				for(int n : list[curr]) {
					if(visited[n]) continue;
					visited[n] = true;
					q.offer(n);
				}
			}
	 		
	 		for(int t: tmp) {
				results[t] = p > d ? 1 : 0;
	 		}
		}
		
		while(Q-- > 0) {
			int K = Integer.parseInt(bf.readLine());
						
			sb.append(results[K]).append("\n");
		}
		System.out.println(sb);
	}
}
