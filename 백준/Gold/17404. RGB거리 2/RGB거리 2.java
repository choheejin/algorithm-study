
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] arr = new int[N + 1][3];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
            arr[i][2] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N + 1][3];
        int answer = 1000_0000;
        for (int startColor = 0; startColor < 3; startColor++) {
            dp[1][startColor] = arr[1][startColor];
            dp[1][(startColor + 1) % 3] = dp[1][(startColor + 2) % 3] = 1000_0000;

            for (int i = 2; i <= N; i++) {
                dp[i][0] = Math.min(dp[i-1][1], dp[i-1][2]) + arr[i][0];
                dp[i][1] = Math.min(dp[i-1][0], dp[i-1][2]) + arr[i][1];
                dp[i][2] = Math.min(dp[i-1][0], dp[i-1][1]) + arr[i][2];
            }

            for (int color = 0; color < 3; color++) {
                if (color != startColor) {
                    answer = Math.min(answer, dp[N][color]);
                }
            }
        }

        System.out.println(answer);
    }
}
