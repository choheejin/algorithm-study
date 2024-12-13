import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

// 간단한 위상정렬 문
public class Main {
	
	static int[] indegree;
	static int[] costs;
	static int n, k, goal;
	
	static class Node {
		int to;
		Node next;
		
		public Node (int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			n = Integer.parseInt(st.nextToken()); // 건물의 개수 
			k = Integer.parseInt(st.nextToken()); // 간선의 개수 
			
			costs = new int[n+1];
			indegree = new int[n+1];
			
			Node[] adjList = new Node[n+1];
			
			st = new StringTokenizer(bf.readLine());
			
			for(int i = 1; i <= n; i++) {
				costs[i] = Integer.parseInt(st.nextToken());
			}
			
			
			for(int i = 0; i < k; i++) {
				st = new StringTokenizer(bf.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, adjList[from]);
				indegree[to]++;
			}
			
			goal = Integer.parseInt(bf.readLine());
			
			Queue<Integer> q = new ArrayDeque<>();
			
			// 진입차수가 0인 노드 넣어주기 
			for(int i =1; i <= n; i++) {
				if(indegree[i] == 0) {
					q.offer(i);
				}
			}
			
			int[] minCosts = new int[n+1];
			
			while(!q.isEmpty()) {
				int currVertex = q.poll();
																
				
				if(currVertex == goal) {
					continue;
				}

				for(Node n = adjList[currVertex]; n != null; n = n.next) {
					indegree[n.to]--;
					minCosts[n.to] = Math.max(costs[currVertex] + minCosts[currVertex], minCosts[n.to]);
					
					if(indegree[n.to] == 0) {
						q.offer(n.to);
					}
				}
				
			}
			
			int result = minCosts[goal] + costs[goal];
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}

}