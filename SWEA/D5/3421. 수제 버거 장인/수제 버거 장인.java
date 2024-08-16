import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int T, N, M, result;
	static boolean[][] bad;
	static boolean[] isSelected;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			isSelected = new boolean[N+1];
			bad = new boolean[N+1][N+1];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
				bad[a][b] = true; 
				bad[b][a] = true;
			}
			result = 0;
			subset(1);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void subset(int cnt) {
		if(cnt == N+1) {
			result++;
			return;
		}
		if(check(cnt)) {
			isSelected[cnt] = true;
			subset(cnt + 1);
		}
		isSelected[cnt] = false;
		subset(cnt + 1);
	}
	
	private static boolean check(int cnt) {
		for(int i = 1; i <= N; i++) {
			if(bad[i][cnt] && isSelected[i]) {
				return false; 
			}
		}
		return true;
	}
}