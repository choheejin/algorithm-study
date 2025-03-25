import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 
7 2 3
2 5 3 7 1 6 8
1 4 7
 */

public class Main {
	public static class Wall implements Comparable<Wall>{
		int idx;
		long sum;
		
		public Wall(int idx, long sum) {
			this.idx = idx;
			this.sum = sum;
		}
		
		@Override
		public String toString() {
			return "[" + idx + "," + sum + "]";			
		}
		
		@Override
		public int compareTo(Wall o) {
		    if (this.sum == o.sum) {
		        return Integer.compare(this.idx, o.idx);
		    }
		    return Long.compare(o.sum, this.sum);
		}
	}
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 마을의 개수 
		int M = Integer.parseInt(st.nextToken()); // 벽의 개수 
		int K = Integer.parseInt(st.nextToken()); // 돌의 개수 
		
		st = new StringTokenizer(br.readLine());
		
		long[] dp = new long[N + 1]; // 누적합 
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int[] points = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int i =0 ; i< K; i++) {
			points[i] = Integer.parseInt(st.nextToken());
		}
		
		
		List<Wall> list = new ArrayList<>();
		
		
		for(int i = 0; i < K-1; i++) {
			long sum = dp[points[i + 1] - 1] - dp[points[i] -1];
			Wall newWall = new Wall(points[i], sum);
			list.add(newWall);
		}
		
		long sum = dp[N] - dp[points[K-1]-1];
		list.add(new Wall(points[K-1],sum));
		
		Collections.sort(list);
//		System.out.println(Arrays.toString(dp));
//		System.out.println(list);
		
		List<Integer> answer = new ArrayList<>();
		for(int i = 0; i < M; i++) {
			answer.add(list.get(i).idx);
		}
		Collections.sort(answer);
		
		for(int i : answer) {
			sb.append(i).append("\n");
		}
		System.out.println(sb);
		
	}
}
