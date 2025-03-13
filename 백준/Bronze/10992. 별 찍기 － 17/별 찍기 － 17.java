import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < N - 1; i++) {
		
			for(int a = 0; a < N; a++) {
				if(a == N - 1 - i) {
					sb.append("*");
					continue;
				}
				sb.append(" ");
			}
			
			for(int b = 1; b < N - 1; b++) {
				if(i == 0) continue;
				if(b == i) {
					sb.append("*");
					break;
				}
				sb.append(" ");
			}
			sb.append("\n");
		}
		
		for(int i = 0; i < 2 * N - 1; i++) {
			sb.append("*");
		}
		System.out.print(sb);
	}
}