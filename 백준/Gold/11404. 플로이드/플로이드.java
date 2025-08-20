import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 플로이드 워샬 - 모든 정점으로의 최단 비용을 구해야하는 상황	
	// N <= 100 이므로 시간복잡도 충분
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		int M = Integer.parseInt(bf.readLine());
		
		int[][] dists = new int[N+1][N+1];
		
		
		for(int i = 1; i <= N; i++) {
			Arrays.fill(dists[i], Integer.MAX_VALUE);
		}


		for(int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			if(dists[a][b] == Integer.MAX_VALUE) {
				dists[a][b] = c;
			} else {
				dists[a][b] = Math.min(dists[a][b], c);
			}
		}
		
		for(int k = 1; k <= N; k++) {
			for(int i = 1; i <= N; i++) {
				if(dists[i][k] == Integer.MAX_VALUE) continue;
				for(int j = 1; j <= N; j++) {
					if(i == j) continue;
					if(dists[k][j] == Integer.MAX_VALUE) continue;
					
					if(dists[i][k] + dists[k][j] < dists[i][j]) {
						dists[i][j] = dists[i][k] + dists[k][j];
					}
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 1; i <= N; i++) {
			for(int j = 1; j <= N; j++) {
				int result = dists[i][j] == Integer.MAX_VALUE ? 0 : dists[i][j];
				sb.append(result).append(" ");
			}
			sb.append("\n");
		}
		
		
		System.out.print(sb);
	}
}
