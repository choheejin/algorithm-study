import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
// 풀고보니 강사님과 풀이가 똑같네요.
//매모리 : 40012	 시간 : 360
public class Main {
    static int R, C;
    static char[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 0, 1};// 위 중앙 아래
    static int[] dy = {1, 1, 1};
    static int max = 0;

    //오른쪽, 오른쪽 위 대각선, 오른쪽 아래 대각선으로 연결할 수 있고
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visit = new boolean[R][C];

        //'.'는 빈 칸이고, 'x'는 건물
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            // 파이프가 끝까지 연결된 경우
            if (makePipe(i, 0)) {
                max++;
            }
        }
        System.out.println(max);
    }

    private static boolean makePipe(int x, int y) {
        visit[x][y] = true;

        for (int i = 0; i < 3; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
            if (map[nx][ny] == 'x') continue;
            if (visit[nx][ny]) continue;

            if (ny == C - 1) { // 파이프가 매트릭스의 마지막 열까지 도달했는지 확인.
                return true; // 도달하면 true 반환.
            }
            if (makePipe(nx, ny)) return true; // 재귀적으로 다음 위치에서 파이프를 설치 가능한지 확인.
        }
        return false;
    }
}