import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] nums = new int[K];
		st = new StringTokenizer(bf.readLine());
		
		for(int i = 0; i < K; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		HashSet<Integer> set = new HashSet<>();
		int curr = 0;
		
		for(int i = 0; i < K; i++) {
			// 꺼내야 할 때 
			if(set.size() == N) {
				if(set.contains(nums[i])) continue;

				int maxIdx = -1;
				int target = -1;
				
				for(int num: set) {
					int next = next(nums, i, K, num);
					if(next > maxIdx) {
						maxIdx = next;
						target = num;
					}
				}
				
				set.remove(target);
				curr++;
			} 
			set.add(nums[i]);
			
		}
		
		System.out.println(curr);
	}
	

	public static int next(int[] nums, int start, int end, int num) {
//		System.out.print(start + "," + end + "," + num + " ");
		for(int i = start + 1; i < end; i++) {
			if(nums[i] == num) return i;
		}
		return Integer.MAX_VALUE;
	}
}
