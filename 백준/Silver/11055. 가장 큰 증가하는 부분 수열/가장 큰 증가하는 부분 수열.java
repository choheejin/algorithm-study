import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int max = 0;
        int[] arr = new int[N+1];
        int[] dp = new int[N+1];

        System.arraycopy(nums, 0, arr, 1, N);
        System.arraycopy(nums, 0, dp, 1, N);

        for(int i = 1; i <= N; i++) {
            for(int j=1; j <i; j++) {
                if(arr[i] > arr[j] && dp[i] < dp[j] + arr[i]) {
                    dp[i] = dp[j] + arr[i];
                }
            }
            max = Math.max(dp[i], max);
        }

        System.out.println(max);
    }
}
