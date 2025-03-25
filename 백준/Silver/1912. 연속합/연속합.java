import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];
        int maxV = nums[0];
        dp[0] = nums[0];
        for(int i = 1; i < N; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            maxV = Math.max(maxV, dp[i]);
        }

        System.out.println(maxV);
    }
}
