import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 0; tc < T; tc ++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());			
			
			int target = y - x;
			
			int max = (int)Math.sqrt(target);
			
			if(max == Math.sqrt(target)) {
				sb.append(max * 2 - 1).append('\n');
			}
			else if(target <= max * max + max) {
				sb.append(max * 2).append('\n');
			}
			else {
				sb.append(max * 2 + 1).append('\n');
			}
			
		}
		System.out.println(sb);
	}
}