import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, L, maxValue;
	static int[][] foods;
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			maxValue = 0;
			foods = new int[N][2];
			isSelected = new boolean[N];
			for(int i = 0; i < N; i ++) {
				st = new StringTokenizer(bf.readLine());
				foods[i][0] = Integer.parseInt(st.nextToken());
				foods[i][1] = Integer.parseInt(st.nextToken());
			}
			subset(0, 0, 0);
			sb.append("#").append(tc).append(" ").append(maxValue).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void subset(int cnt, int totalT, int totalK) {
		if(totalK > L) {
			return;
		}
		if(cnt == N) {
			maxValue = Math.max(maxValue, totalT);
			return;
		}
		isSelected[cnt] = true;
		subset(cnt + 1, totalT + foods[cnt][0], totalK + foods[cnt][1]);
		isSelected[cnt] = false;
		subset(cnt + 1, totalT, totalK);
		
	}

}