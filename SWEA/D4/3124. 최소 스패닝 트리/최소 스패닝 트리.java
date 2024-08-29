import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Solution {
	
	static class Edge implements Comparable<Edge>{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	
	static int T, V, E;
	static int[] parents;
	static Edge[] edges;
	
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			parents = new int[V + 1];
			edges = new Edge[E];
			
			// 간선리스트 받음
			for(int i = 0; i < E; i++) {
				st = new StringTokenizer(bf.readLine());
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				int weight = Integer.parseInt(st.nextToken());
				
				edges[i] = new Edge(start, end, weight);
			}
			
			// 각 정점을 단일서로소 집합으로 만들어준다.
			make();
			
			// 가중치 기준으로 간선 리스트를 정렬한다.
			Arrays.sort(edges);
			
			int cnt = 0;
			long cost = 0;
			for(int i = 0; i < E; i++) {
				if(cnt == V - 1) break;
				Edge curr = edges[i];
				if(union(curr.start, curr.end)) {
					cost += curr.weight;
					cnt++;
				}
			}
			sb.append("#").append(tc).append(" ").append(cost).append("\n");
		}
		System.out.println(sb);
		
	}
	
	static void make() {
		for(int i = 1; i <= V; i++) {
			parents[i] = i;
		}
	}
	
	static int findSet(int a) {
		if(parents[a] == a) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static boolean union(int a, int b) {
		int repA = findSet(a);
		int repB = findSet(b);
		
		if(repA == repB) return false;
		
		parents[repB] = repA;
		return true;
	}
}