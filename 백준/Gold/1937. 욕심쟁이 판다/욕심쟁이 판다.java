import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] dx = {1, -1, 0, 0}, dy ={0, 0, -1, 1};
    static int[][] map, dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        dp = new int[N][N];
        map = new int[N][N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int maxV = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                maxV = Math.max(dfs(i, j), maxV);
            }
        }



        System.out.println(maxV);
    }

    static int dfs(int x, int y) {
        if(dp[x][y] != 0) return dp[x][y]; // 이미 최적이다.

        dp[x][y] = 1;
        for(int dir = 0; dir < 4; dir++) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];

            if(nx >= N || ny >= N || 0 > nx || 0 > ny) continue;
            if(map[x][y] <= map[nx][ny]) continue;

            dp[x][y] = Math.max(dp[x][y], dfs(nx, ny) + 1);
        }

        return dp[x][y];
    }
}
