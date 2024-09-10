import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static int start, end;
	static Node[] adjList;
	
	static class Node {
		int vertex;
		int weight;
		Node next;
		
		public Node(int vertex, int weight, Node next) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer("");
		
		N = Integer.parseInt(bf.readLine());
		M = Integer.parseInt(bf.readLine());
		
		adjList = new Node[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
		}
		
		st = new StringTokenizer(bf.readLine());
		
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());
		
		int result = getMin();
		System.out.println(result);
	}

	
	static int getMin() {
		boolean[] visited = new boolean[N+1];
		int[] minDistance = new int[N+1];
		
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		
		PriorityQueue<int []> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
		
		pq.offer(new int[] {start, 0});
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int stopOver = curr[0];
			int minValue = curr[1];
			
			if(visited[stopOver]) continue;
			visited[stopOver] = true;
			
			if(stopOver == end) return minValue;
			
			for(Node n = adjList[stopOver]; n != null; n=n.next) {
				if(visited[n.vertex]) continue;
				
				if(minDistance[n.vertex] > minValue + n.weight) {
					minDistance[n.vertex] = minValue + n.weight;
					pq.offer(new int[] {n.vertex, minDistance[n.vertex]});
				}
			}
			
		}
		
		
		return -1;
	}
	
}