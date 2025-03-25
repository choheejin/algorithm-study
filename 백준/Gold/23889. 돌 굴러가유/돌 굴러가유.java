import java.io.BufferedReader;
import java.io.InputStreamReader;
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
		int idx, sum;
		
		public Wall(int idx, int sum) {
			this.idx = idx;
			this.sum = sum;
		}
		
		@Override
		public String toString() {
			return "[" + idx + "," + sum + "]";			
		}
		
		@Override
		public int compareTo(Wall o) {
			if(o.sum == sum) {
				return Integer.compare(idx, o.idx);
			}
			return Integer.compare(o.sum, sum);
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
		
		int[] dp = new int[N + 1]; // 누적합 
		for(int i = 1; i <= N; i++) {
			dp[i] = dp[i-1] + Integer.parseInt(st.nextToken());
		}
		
		int[] points = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		
		List<Wall> list = new LinkedList<>();
		
		int currPointIdx = 0;
		
		while(currPointIdx <= K - 1) {
			int idx = points[currPointIdx];
			if(currPointIdx == K - 1) {
				int sum = idx == N ? dp[N] - dp[N-1] : dp[N] - dp[idx - 1];
				Wall newWall = new Wall(idx, sum);
				list.add(newWall);
				break;
			}

			
			int sum = dp[points[currPointIdx + 1] - 1] - dp[idx - 1];
			Wall newWall = new Wall(idx, sum);
			list.add(newWall);
			currPointIdx++;
		}
		
		Collections.sort(list);
//		System.out.println(Arrays.toString(dp));
//		System.out.println(list);
		
		List<Integer> answer = new LinkedList<>();
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
