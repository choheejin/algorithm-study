import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class Main {	
	
	static class Point {
		int x, y;
		
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int N;
	static double minValue;
	static Point[] points;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			
			points = new Point[N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());

				points[i] = new Point(x, y);
			}
			
			minValue = Double.MAX_VALUE;
			makeOrders(0, 0, 0, new boolean[N], 0, 0);
			
			String result = minValue == 0.0 ? "0.000000000000" : String.valueOf(minValue);
			
			sb.append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	
	public static void makeOrders(int idx, int total, int num, boolean[] checked, int x, int y) {		
		if(total == N) {
			if(num == N/2) {
				minValue = Math.min(minValue, Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)));
			}
			return;
		}
		
		makeOrders(idx + 1,total + 1, num, checked, points[idx].x + x, points[idx].y + y);
		checked[idx] = true;
		makeOrders(idx + 1, total + 1, num + 1, checked, x - points[idx].x, y - points[idx].y);
		checked[idx] = false;
	}
}