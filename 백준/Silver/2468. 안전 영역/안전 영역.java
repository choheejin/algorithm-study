import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, maxHeight, minHeight = Integer.MAX_VALUE;
    static boolean[][] visited;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0,}, dy = {0, 0, 1, -1};
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                maxHeight = Math.max(maxHeight, map[i][j]);
                minHeight = Math.min(minHeight, map[i][j]);
            }
        }

        int result = 1;
        for(int i = minHeight; i < maxHeight; i++) {
            int cnt = 0;
            visited = new boolean[N][N];
            for(int x = 0; x < N; x++) {
                for(int y =0; y < N; y++) {
                    if(dfs(x, y, i)) cnt++;
                }
            }
            result = Math.max(result, cnt);
        }
        System.out.println(result);
    }

    static boolean dfs(int x, int y, int target) {
        if(x < 0 || y < 0 || x >= N || y >= N) return false;
        if(visited[x][y]) return false;

        visited[x][y] = true;
        if(map[x][y] > target) {
            dfs(x + 1, y, target);
            dfs(x - 1, y, target);
            dfs(x, y + 1, target);
            dfs(x , y - 1, target);
            return true;
        }

        return false;
    }
}