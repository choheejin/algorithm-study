import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int minValue = Integer.MAX_VALUE;
    static int[][] map, selectIdx, mapCopy;
    static boolean[][] visited;
    static int[] dx = {0, 0, 1, -1}, dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        mapCopy = new int[N][N];
        selectIdx = new int[M][2];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        combination(0, 0);
        if(minValue == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(minValue);

    }

    static int bfs() {
        int cnt = -1;
        Queue<int[]> q = new ArrayDeque<>();

        for(int i = 0; i < M; i++) {
            mapCopy[selectIdx[i][0]][selectIdx[i][1]] = 2;
            q.offer(new int[] {selectIdx[i][0], selectIdx[i][1]});
        }

        while(!q.isEmpty()) {
            int size = q.size();
            cnt++;
            while (size-- > 0) {
                int[] curr = q.poll();
                for(int i = 0; i < 4; i++) {
                    int nx = curr[0] + dx[i], ny = curr[1] + dy[i];

                    if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                    if(mapCopy[nx][ny] != 0) continue;

                    mapCopy[nx][ny] = 2;
                    q.offer(new int[]{nx, ny});
                }
            }
        }

        if(!check()) {
            return Integer.MAX_VALUE;
        } else {
            return cnt;
        }
    }

    static boolean check() {
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(mapCopy[i][j] == 0) return false;
            }
        }
        return true;
    }

    static void combination(int cnt, int start) {
        if(cnt == M) {
            arrayCopy();

            minValue = Math.min(bfs(), minValue);
            return;
        }

        for(int idx = start; idx < N * N; idx++) {
            int r = idx / N;
            int c = idx % N;

            if(map[r][c] != 2) continue;

            selectIdx[cnt][0] = r;
            selectIdx[cnt][1] = c;

            combination(cnt + 1, idx + 1);
        }
    }

    static void arrayCopy() {
        for(int i = 0; i < N; i++) {
            System.arraycopy(map[i], 0, mapCopy[i], 0, N);
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(mapCopy[i][j] == 2) mapCopy[i][j] = 0;
            }
        }
    }
}