import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 1. 진입차수를 계산해준다.
	// 2. 진입차수가 0인 부분을 큐에 넣어준다.
	// 3. 큐에서 빼내서 해당 노드와 인접한 노드들의 진입차수를 -1 해준다
		// => 연결해제를 해준다는 것을 의미한다.
	// 4. 진입차수가 0인 부분들을 큐에 넣어준다.
	// 5. 큐에 들어간 것이 없을 때까지 반복
	
	static int N, M;
	static int[] indegrees;
	static Node[] adjList;
	static Queue<Integer> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();
	
	static class Node {
		int to;
		Node next;
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		indegrees = new int[N+1];
		adjList = new Node[N+1];
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList[from] = new Node(to, adjList[from]);
			indegrees[to]++;
		}
		
		setStartPoints();
		topology();
		System.out.println(sb);
	}
	
	static void setStartPoints() {
		for(int i = 1; i <= N; i++) {
			if(indegrees[i] == 0) q.offer(i);
		}
	}
	
	static void topology() {
		while (!q.isEmpty()) {
			int from = q.poll();
			sb.append(from).append(" ");
			
			for(Node n = adjList[from]; n != null; n = n.next) {
				int to = n.to;
				
				indegrees[to]--;
				
				if(indegrees[to] == 0) {
					q.offer(to);
				}
			}
		}
	}
}