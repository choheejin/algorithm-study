import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(bf.readLine());

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(bf.readLine());

            int[] coin = new int[N];
            StringTokenizer st = new StringTokenizer(bf.readLine());

            int M = Integer.parseInt(bf.readLine());
            int[] dp = new int[M + 1];

            for(int i = 0; i < N; i++) {
                coin[i] = Integer.parseInt(st.nextToken());
            }

            dp[0] = 1; // 아무것도 선택하지 않았을때

            for(int i = 0; i < N; i++) {
                for(int j = coin[i]; j <= M; j++) { // 동전 중복 사용 가능
                    dp[j] += dp[j - coin[i]];
                }
            }

            sb.append(dp[M]).append("\n");
        }
        System.out.print(sb);
    }
}
