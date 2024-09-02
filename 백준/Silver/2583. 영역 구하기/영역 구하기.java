import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	// BFS
	static int M, N, K;
	static int[][] map;
	static boolean[][] visited;
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];

		// 인접행렬 완성
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(bf.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			int width = x2 - x1;
			int height = y2 - y1;

			for (int h = y1; h < y1 + height; h++) {
				for (int w = x1; w < x1 + width; w++) {
					map[h][w] = 1;
				}
			}
		}

		List<Integer> results = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				if (visited[i][j])
					continue;
				if (map[i][j] == 1)
					continue;

				int cnt = bfs(i, j);
				results.add(cnt);
			}
		}

		Collections.sort(results);

		sb.append(results.size()).append("\n");

		for (int result : results) {
			sb.append(result).append(" ");
		}

		System.out.println(sb);
	}

	static int bfs(int x, int y) {
		int cnt = 0;
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] { x, y });
		visited[x][y] = true;

		while (!q.isEmpty()) {
			int[] curr = q.poll();
			cnt++;
			for (int i = 0; i < 4; i++) {
				int nx = dx[i] + curr[0], ny = dy[i] + curr[1];

				if (nx < 0 || ny < 0 || nx >= M || ny >= N)
					continue;
				if (visited[nx][ny])
					continue;
				if (map[nx][ny] == 1)
					continue;

				q.offer(new int[] { nx, ny });
				visited[nx][ny] = true;
			}
		}
		return cnt;
	}

	static void print() {
		for (int i = 0; i < M; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
		System.out.println("====");
	}
}