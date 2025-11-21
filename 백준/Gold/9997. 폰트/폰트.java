import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N;
	static int[] alphas;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(bf.readLine());
		
		alphas = new int[N];
		
		for(int i = 0; i < N; i++) {
			char[] word = bf.readLine().trim().toCharArray();
			
			for(char w: word) {
				alphas[i] = alphas[i] | (1 << ((int) w - 'a'));
			}
			
//			System.out.println(Integer.toBinaryString(alphas[i]));
		}
		
		System.out.println(subset(0, 0));
	}
	
	public static int subset(int idx, int status) {
		if(idx == N) {
			if(status == ((1 << 26) - 1)) return 1;
			return 0;
		}
		
		return subset(idx + 1, status | alphas[idx]) + subset(idx + 1, status);
	}
}
