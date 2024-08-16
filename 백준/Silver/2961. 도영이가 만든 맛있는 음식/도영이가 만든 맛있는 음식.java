import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[][] foods;
	static boolean[] isSelected;
	static int minValue;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		foods = new int[N][2];
		isSelected = new boolean[N];
		minValue = Integer.MAX_VALUE;
		
		for(int i = 0;  i < N; i ++) {
			String[] inputs = bf.readLine().split(" ");
			foods[i][0] = Integer.parseInt(inputs[0]);
			foods[i][1] = Integer.parseInt(inputs[1]);
		}
		
		subset(0, 1, 0, 0);
		System.out.println(minValue);
	}
	
	private static void subset(int cnt, int totalR, int totalL, int eCnt) {
		if(minValue < totalR - totalL) {
			return;
		}
		if(cnt == N) {
			if(eCnt > 0) minValue = Math.min(minValue, Math.abs(totalR - totalL));
			return;
		}
		isSelected[cnt] = true;
		subset(cnt + 1, totalR * foods[cnt][0], totalL + foods[cnt][1], eCnt+1);
		isSelected[cnt] = false;
		subset(cnt + 1, totalR, totalL, eCnt);
		
	}
}