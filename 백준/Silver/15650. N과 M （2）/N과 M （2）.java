import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static StringBuilder sb;
	static int N, M;
	static int[] nums;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		String[] inputs = bf.readLine().split(" ");
		N = Integer.parseInt(inputs[0]);
		M = Integer.parseInt(inputs[1]);
		nums = new int[M];
		combi(0, 1);
		System.out.println(sb);
	}
	
	private static void combi(int cnt, int start) {
		if(cnt == M) {
			print();
			return;
		}
		
		for(int i = start; i <= N; i++) {
			nums[cnt] = i;
			combi(cnt+1, i + 1);
		}
	}
	
	private static void print() {
		for(int i = 0; i < M; i++) {
			sb.append(nums[i]).append(" ");
		}
		sb.append("\n");
	}
}