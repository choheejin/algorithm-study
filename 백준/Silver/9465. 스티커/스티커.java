import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        for(int tc = 1; tc<= T; tc++) {
            int N = Integer.parseInt(bf.readLine());

            int[][] nums = new int[N+1][2];
            int[][] dp = new int[N+1][2];

            StringTokenizer st1 = new StringTokenizer(bf.readLine());
            StringTokenizer st2 = new StringTokenizer(bf.readLine());

            for(int i = 1; i <= N; i++) {
                nums[i][0] = Integer.parseInt(st1.nextToken());
                nums[i][1] = Integer.parseInt(st2.nextToken());
            }

            dp[0][0] = dp[0][1] = 0;
            dp[1][0] = nums[1][0]; dp[1][1] = nums[1][1];

            for(int i = 2; i <= N; i++) {
                dp[i][0] = Math.max(dp[i-1][1], dp[i-2][1]) + nums[i][0];
                dp[i][1] = Math.max(dp[i-1][0], dp[i-2][0]) + nums[i][1];
            }

            int result = Math.max(dp[N][0], dp[N][1]);
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }
}
