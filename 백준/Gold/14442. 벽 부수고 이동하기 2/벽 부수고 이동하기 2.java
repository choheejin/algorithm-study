import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M, K;
    static int[][] map;
    static boolean[][][] visited;

    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M][K+1];

        for(int i = 0; i < N; i++) {
            String inputs = bf.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = inputs.charAt(j) - '0';
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {0, 0, 0});
        visited[0][0][0] = true;

        int cnt = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                int[] curr = q.poll();
                int x = curr[0], y = curr[1], crush = curr[2];

                if(x == N-1 && y == M-1) {
                    System.out.println(cnt);
                    return;
                }

                for(int i = 0; i < 4; i++) {
                    int nx = dx[i] + x, ny = dy[i] + y;

                    if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;

                    // 벽을 만남
                    if(map[nx][ny] == 1) {
                        if(crush == K) continue; // 부수는 기회를 모두 사용함
                        if(visited[nx][ny][crush + 1]) continue; // 다른 경로에서 이미 부숴서 지나간 경로라면
                        visited[nx][ny][crush + 1] = true;
                        q.offer(new int[]{nx, ny, crush + 1});
                    }
                    // 벽이 아님
                    else {
                        if(visited[nx][ny][crush]) continue;
                        visited[nx][ny][crush] = true;
                        q.offer(new int[]{nx, ny, crush});
                    }
                }

            }
            cnt++;
        }
        System.out.println(-1);
    }
}