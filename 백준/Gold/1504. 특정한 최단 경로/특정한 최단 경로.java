import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
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

	static int N, E;
	static int v1, v2;
	static Node[] adjList;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		
		adjList = new Node[N+1];
		
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, weight, adjList[from]);
			adjList[to] = new Node(from, weight, adjList[to]);
		}
		
		st = new StringTokenizer(bf.readLine());
		
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		// 1 -> v1 -> v2 -> N;
		int root1_v1 = getMin(1, v1);
		int rootv1_v2 = getMin(v1, v2);
		int rootv2_N = getMin(v2, N);
		
		int result1 = (root1_v1 == -1 || rootv1_v2 == -1 || rootv2_N == -1) ? Integer.MAX_VALUE : root1_v1 + rootv1_v2 + rootv2_N; 
		
		// 1 -> v2 -> v1 -> N;
		int root1_v2 = getMin(1, v2);
		int rootv2_v2 = getMin(v2, v1);
		int rootv1_N = getMin(v1, N);
		
		int result2 = (root1_v2 == -1 || rootv2_v2 == -1 || rootv1_N == -1) ? Integer.MAX_VALUE : root1_v2 + rootv2_v2 + rootv1_N; 
		
		int result = Math.min(result1, result2);
		
		System.out.println(result == Integer.MAX_VALUE ? -1 : result);
	}

	static int getMin(int start, int end) {
		boolean[] visited = new boolean[N+1];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		int[] minDistance = new int[N+1];
		
		Arrays.fill(minDistance, Integer.MAX_VALUE);
		
		pq.offer(new int[] {start, 0});
		minDistance[start] = 0;
		
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int from = curr[0], min = curr[1];
			
			if(visited[from]) continue;
			visited[from] = true;
			
			if(from == end) return min;
			
			for(Node n = adjList[from]; n != null; n=n.next) {
				if(visited[n.vertex]) continue;
				if(minDistance[n.vertex] > min + n.weight) {
					minDistance[n.vertex] = min + n.weight;
					pq.offer(new int[] {n.vertex, minDistance[n.vertex]});
				}
			}
			
		}
		
		return -1;
	}
	
}