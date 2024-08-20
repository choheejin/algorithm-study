import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 가능한 조합을 모두 찾아서 최소를 구한다.
	// 2 ^ 20 : 충분한 시간복잡도.
	
	static int N, B, minValue;
	static int[] people;
	static boolean[] isSelected;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			people = new int[N];
			isSelected = new boolean[N];
			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++) {
				people[i] = Integer.parseInt(st.nextToken());
			}
			minValue = Integer.MAX_VALUE;
			subset(0, 0);
			sb.append("#").append(tc).append(" ").append(minValue - B).append("\n");
		}
		System.out.println(sb);
	}
	
	private static void subset(int cnt, int total) {
		if(cnt == N) {
			if(total >= B) minValue = Math.min(minValue, total);
			return;
		}
		
		isSelected[cnt] = true;
		subset(cnt + 1, total + people[cnt]);
		isSelected[cnt] = false;
		subset(cnt + 1, total);
	}
}