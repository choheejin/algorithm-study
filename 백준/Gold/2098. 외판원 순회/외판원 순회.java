import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 1_000_000_000;
    static int N;
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        dp = new int[1 << N][N];

        for(int i = 0 ; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < ( 1 << N); i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(tsp(1, 0));
    }

    static int tsp (int visited, int curr) {
        if(visited == (1 << N) - 1) {
            return map[curr][0] == 0 ? INF : map[curr][0];
        }

        if(dp[visited][curr] != -1) return dp[visited][curr];

        dp[visited][curr] = INF;

        for(int nxt = 0; nxt < N; nxt++) {
            if((visited & (1 << nxt)) != 0 || map[curr][nxt] == 0) continue;;

            int newVisited = visited | (1 << nxt);
            int cost = tsp(newVisited, nxt) + map[curr][nxt];

            dp[visited][curr] = Math.min(dp[visited][curr], cost);
        }

        return dp[visited][curr];
    }
}
