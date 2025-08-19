import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
	static Point[] points;
	static List<Integer>[] adjList;
	static int[][] dists;
	
	static class Point{
		int x;
		int y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		points = new Point[N];
		adjList = new ArrayList[N];
		dists = new int[N][N];
				
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			points[i] = new Point(x, y);
			adjList[i] = new ArrayList<>();
            Arrays.fill(dists[i], Integer.MAX_VALUE);
		}
		
		initIsolatePoint(N);
		floyd(N);
		
		int Q = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < Q; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			int A = Integer.parseInt(st.nextToken()) - 1;
			int B = Integer.parseInt(st.nextToken()) - 1;
				
			sb.append(dists[A][B] == Integer.MAX_VALUE ? -1 : dists[A][B]).append("\n");
		}
		System.out.print(sb);
		
	}
	
	public static void floyd(int N) {
		for(int k = 0; k < N; k++) {
			for(int i = 0; i < N; i++) {
				if(dists[i][k] == Integer.MAX_VALUE) continue;
				for(int j = 0; j < N; j++) {
					if(dists[k][j] == Integer.MAX_VALUE) continue;
					if(dists[i][k] + dists[k][j] < dists[i][j]) {
						dists[i][j] = dists[i][k] + dists[k][j];
					}
				}
			}
		}
	}
	
	public static void initIsolatePoint(int N) {
		for(int i = 0; i < N; i++) {
			for(int j = i+1; j < N; j++) {
				if(points[i].y < points[j].x || points[i].x > points[j].y) continue;
				dists[i][j] = 1;
				dists[j][i] = 1;
			}
		}
	}
}
