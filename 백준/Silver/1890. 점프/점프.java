import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    static long[][] dp;
    static int[] dx = {1, 0}, dy = {0, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];
        dp = new long[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }

            Arrays.fill(dp[i], -1);
        }

        System.out.println(dfs(0, 0));
    }


    static long dfs(int x, int y) {
        if (dp[x][y] != -1) return dp[x][y];
        if (x == N - 1 && y == N - 1) return 1;
        if (map[x][y] == 0) return 0;

        dp[x][y] = 0;


        for(int i = 0; i < 2; i++) {
            int nx = dx[i] * map[x][y] + x;
            int ny = dy[i] * map[x][y] + y;
            if (nx >= N || ny >= N) continue;

            dp[x][y] += dfs(nx, ny);
        }

        return dp[x][y];
    }
}
