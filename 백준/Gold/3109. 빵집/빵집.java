import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N, M, maxValue, cnt=0;
	static boolean[][] visited;
	static char[][] graph;
	static int[] dx = {-1, 0, 1}, dy = {1, 1, 1}; 
	public static void main(String[] args) throws Exception{
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		graph = new char[N][M];
		for(int i = 0; i < N; i++) {
			graph[i] = bf.readLine().toCharArray();
		}
		
		visited = new boolean[N][M];
		for(int i = 0; i < N; i++) {
			// 출발지에서 도착지에 도착할 수 있다
			visited[i][0] = true;
			if(makeRoute(i, 0)) {
				// 현재 도달한 개수
				cnt++;
			}
		}
		System.out.println(cnt);
		// 한점의 makeRoute()를 하면 3개의 경로 확인
	}

	/**
	 * 
	 * @param x
	 * @param y
	 * @return (x,y) 좌표 기준으로 파이프설치를 시도했을 때, 성공여부 반환
	 */
	static boolean makeRoute(int x, int y) {
//		if(y == M-1) return true;
		
		for(int i = 0; i < 3; i++) {
			int nx = dx[i] + x;
			int ny = dy[i] + y;
			
			// 더이상 갈 곳이 없음
			if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue;
			if(visited[nx][ny]) continue;
			if(graph[nx][ny] == 'x') continue;

			if (ny == M - 1) { // 파이프가 매트릭스의 마지막 열까지 도달했는지 확인.
                return true; // 도달하면 true 반환.
            }
			
			visited[nx][ny] = true;
			if(makeRoute(nx, ny)) return true;
			
		}
		
		return false;
	}

}