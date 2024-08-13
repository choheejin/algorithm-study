import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static int T;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(bf.readLine());
			int[][] graph = new int[N+1][N+1];
			
			int minCol = 0, minRow = 0;
			int maxCol = N, maxRow = N;
			int cnt = 1;
			for(int i = 0; i < N / 2 +1 ; i++) {
				for(int j = minCol; j < maxCol; j++) {
					graph[minRow][j] = cnt++;
				}
				minRow++;
				for(int j = minRow; j < maxRow; j++) {
					graph[j][maxCol-1] = cnt++;
				}
				maxCol--;
				for(int j = maxCol-1; j >= minCol; j--) {
					graph[maxRow-1][j] = cnt++;
				}
				maxRow--;
				for(int j = maxRow-1; j >= minRow; j--) {
					graph[j][minCol] = cnt++;
				}
				minCol++;
			}
			System.out.printf("#%d\n",tc);
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					System.out.printf("%d ", graph[i][j]);
				}
				System.out.println();
			}
		}
	}
}