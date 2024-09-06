import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {

	// 시간복잡도: 83,890,176: 애매한.. 시간복잡도
	
	// 1. 줄일 칸을 선택한다. -> 8 * 8
	// 2. 얼만큼 줄일지 선택한다. -> 5
	// 3. 가장 높은 노드들을 찾는다. -> 8 * 8
	// 4. 가장 높은 노드의 위치에서 BFS를 돌린다 -> 최악의 경우: 8 * 8 * 8 * 8
	
	
	static int N, K;	
	static int[][] map;
	
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			
			// 줄일 칸을 선정한다.
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					
					// 줄일 길이를 선택한다.
					for(int k = 0; k <= K; k++) {
						// 음수 값이 되면 하지 않는다.
						if(map[i][j] - k < 0) continue;
						
						// 최대 길이로 갱신
						result = Math.max(result, getPathLength(map, i, j, k));
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}	
	
	static int getPathLength(int[][] mapCopy, int sr, int sc, int k) {		
		// 현재 배열에서 최대값 구하기
		int max = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				max = Math.max(mapCopy[i][j], max);
			}
		}
		
		mapCopy[sr][sc] -= k;

		
		// 최대값 자리에서 bfs 최대 깊이 구하기
		int len = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(mapCopy[i][j] == max) {
					len = Math.max(bfs(i, j, mapCopy), len);
				}
			}
		}
		mapCopy[sr][sc] += k;

		return len;
	}
	
	static int bfs(int sr, int sc, int[][] mapCopy) {		
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] {sr, sc});
		
		int cnt = 0; // 최대 길이
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(size-- > 0) {
				int[] curr = q.poll();
				int x = curr[0], y = curr[1];
				
				for(int i = 0; i < 4; i++) {
					int nx = dx[i] + x, ny = dy[i] + y;
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
				
					if(mapCopy[x][y] <= mapCopy[nx][ny]) continue;
					
					q.offer(new int[] {nx, ny});
				}
			}
			
			cnt++;
		}
		
		return cnt;
	}	
}