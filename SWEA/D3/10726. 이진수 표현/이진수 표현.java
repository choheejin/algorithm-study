import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc =1; tc<= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			
			// N비트를 모두 1로 만들어준다. 
			int tmp = (1 << N) - 1;

			if((M & tmp) == tmp) {
				sb.append("#").append(tc).append(" ").append("ON\n");
			}
			else {
				sb.append("#").append(tc).append(" ").append("OFF\n");
			}
			
		}
		
		System.out.println(sb);
	}
}