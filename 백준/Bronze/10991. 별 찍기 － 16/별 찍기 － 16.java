import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		
		int odd = 0;
		int even = 1;
		
		for(int i = 1; i <=N; i++) {
			if(i == N) {
				sb.append("*");
				break;
			} else {
				sb.append(" ");
			}
		}
		sb.append("\n");
		
		for(int level = 1; level < N; level++) {
			int cnt = level % 2 == 0 ? even : odd;
			
			for(int a = N - 2; a >= 0; a--) {
				if(cnt * 2 >= a && (a % 2 != level % 2)) {
					sb.append("*");
				} else {					
					sb.append(" ");
				}
			}
						
			if(level % 2 == 0) {
				sb.append("*");
			} else {
				sb.append(" ");
			}
			
			int count = level % 2 == 0 ? 1 : 0;
			for(int a = 0; a <= N - 2; a++) {
				if(cnt * 2 >= a && (a % 2 != level % 2)) {
					sb.append("*");
					if(cnt == count) {
						break;
					}
					count++;
				} else {					
					sb.append(" ");
				}
			}			
			
			sb.append("\n");
			if(level % 2 == 0) {
				even++;
			}
			else {
				odd++;
			}
		}
		System.out.print(sb);
	}
}