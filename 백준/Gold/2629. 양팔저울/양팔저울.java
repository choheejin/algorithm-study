import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int weightNum = Integer.parseInt(bf.readLine());
		int[] weights = new int[weightNum];
		int maxWeight = 40_001;
		StringTokenizer st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < weightNum; i++) {
			weights[i] = Integer.parseInt(st.nextToken());
		}
		
		int beadNum = Integer.parseInt(bf.readLine());
		int[] beads = new int[beadNum];
		
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i <beadNum; i++) {
			beads[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] dp = new int[maxWeight];
		dp[0] = 1;

		
		for (int i = 0; i < weightNum; i++) {
		    int[] next = dp.clone();
		    for (int j = 0; j < maxWeight; j++) { // 작은 수부터 가도 되고, 큰 수부터 가도 됨 (clone 쓰니까)
		        if (dp[j] != 0) {
		            int sum = j + weights[i];
		            int abs = Math.abs(j - weights[i]);

		            if (sum < maxWeight) next[sum] = 1;
		            if (abs < maxWeight) next[abs] = 1;
		        }
		    }
		    dp = next;
		}
		
		for(int n = 0; n < beadNum; n++) {
			if(dp[beads[n]] != 0) {
				sb.append("Y ");
			} else {
				sb.append("N ");
			}
		}
		
		System.out.println(sb);
	}
}
