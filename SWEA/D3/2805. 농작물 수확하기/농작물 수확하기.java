import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	// 가운데 좌표를 구해서
	// 위/아래로 N/2, N/2-1, .. 0 까지 반복
	static int N, result;
	static int[][] graph;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			graph = new int[N][N];
			result = 0;
			for(int i = 0; i < N; i++) {
				String[] inputs = bf.readLine().split("");
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(inputs[j]);
				}
			}
			
			int startX = N/2, startY = N/2, cnt = 0;

			for(int i = 0; i < N; i++) {
				result += graph[i][startX];
				result += graph[startY][i];
			}
			
			result -= graph[startY][startX];
			
			for(int i = 1; i < N/2; i++) {
//				System.out.printf(":::%d\n", N/2-cnt);
				for(int j = 1; j < N/2 - cnt; j++) {
					// 1사분면
					result += graph[startY + i][startX + j];
					// 2사분면
					result += graph[startY + i][startX - j];
					// 3사분면
					result += graph[startY - i][startX - j];
					// 4사분면
					result += graph[startY - i][startX + j];
//					System.out.print(graph[startY + i][startX + j]);
//					System.out.print(", ");
//					System.out.print(graph[startY - i][startX + j]);
//					System.out.print(", ");
//					System.out.print(graph[startY - i][startX - j]);
//					System.out.print(", ");
//					System.out.print(graph[startY + i][startX - j]);
//					System.out.print("\n");
				}
				cnt++; 
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}