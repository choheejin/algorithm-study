import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static class Node {
		int to;
		Node next;
		public Node(int to, Node next) {
			this.to = to;
			this.next = next;
		}
	}
	
	static int N = 100, len, M, start;
	static int maxValue;
	static Node[] adjList;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 1; tc <= 10; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			len = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			M = len / 2;
			
			adjList = new Node[N + 1];
			visited = new boolean[N + 1];
			
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < M; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, adjList[from]);
			}
			
			maxValue = start;
			bfs(start);
			sb.append("#").append(tc).append(" ");
			sb.append(maxValue).append("\n"); 
		}
		System.out.println(sb);
	}
	
	static void bfs(int start) {
		Queue<Integer> q = new ArrayDeque<>();
		visited[start] = true;
		q.offer(start);
		
		while(!q.isEmpty()) {
			int value = 0;
			int size = q.size();
			while(size > 0) {
				int from = q.poll();
				value = Math.max(value, from);
				
				for(Node n = adjList[from]; n != null; n = n.next) {
					int to = n.to;
					if(visited[to]) continue;
					visited[to] = true;
					q.offer(to);
				}
				
				maxValue = value;
				size--;
			}
		}
	}
}