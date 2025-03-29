import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		dp = new int[N];
		int end = 0;
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
//			System.out.println(Arrays.toString(dp));
			
			int idx = find(end, num);
			
			dp[idx] = num;
			if(idx == end) end++;
		}
//		System.out.println(Arrays.toString(dp));
		System.out.println(end);
	}
	
	public static int find(int end, int target) {
		int start = 0;
		
		while(start < end) {
			int mid = (start + end) / 2;
						
			if(target > dp[mid]) {
				start = mid + 1;
				continue;
			}
			
			end = mid;
		}
		
		return end;
	}
}
