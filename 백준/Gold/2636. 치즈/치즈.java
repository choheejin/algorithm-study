import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static boolean[][] visited;
    static int[][] adjMatrix;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        adjMatrix = new int[R][C];

        int preCheese = 0;
        int time = 0;

        for(int i = 0; i < R; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < C; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                if(adjMatrix[i][j] == 1) preCheese++;
            }
        }

        while(true) {
            visited = new boolean[R][C];
            bfs(0, 0);
            int nowCheese = getCheeseCnt();
            time++;
            if(nowCheese == 0) break;
            else preCheese = nowCheese;
        }
        System.out.println(time);
        System.out.println(preCheese);
    }

    static void bfs (int x, int y) {
        Queue<Point> q = new ArrayDeque<>();
        q.offer(new Point(x, y));
        visited[x][y] = true;
        while(!q.isEmpty()) {
            Point node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i], ny = node.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
                if (visited[nx][ny]) continue;

                visited[nx][ny] = true;
                if (adjMatrix[nx][ny] == 1) {
                    adjMatrix[nx][ny] = 0;
                } else {
                    q.offer(new Point(nx, ny));
                }
            }
        }
    }

    static int getCheeseCnt() {
        int result = 0;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(adjMatrix[i][j] == 1) result++;
            }
        }
        return result;
    }
}