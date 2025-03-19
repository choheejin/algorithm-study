import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] arr = new int[N+1];
        for(int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(bf.readLine());
        }

        int[] dp = new int[N+1];
        dp[1] = arr[1];
        for(int i = 2; i <= N; i++) {
            int tmp = dp[i-1];
            tmp = Math.max(tmp, dp[i-3 < 0 ? 0 : i-3] + arr[i] + arr[i-1]);
            tmp = Math.max(tmp, dp[i-2] + arr[i]);
            
            dp[i] = tmp;
        }

        System.out.println(dp[N]);
    }
}
