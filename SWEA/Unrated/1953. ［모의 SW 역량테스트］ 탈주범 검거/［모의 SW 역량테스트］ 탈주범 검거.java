import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	static int N, M, R, C, L;
	static int[][] map;
	static boolean[][] visited;
	static int[] dr = { 1, -1, 0, 0 }, dc = { 0, 0, 1, -1 };

	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());

			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());

			map = new int[N][M];
			visited = new boolean[N][M];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = bfs(R, C);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

	// BFS로 하면 되겠다. (레벨별 탐색)

	static int bfs(int sr, int sc) {
		Queue<int[]> q = new ArrayDeque<>();
		
		
		visited[sr][sc] = true;
		q.offer(new int[] { sr, sc });

		int depth = 0, cnt = 0;
		while (!q.isEmpty()) {
			int size = q.size();
			
			if(depth == L) return cnt;

			while (size-- > 0) {
				int[] curr = q.poll();
				int nr = curr[0], nc = curr[1];
				cnt++;

				switch (map[nr][nc]) {
					case 1:
						// 상 - 하를 포함하고 있어야 한다.
						if(check(nr - 1, nc) && (map[nr - 1][nc] == 1 || map[nr - 1][nc] == 2 || map[nr - 1][nc] == 5 || map[nr - 1][nc] == 6)) {
							visited[nr - 1][nc] = true;
							q.offer(new int[] {nr - 1, nc});
						}
						// 하 - 상을 포함하고 있어야 한다.
						if(check(nr + 1, nc) && (map[nr + 1][nc] == 1 || map[nr + 1][nc] == 2 || map[nr + 1][nc] == 4 || map[nr + 1][nc] == 7)) {
							visited[nr + 1][nc] = true;
							q.offer(new int[] {nr + 1, nc});
						}
						// 좌 - 우를 포함하고 있어야 한다.
						if(check(nr, nc - 1) && (map[nr][nc - 1] == 1 || map[nr][nc - 1] == 3 || map[nr][nc - 1] == 4 || map[nr][nc - 1] == 5)) {
							visited[nr][nc - 1] = true;
							q.offer(new int[] {nr, nc - 1});
						}
						// 우
						if(check(nr, nc + 1) && (map[nr][nc + 1] == 1 || map[nr][nc + 1] == 3 || map[nr][nc + 1] == 6 || map[nr][nc + 1] == 7)) {
							visited[nr][nc + 1] = true;
							q.offer(new int[] {nr, nc + 1});
						}
						break;
					case 2:
						// 상
						if(check(nr - 1, nc) && (map[nr - 1][nc] == 1 || map[nr - 1][nc] == 2 || map[nr - 1][nc] == 5 || map[nr - 1][nc] == 6)) {
							visited[nr - 1][nc] = true;
							q.offer(new int[] {nr - 1, nc});
						}						
						// 하
						if(check(nr + 1, nc) && (map[nr + 1][nc] == 1 || map[nr + 1][nc] == 2 || map[nr + 1][nc] == 4 || map[nr + 1][nc] == 7)) {
							visited[nr + 1][nc] = true;
							q.offer(new int[] {nr + 1, nc});
						}
						break;
					case 3:
						// 좌
						if(check(nr, nc - 1) && (map[nr][nc - 1] == 1 || map[nr][nc - 1] == 3 || map[nr][nc - 1] == 4 || map[nr][nc - 1] == 5)) {
							visited[nr][nc - 1] = true;
							q.offer(new int[] {nr, nc - 1});
						}
						// 우
						if(check(nr, nc + 1) && (map[nr][nc + 1] == 1 || map[nr][nc + 1] == 3 || map[nr][nc + 1] == 6 || map[nr][nc + 1] == 7)) {
							visited[nr][nc + 1] = true;
							q.offer(new int[] {nr, nc + 1});
						}
						break;
					case 4:
						// 상
						if(check(nr - 1, nc) && (map[nr - 1][nc] == 1 || map[nr - 1][nc] == 2 || map[nr - 1][nc] == 5 || map[nr - 1][nc] == 6)) {
							visited[nr - 1][nc] = true;
							q.offer(new int[] {nr - 1, nc});
						}
						// 우
						if(check(nr, nc + 1) && (map[nr][nc + 1] == 1 || map[nr][nc + 1] == 3 || map[nr][nc + 1] == 6 || map[nr][nc + 1] == 7)) {
							visited[nr][nc + 1] = true;
							q.offer(new int[] {nr, nc + 1});
						}
						break;
					case 5:
						// 하
						if(check(nr + 1, nc) && (map[nr + 1][nc] == 1 || map[nr + 1][nc] == 2 || map[nr + 1][nc] == 4 || map[nr + 1][nc] == 7)) {
							visited[nr + 1][nc] = true;
							q.offer(new int[] {nr + 1, nc});
						}
						// 우
						if(check(nr, nc + 1) && (map[nr][nc + 1] == 1 || map[nr][nc + 1] == 3 || map[nr][nc + 1] == 6 || map[nr][nc + 1] == 7)) {
							visited[nr][nc + 1] = true;
							q.offer(new int[] {nr, nc + 1});
						}
						break;
					case 6:
						// 하
						if(check(nr + 1, nc) && (map[nr + 1][nc] == 1 || map[nr + 1][nc] == 2 || map[nr + 1][nc] == 4 || map[nr + 1][nc] == 7)) {
							visited[nr + 1][nc] = true;
							q.offer(new int[] {nr + 1, nc});
						}
						// 좌
						if(check(nr, nc - 1) && (map[nr][nc - 1] == 1 || map[nr][nc - 1] == 3 || map[nr][nc - 1] == 4 || map[nr][nc - 1] == 5)) {
							visited[nr][nc - 1] = true;
							q.offer(new int[] {nr, nc - 1});
						}
						break;
					case 7:
						// 상
						if(check(nr - 1, nc) && (map[nr - 1][nc] == 1 || map[nr - 1][nc] == 2 || map[nr - 1][nc] == 5 || map[nr - 1][nc] == 6)) {
							visited[nr - 1][nc] = true;
							q.offer(new int[] {nr - 1, nc});
						}
						// 좌
						if(check(nr, nc - 1) && (map[nr][nc - 1] == 1 || map[nr][nc - 1] == 3 || map[nr][nc - 1] == 4 || map[nr][nc - 1] == 5)) {
							visited[nr][nc - 1] = true;
							q.offer(new int[] {nr, nc - 1});
						}
						break;
				}
			}
			depth++;
		}
		
		return cnt;
	}
	
	static boolean check(int nr, int nc) {
		return !(nr < 0 || nc < 0 || nr >= N || nc >= M) && !visited[nr][nc];
	}
}