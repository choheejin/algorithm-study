import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	// 프림 알고리즘
	
	static class Node {
		int to, weight;
		
		public Node (int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	
	}
	
	static int N, M;
	static ArrayList<Node>[] lists;
	static boolean[] isVisited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		lists = new ArrayList[N + 1];
		isVisited = new boolean[N + 1];
		
		for(int i = 1; i <= N; i++) lists[i] = new ArrayList<>();
	
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			lists[from].add(new Node(to, w));
			lists[to].add(new Node(from, w));
		}
		
		int result = 0;
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		pq.offer(new int[] {1, 0});
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = curr[0];
			int w = curr[1];
			
			isVisited[from] = true;
			
			if(from == N) {
				result = w;
				break;
			}
			
			for(Node nxt: lists[from]) {
				if(isVisited[nxt.to]) continue;
				pq.offer(new int[] {nxt.to, w + nxt.weight});
			}
		}
		
		System.out.println(result);
	}

}
