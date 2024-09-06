import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	// BFS(레벨별 탐색)로 푼다.
	// dx/dy의 인덱스가 3 이상이면, K의 횟수를 줄여줘야 한다.
	// 만약, K번 이상 사용하게 되는 경우,,,? 어케 하지;
	
	static int[] dx = {
			0, 
			-1, 1, 0, 0, 
			2, 1, -1, -2, 
			2, 1, -1, -2};
	
	static int[] dy = {
			0, 
			0, 0, -1, 1, 
			1, 2, 2, 1, 
			-1, -2, -2, -1
			};
	
	static int W, H, K;
	
	static int[][] map;
	static boolean[][][] visited;
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		K = Integer.parseInt(bf.readLine());
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		map = new int[H][W];
		visited = new boolean[H][W][K+1];
		
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		bfs();
	}

	static void bfs() {
		// {x, y, k 횟수}
		Queue<int[]> q = new ArrayDeque<>();
		visited[0][0][0] = true;
		q.offer(new int[] {0, 0, 0});
		
		int depth = 0;
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				int[] curr = q.poll();
				int x = curr[0], y = curr[1], k = curr[2];
				
				// 어떤 경로든 먼저 도착하면 최단거리
				if(x == H - 1 && y == W - 1) {
					System.out.println(depth);
					return;
				}
				
				for(int i = 1; i <= 12; i++) {
					int nx = x + dx[i], ny = y + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= H || ny >= W) continue; // 경계 밖
					
					// 나이트의 이동경로라면
					// 그리고 이미 k의 횟수를 다 사용한 상태면? 탐색 안 넣어주면 됨
					if(i > 4 && k == K) continue;					
					if(map[nx][ny] == 1) continue; // 장애물
					if(visited[nx][ny][i > 4 ? k + 1 : k]) continue;
					
					visited[nx][ny][i > 4 ? k + 1 : k] = true;
					q.offer(new int[] {nx, ny, i > 4 ? k + 1 : k});
				}
			}
			depth++;
		}
		
		System.out.println(-1);
	}
	
}