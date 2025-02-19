import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Edge {
		int src, des, cost;
		
		public Edge(int src, int des, int cost) {
			this.src = src;
			this.des = des;
			this.cost = cost;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int countryNum = Integer.parseInt(st.nextToken());
		int busNum = Integer.parseInt(st.nextToken());
		
		List<Edge> edgeList = new ArrayList<>();
		
		for(int i = 0; i < busNum; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int src = Integer.parseInt(st.nextToken());
			int des = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			
			edgeList.add(new Edge(src, des, cost));
		}
	
		Long[] dist = new Long[countryNum + 1];		
		Arrays.fill(dist, Long.MAX_VALUE);
		dist[1] = Long.valueOf(0);

		for(int i = 0; i < busNum - 1; i ++) {
			for(Edge edge: edgeList) {
				if(dist[edge.src] != Long.MAX_VALUE && dist[edge.des] > edge.cost + dist[edge.src]) {
					dist[edge.des] = edge.cost + dist[edge.src];
				}
			}
		}
		
		for(Edge edge : edgeList) {
			if(dist[edge.src] != Long.MAX_VALUE && dist[edge.des] > edge.cost + dist[edge.src]) {
				System.out.println("-1");
				return;
			}
		}
		StringBuilder sb = new StringBuilder();
		for(int i = 2; i < countryNum + 1; i++ ) {
			if(dist[i] == Long.MAX_VALUE) {
				sb.append("-1").append("\n");
			}
			else {
				sb.append(dist[i]).append("\n");
			}
		}
		System.out.println(sb);
	}
}