import java.util.*;
import java.io.*;

public class Main {
    static int N = 5;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static char[][] map = new char[N][N];
    static boolean[][] visited = new boolean[N][N];
    static int result = 0;
    static ArrayList<int[]> selected = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i++) {
            String cmd = bf.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = cmd.charAt(j);
            }
        }

        select(0, 0);
        System.out.println(result);
    }

    static void select(int start, int cnt) {
        if (cnt == 7) {
            if (isConnected()) {
                int sCnt = 0;
                for (int[] pos : selected) {
                    if (map[pos[0]][pos[1]] == 'S') sCnt++;
                }
                if (sCnt >= 4) result++;
            }
            return;
        }

        for (int i = start; i < N * N; i++) {
            int x = i / N;
            int y = i % N;
            selected.add(new int[] {x, y});
            select(i + 1, cnt + 1);
            selected.remove(selected.size() - 1);
        }
    }

    static boolean isConnected() {
        boolean[][] tempVisited = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(selected.get(0));
        tempVisited[selected.get(0)[0]][selected.get(0)[1]] = true;

        int visitedCount = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < N && ny < N && !tempVisited[nx][ny]) {
                    for (int[] pos : selected) {
                        if (pos[0] == nx && pos[1] == ny) {
                            queue.add(new int[] {nx, ny});
                            tempVisited[nx][ny] = true;
                            visitedCount++;
                            break;
                        }
                    }
                }
            }
        }

        return visitedCount == 7;
    }
}
