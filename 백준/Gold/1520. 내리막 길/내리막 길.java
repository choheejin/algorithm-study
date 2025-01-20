import java.io.*;
import java.util.*;

public class Main {
    static int n, m;
    static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    static int[][] map;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        dp = new int[n][m]; // 각 위치에서 도달 가능한 경로 수를 저장
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                dp[i][j] = -1; // 아직 방문하지 않은 경우를 -1로 초기화
            }
        }

        System.out.println(dfs(0, 0));
    }

    public static int dfs(int x, int y) {
        if (x == n - 1 && y == m - 1) return 1; // 도착지점에 도달한 경우
        if (dp[x][y] != -1) return dp[x][y]; // 이미 계산된 값이 있으면 반환

        dp[x][y] = 0; // 현재 위치에서 도달 가능한 경로 수 초기화
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 경계 체크 및 내리막길 조건 확인
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && map[nx][ny] < map[x][y]) {
                dp[x][y] += dfs(nx, ny); // 다음 위치에서 가능한 경로 수를 더함
            }
        }
        return dp[x][y];
    }
}