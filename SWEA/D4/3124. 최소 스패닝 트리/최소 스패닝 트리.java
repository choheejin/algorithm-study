import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Node implements Comparable<Node> {
		int v;
		int weight;
		
		public Node(int to, int weight) {
			this.v = to;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Integer.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "[" + v + "," + weight + "]";
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int V = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			List<Node>[] adjList = new List[V+1];
			for(int i=1; i<=V; i++) adjList[i] = new ArrayList<>();
			
			boolean[] visited = new boolean[V+1]; // 방문여부 배열 (트리 정점 배열)
			Queue<Node> q = new PriorityQueue<> ();

			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				adjList[from].add(new Node(to,weight));
				adjList[to].add(new Node(from, weight));
			}
			
			// 정점 1을 시작포인트로 지정
//			for(int i = 1; i <= V; i++) {
//				if(adjMatrix[1][i] != 0) q.offer(new Node(1, i, adjMatrix[1][i]));
//			}
			
			q.offer(new Node(1, 0));
			
			long cost = 0;
			int cnt = 0;
			while(!q.isEmpty()) {
				// 최소 신장 트리가 완성된 경우 break;
				// 트리 구성에 포함될 가장 유리한 정점 선택 (비트리 정점 중 최소 비용 간선의 정점 선택)
				Node curr = q.poll();
				
				int min = curr.weight;
//				int from = curr.from;
				int from = curr.v;

				if(visited[from]) continue;
				
							
				visited[from] = true;	//
				cost += (long) min;

				if(++cnt == V) break;
				
				// 선택된 정점과 타 정점들 간선 비용 비교하기 (간보기)	
				for(Node n : adjList[from]) {
					if(visited[n.v]) continue;
					q.offer(new Node(n.v, n.weight));
				}
			}
			sb.append("#").append(tc).append(" ").append(cost).append("\n");
		}
		System.out.println(sb);
	}

}