import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	// 문제의 설명 과정이 BFS 이다.
	// visited 배열 필요로 함 (이미 연락한 노드엔 더이상 연락을 취하지 않는다)
	// 큐에 넣어줄 때, 노드의 최대값 갱신 필요
	
	static int N = 100, len, start, maxValue, maxLevel;
	static Node[] adjList;
	static boolean[] visited;
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
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			len = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			visited = new boolean[N + 1];
			adjList = new Node[N + 1];
			
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < len/2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, adjList[from]);
			}
			
			maxValue = start;
			maxLevel = 1;
			
			bfs(start);
			sb.append("#").append(tc).append(" ");
			sb.append(maxValue).append("\n"); 
		}
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {start, 1});
		visited[start] = true;
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			
			int from = curr[0];  	// 출발점
			int level = curr[1]; 	// bfs 단계
			
			if(maxLevel < level) {
				maxLevel = level;
				maxValue = from;
			} else if (maxLevel == level) {
				maxValue = Math.max(maxValue, from);
			}
			

			for(Node n = adjList[from]; n != null; n = n.next) {
				int to = n.to;
				if(visited[to]) continue;
				
				visited[to] = true;
				q.offer(new int[] {to, level + 1});
			}
		}
	}
}