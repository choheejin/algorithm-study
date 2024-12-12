import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// step 1) 모든 인원에 대해서 출발 노드에서부터의 모든 정점으로의 최단 거리를 구한다.
// step 2) 모든 인원에 대해서 특정 정점들의 최소 거리를 더해서 최단 비용 및 인덱스를 갱신해준다.
public class Main {
	
	static class Node {
		public int distance;
		public int to;
		public Node next;
		
		public Node (int to, int distance, Node next) {
			this.to = to;
			this.distance = distance;
			this.next = next;
		}
	}
	
	static int n, p, q;
	static long[][] minCosts;
	static int[] startPoints;
	static Node[] adjList;
	
	public static void dijkstra(int start, long[] minCost) {		
		PriorityQueue<long[]> pq = new PriorityQueue<>((a, b) -> Long.compare(a[1], b[1]));
		boolean[] visited = new boolean[p+1];
		
		minCost[start] = 0;
		pq.offer(new long[] {start, 0});
		
		while(!pq.isEmpty()) {
			long[] node = pq.poll();
			
			int currIdx = Long.valueOf(node[0]).intValue();
			long currCost = node[1];
			
            if (visited[currIdx]) continue;
			visited[currIdx] = true;
			
			for(Node n = adjList[currIdx]; n != null; n = n.next) {				
				if(!visited[n.to] && minCost[n.to] >  currCost + n.distance) {
					minCost[n.to] = currCost + n.distance;
					pq.offer(new long[] {n.to, minCost[n.to]});
				}
			}
		}		
	}
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			n = Integer.parseInt(st.nextToken());
			p = Integer.parseInt(st.nextToken());
			q = Integer.parseInt(st.nextToken());
						
			minCosts = new long[n][p+1];
			startPoints = new int[n];
			adjList = new Node[p+1];
			
			// 인원에 따른 시작 포인트 초기화
			for(int i = 0; i < n; i++) {
				startPoints[i] = Integer.parseInt(bf.readLine());
				Arrays.fill(minCosts[i], Long.MAX_VALUE);
			}
			
			// 인접리스트 초기화
			for(int i = 0; i < q; i++) {
				st = new StringTokenizer(bf.readLine());
				
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				int d = Integer.parseInt(st.nextToken());
				
				adjList[from] = new Node(to, d, adjList[from]);
				adjList[to] = new Node(from, d, adjList[to]); // 추가
			}
			
			// 모든 인원에 대해서 각각의 최소비용 구함 
			for(int pIdx = 0; pIdx < n; pIdx++) {
				dijkstra(startPoints[pIdx], minCosts[pIdx]);
			}
			
			long minCost = Long.MAX_VALUE;
			int minCostIdx = -1;
			
			// 최단 비용 구하기 
			for(int pIdx = 1; pIdx <= p; pIdx++) {
                long totalCost = 0;
                boolean reachable = true;

				for(int person = 0; person < n; person++) {
                    if (minCosts[person][pIdx] == Long.MAX_VALUE) {
                        reachable = false;
                        break;
                    }

                    totalCost += minCosts[person][pIdx] * minCosts[person][pIdx];
                    
				}
				
                if (reachable && totalCost < minCost) {
                    minCost = totalCost;
                    minCostIdx = pIdx;
                }
				
			}
			
			sb.append(minCostIdx).append(" ").append(minCost).append("\n");
		}
		
		System.out.println(sb);
	
	}

}