import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    // DFS 문제
    // 항상 탐색의 시작은 좌측 아래로
    // 방문 배열 필요 - 이미 방문한 숫자가 있으면 안되므로
    // 방향은 직진이거나 꺽이거나
    // => 구현 방법) 현재 방향을 저장해줌 -> 현재 방향에 따라, 좌표의 -/+ 를 변경해준다.

    // 탐색 시작 구간은 (행: 0~N-3) (열: 1~N-2)

    static int[] dx = {1, 1, -1, -1}, dy = {-1, 1, 1, -1}; // 현재 방향 저장
    static int N, result;
    static boolean[] visited;
    static int[][] adjMatrix;

    public static void main(String[] args) throws  Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());

        for (int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(bf.readLine());
            adjMatrix = new int[N][N];
            for(int i = 0; i < N; i ++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int j = 0; j < N; j++) {
                    adjMatrix[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            visited = new boolean[101];
            result = -1;
            for(int r = 0; r <= N - 3; r ++) {
                for (int c = 1; c <= N - 2; c++) {
                    dfs(r, c, 0, 1, r + 1, c + 1);
                }
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void dfs(int x, int y, int idx, int depth, int endR, int endC) {
        if(x < 0 || y < 0 || x >= N || y >= N) return; // 경계 밖
        if(visited[adjMatrix[x][y]]) return; // 이미 사용된 번호
        if(idx == 4) return; // 방향 전환 다 함

        if(x == endR && y == endC) { // 종착지점까지 도달 완료
            result = Math.max(result, depth);
            return;
        }

        visited[adjMatrix[x][y]] = true;
        int nx = dx[idx] + x;
        int ny = dy[idx] + y;
        // 직진
        dfs(nx, ny, idx, depth + 1 ,endR, endC);
        // 꺾음
        dfs(nx, ny, idx + 1, depth + 1, endR, endC);

        visited[adjMatrix[x][y]] = false;
    }
}