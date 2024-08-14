import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * @author 조희진
 *
 */

public class Main {
	// 예상 시간 복잡도: O(3 * 100,000) => 시간 초과 왜...??
	
	static int N, M;
	static int[] arr;
	static int[][] arrSum;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		arr = new int[N];
		arrSum = new int[N][1];
		st = new StringTokenizer(bf.readLine());
		
		arr[0] = Integer.parseInt(st.nextToken());
		arrSum[0][0] = arr[0];
		
		for(int i = 1; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			arrSum[i][0] += arrSum[i-1][0] + arr[i];
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());
			int s = Integer.parseInt(st.nextToken()), e = Integer.parseInt(st.nextToken());
			sb.append(sum(s-1, e-1)).append("\n");
		}
		System.out.println(sb);
	}
	
	private static int sum(int s, int e) {
		if(s == e) return arr[s];
		if(s-1 >= 0) {
			return arrSum[e][0] - arrSum[s-1][0];
		}
		return arrSum[e][0];
	}
}