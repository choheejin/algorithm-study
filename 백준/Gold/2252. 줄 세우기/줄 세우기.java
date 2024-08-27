import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// 준비단계
	// 그래프를 만들 때, 진입차수 배열을 만들어줘야한다.
	// 진입차수: 나에게 들어오는 간선 (from X, to O)

	// 1. 진입차수가 0인 정점(시작 정점)를 큐에 넣는다.
	// -> 자신으로 들어오는 간선이 없다는 것은 자신보다 앞선 작업이 없다는 것

	// 2.1 큐에서 진입차수가 0인 정점을 꺼내어 자신과 인접한 정점의 간선을 제거한다.
	// 2.2 즉, 인접한 정점의 진입차수를 1 감소시킨다.
	// -> 나가는 간선이 제거된다. (상대 입장: 자신으로 들어오는 간선이 제거된다)

	// 3. 간선 제거 후 진입 차수가 0이 된 정점을 큐에 넣는다.

	// 4. 큐가 공백 큐 상태가 될 때까지 2번과 3번을 반복한다.

	// 5.1 모든 노드가 다 처리 되었다면, 위상 정렬 완성
	// 5.2 모든 노드가 처리되지 않았다면, 사이클 발생

	static int N, M, size;
	static int[] indegrees;
	static Node[] adjList;
	static Queue<Integer> q = new ArrayDeque<>();
	static StringBuilder sb = new StringBuilder();;
	
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

		size = N + 1;
		
		adjList = new Node[N + 1];
		indegrees = new int[size];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			adjList[from] = new Node(to, adjList[from]);
			indegrees[to] += 1;
		}

		getStartPoint();
		topology();

		System.out.println(sb);
	}

	static void topology() {
		while (!q.isEmpty()) {
			int from = q.poll();
			sb.append(from).append(" ");

			
			for(Node n=adjList[from]; n!=null; n=n.next) {
				indegrees[n.to]--;
				
				if(indegrees[n.to] == 0) q.offer(n.to);
			}
		}
	}

	// 첫 시작이 진입차수가 0인 인덱스들
	static void getStartPoint() {
		for (int i = 1; i < size; i++) {
			if (indegrees[i] == 0) {
				q.offer(i);
			}
		}
	}
}