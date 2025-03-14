import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(bf.readLine());
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < i; j++) {
				sb.append(" ");
			}
			for(int j = 0; j < 2 * N - 1 - (i * 2); j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		for(int i = N - 2; i >= 0; i--) {
			for(int j = 0; j < i; j++) {
				sb.append(" ");
			}
			for(int j = 0; j < 2 * N - 1 - (i * 2); j++) {
				sb.append("*");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}