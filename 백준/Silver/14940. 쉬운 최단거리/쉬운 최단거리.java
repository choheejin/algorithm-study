import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    // bfs
    // 노드의 개수가 1000 이하이므로 인접행렬로도 가능
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};

    static int[][] adjMatrix;
    static boolean[][] visited;

    static int sr, sc, er, ec;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjMatrix = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i ++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < M; j ++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                if(adjMatrix[i][j] == 2) {
                    sr = i;
                    sc = j;
                    adjMatrix[i][j] = 0;
                }
            }
        }

        bfs();
        print();
    }

    static void bfs() {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {sr, sc});
        visited[sr][sc] = true;

        while(!q.isEmpty()) {
            int[] curr = q.poll();
            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + curr[0], ny = dy[i] + curr[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
                if(visited[nx][ny]) continue;
                if(adjMatrix[nx][ny]==0) continue;
                adjMatrix[nx][ny] = adjMatrix[curr[0]][curr[1]] + 1;
                visited[nx][ny] = true;
                q.offer(new int[] {nx, ny});
            }
        }
    }

    static void print() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!visited[i][j] && adjMatrix[i][j] == 1) sb.append(-1).append(" ");
                else sb.append(adjMatrix[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}