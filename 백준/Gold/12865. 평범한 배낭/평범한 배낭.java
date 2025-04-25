import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] weight = new int[N];
        int[] values = new int[N];

        int maxValue = 0;
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());

            weight[i] = Integer.parseInt(st.nextToken());
            values[i] = Integer.parseInt(st.nextToken());
            maxValue += values[i];
        }

        int INF = 100_000_000;
        int[] dp = new int[maxValue + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        for(int i = 0; i < N; i++) {
            for(int j = maxValue; j >= values[i]; j--) {
                dp[j] = Math.min(dp[j], dp[j - values[i]] + weight[i]);
            }
        }

        for(int i = maxValue; i >= 0; i--) {
            if(dp[i] <= K) {
                System.out.println(i);
                return;
            }
        }
    }
}
