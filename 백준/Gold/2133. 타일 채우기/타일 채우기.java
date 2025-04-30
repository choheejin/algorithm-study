import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bf.readLine());

        int[] dp = new int[31];

        dp[0] = 1;
        dp[2] = 3;
        dp[4] = 11;

        for(int i = 6; i <= N; i += 2) {
            dp[i] = dp[i - 2] * 3;
            for(int j = 0; j <= i - 4; j += 2) {
                dp[i] += dp[j] * 2;
            }
        }

        System.out.println(dp[N]);
    }
}
