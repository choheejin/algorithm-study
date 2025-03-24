import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(bf.readLine());
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int[] dp = new int[N + 1];
		
		int end = 0;
		
		for(int i = 0; i < N; i++) {
			int num = Integer.parseInt(st.nextToken());
			int idx = binarySearch(dp, end, num);
			dp[idx] = num;
			if(idx == end) end++;
			
		}
		System.out.println(end);
	}
	
	public static int binarySearch(int[] arr, int end, int target) {
		int start = 0;

		while(start < end) {
			int mid = (start + end) / 2;
			
			if(target < arr[mid]) {
				start = mid + 1;
				continue;
			}
			end = mid;
		}
		
		return start;
	}
}
