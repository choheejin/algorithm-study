import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	static long N;
	static long result;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		long T = Long.parseLong(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Long.parseLong(bf.readLine());
		
			// 1. 제곱근을 취해준다.
			// 2-1. 제곱근에 대한 값이 정수이다
			// 		1. 그대로 result++;
			//		2. 제곱근 값으로 업데이트
			// 2-2. 제곱근에 대한 값이 정수가 아니다.
			// 		1. 제곱근 int 형변환 값 + 1
			//		2. 1의 값에 제곱 - 원래 값만큼을 result에 더해준다.
			// 3. 2가 될때까지 반복
			
			result = 0;
			while(N > 2) {
				double sqrt = Math.sqrt(N);
				long sqrtInt = (long) sqrt;
				if(sqrt == sqrtInt) {
					N = sqrtInt;
					result++;
				} else {
					long tmp = sqrtInt + 1;
					long pow = tmp * tmp;
					result += pow - N;
					N = pow;
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
}