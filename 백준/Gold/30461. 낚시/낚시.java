import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 누적합 문제 

	static int N, M, Q;
	static int[][] sum, graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		sum = new int[N][M];
		graph = new int[N][M];
		
		for(int n = 0; n < N; n++) {
			st = new StringTokenizer(bf.readLine());
			for(int m = 0; m < M; m++) {
				graph[n][m] = Integer.parseInt(st.nextToken());
				if(n == 0) sum[n][m] = graph[n][m];
				else {
					sum[n][m] = graph[n][m] + sum[n-1][m];
				}
			}
		}
		
		for(int m = 1; m < M; m++) {
			for(int n = 1; n < N; n++) {
				sum[n][m] += sum[n-1][m-1];
			}
		}
//		
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(bf.readLine());
			
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			
			int result = sum[a][b];
						
			sb.append(result).append("\n");
		}
		
		System.out.println(sb);
		
//		for(int i = 0; i < N; i++) {
//			System.out.println(Arrays.toString(sum[i]));
//		}
	}
}
