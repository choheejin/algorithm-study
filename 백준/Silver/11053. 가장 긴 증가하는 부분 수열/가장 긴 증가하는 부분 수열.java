import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[] dp;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf =new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int[] nums = new int[N];
		dp = new int[N];
		
		for(int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		dp[0] = nums[0];
		int end_idx = 0;
		for(int i = 1; i < N; i++) {
			if(dp[end_idx] < nums[i]) {
				dp[++end_idx] = nums[i];
			} else {
				int idx = binary_search(end_idx, nums[i]);
				dp[idx] = nums[i];
			}
		}
		System.out.println(end_idx + 1);
	}
	
	public static int binary_search(int end, int target) {
		int start = 0;
		while (start < end) {
			int mid = (start + end) / 2;
			
			if(dp[mid] >= target) {
				end = mid;
				continue;
			}
			
			start = mid + 1;
		}
		
		return end;
	}
}
