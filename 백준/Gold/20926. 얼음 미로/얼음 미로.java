import java.util.*;
import java.io.*;

public class Main {
    static int n, m, sx, sy;
    static char[][] board;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0}; // 4방향 이동
    static int result = -1;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine(), " ");

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        board = new char[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            String line = bf.readLine();
            for (int j = 1; j <= m; j++) {
                board[i][j] = line.charAt(j - 1);
                if (board[i][j] == 'T') {
                    sx = i;
                    sy = j; // 시작 위치 저장
                }
            }
        }

        search();
        System.out.println(result);
    }

    static void search() {
        int[][] v = new int[n + 1][m + 1]; // 거리 배열
        boolean[][] visit = new boolean[n + 1][m + 1]; // 방문 배열
        Queue<Node> q = new LinkedList<>();
        visit[sx][sy] = true;
        q.add(new Node(sx, sy));

        while (!q.isEmpty()) {
            Node cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int idx = 1;
                int tmp = 0;

                while (true) {
                    int nx = cur.x + (idx * dx[i]);
                    int ny = cur.y + (idx * dy[i]);

                    if (isOut(nx, ny)) break; // 범위 밖
                    if (board[nx][ny] == 'H' || board[nx][ny] == 'T') break; // 구멍 또는 시작 위치

                    // 출구를 발견한 경우
                    if (board[nx][ny] == 'E') {
                        if (result == -1) {
                            result = v[cur.x][cur.y] + tmp;
                        } else {
                            result = Math.min(result, v[cur.x][cur.y] + tmp);
                        }
                    }

                    // 벽에 도달한 경우
                    if (board[nx][ny] == 'R') {
                        nx -= dx[i];
                        ny -= dy[i];
                        if (!visit[nx][ny] || v[nx][ny] > v[cur.x][cur.y] + tmp) {
                            visit[nx][ny] = true;
                            v[nx][ny] = v[cur.x][cur.y] + tmp;
                            q.add(new Node(nx, ny));
                        }
                        break;
                    }

                    // 이동 중 비용 추가
                    tmp += board[nx][ny] - '0';
                    idx++;
                }
            }
        }
    }

    static boolean isOut(int x, int y) {
        return x < 1 || x > n || y < 1 || y > m;
    }
}

class Node {
    int x, y;

    Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}