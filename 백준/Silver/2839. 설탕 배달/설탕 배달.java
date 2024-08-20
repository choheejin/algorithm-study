import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int result = 0;
		
		while(N > 0) {
			// 5로 나누어질때
			if(N % 5 == 0) {
				result += N / 5;
				N  = N % 5;
			} 
			// 3으로 빼준다.
			else {
				N -= 3;
				result++;	
			}
//			System.out.printf("%d %d\n", N, result);
		}
		
		if(N < 0 || result == 0) {
			System.out.println(-1);
		} else {
			System.out.println(result);
		}
	}
}