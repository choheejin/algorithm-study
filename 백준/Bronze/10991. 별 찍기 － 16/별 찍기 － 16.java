import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		for(int i = 1; i <= N; i++) {
			for(int j = 0; j < N - i; j++) {
				sb.append(" ");
			}
			for(int j = 1; j <= i; j++) {
                sb.append("* ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}