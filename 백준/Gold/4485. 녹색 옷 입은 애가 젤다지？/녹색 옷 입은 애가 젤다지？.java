import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int N;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0} , dy = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        while(true) {
            N = Integer.parseInt(bf.readLine());
            if(N == 0) break;
            map = new int[N][N];

            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int result = getMinDistance();

            sb.append("Problem ").append(tc).append(": ").append(result).append("\n");
            tc++;
        }

        System.out.println(sb);
    }

    static int getMinDistance() {
        int[][] minDistance = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));

        // 최소비용 배열 초기화
        for(int i = 0; i < N; i++) {
            Arrays.fill(minDistance[i], Integer.MAX_VALUE);
        }

        // 스타트 지점 선택
        pq.offer(new int[] {0, 0, map[0][0]});
        minDistance[0][0] = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();

            if(visited[curr[0]][curr[1]]) continue;

            visited[curr[0]][curr[1]] = true;

            if(curr[0] == N - 1 && curr[1] == N - 1) return curr[2];

            // 미방문 정점 중에서 현재 정점과 가장 최단 거리
            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + curr[0], ny = dy[i] + curr[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if(visited[nx][ny]) continue;

                if(minDistance[nx][ny] > map[nx][ny] + curr[2]) {
                    minDistance[nx][ny] = map[nx][ny] + curr[2];
                    pq.offer(new int[] {nx, ny, map[nx][ny] + curr[2]});
                }
            }
        }

        return -1;
    }


}