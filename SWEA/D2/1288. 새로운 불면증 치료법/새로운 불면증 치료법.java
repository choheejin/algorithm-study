import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(bf.readLine());
			int result = 0;
			int cnt = 1;
//			// 0~9까지의 모든 수가 나올 때까지 돌아야 한다.
			while((1 << 10) - 1 != result) {
				int tmp = N * cnt;

				while(tmp > 0) {
					int digit = tmp % 10;
					
//					System.out.print(digit);
					
					result = ( 1 << digit ) | result;
					
//					System.out.println(Integer.toBinaryString(result));
					
					tmp /= 10;
				}
				
				cnt++;
//				System.out.println("=====" + cnt +"=====");
			}
//			System.out.println((1 << 10) - 1);
//			System.out.println(Integer.toBinaryString((1 << 10) - 1));
			int num = N * (cnt - 1);
			sb.append("#").append(tc).append(" ").append(num).append("\n");
		}
		System.out.println(sb);
	}
}