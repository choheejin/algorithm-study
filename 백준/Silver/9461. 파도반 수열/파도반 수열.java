import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        long[] dp = new long[101];
        dp[1] = dp[2] = dp[3] = 1;
        dp[4] = dp[5] = 2;
        for(int i = 6; i <= 100; i++) {
            dp[i] = dp[i-1] + dp[i-5];
        }
        for(int t = 0; t < T; t++) {
            int idx = Integer.parseInt(bf.readLine());
            sb.append(dp[idx]).append("\n");
        }
        System.out.println(sb);
    }
}