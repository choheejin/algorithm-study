import java.awt.Point;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int T, startX, startY;
	static int[] dx = { -1, 1, 0 };
	static int[] dy = { 0, 0, -1 };
	static char[][] graph;
	static Queue<Point> queue;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		for (int tc = 1; tc <= 10; tc++) {
			T = Integer.parseInt(bf.readLine());
			graph = new char[100][100];
			queue = new ArrayDeque<>();
			// 그래프 초기화
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for (int j = 0; j < 100; j++) {
					graph[i][j] = st.nextToken().charAt(0);
					if (graph[i][j] == '2') {
						startX = j;
						startY = i;
					}
				}
			}
			queue.offer(new Point(startX, startY));
			while (!queue.isEmpty()) {
				Point p = queue.poll();
				for (int i = 0; i < 3; i++) {
					int nx = p.x + dx[i];
					int ny = p.y + dy[i];
					if (nx < 0 || ny < 0 || ny >= 100 || nx >= 100)
						continue;
					if (graph[ny][nx] == '1') {
						graph[ny][nx] = '2';
						queue.offer(new Point(nx, ny));
						break;
					}
				}
			}
			int result = 0;
			for (int i = 0; i < 100; i++) {
				if (graph[0][i] == '2') {
					result = i;
					break;
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}

}