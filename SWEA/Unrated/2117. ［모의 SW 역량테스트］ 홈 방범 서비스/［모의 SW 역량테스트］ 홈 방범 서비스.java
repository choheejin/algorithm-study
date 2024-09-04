import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	
	// 시간복잡도: 400 * 400 * 20: nullnull하다
	// 1. K를 N부터 1까지로 줄이면서 최대 집의 수를 구한다.
	// 2. 방범서비스를 받을 수 있는 영역은
	//	  => BFS로 레벨별 탐색: K레벨까지
	
	static int N, M;
	static int[][] map;
	
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws Exception {
		 BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		 StringBuilder sb = new StringBuilder();
		 
		 int T = Integer.parseInt(bf.readLine());
		 
		 for(int tc = 1; tc <= T; tc++) {
			 StringTokenizer st = new StringTokenizer(bf.readLine());
			 N = Integer.parseInt(st.nextToken());
			 M = Integer.parseInt(st.nextToken());
			 
			 map = new int[N][N];
			 
			 for(int i = 0; i < N; i++) {
				 st = new StringTokenizer(bf.readLine());
				 for(int j = 0; j < N; j++) {
					 map[i][j] = Integer.parseInt(st.nextToken());
				 }
			 }
			 
			 int maxHome = Integer.MIN_VALUE;
			 // step 1
			 for(int i = 0; i < N; i++) {
				 for(int j = 0; j < N; j++) {
					 
					 for(int k = N+1; k >= 1; k--) {
						 int home = bfs(i, j, k);
						 int result = (home * M) - (k * k + (k - 1) * (k - 1));
						 
						 if(result >= 0) maxHome = Math.max(maxHome, home);
					 }
				 
				 }
			 }
			 sb.append("#").append(tc).append(" ").append(maxHome).append("\n");
		 }
		 
		 System.out.println(sb);
	}
	
	static int bfs(int sr, int sc, int k) {
		boolean[][] visited = new boolean[N][N];
		Queue<int[]> q = new ArrayDeque<>();
		
		visited[sr][sc] = true;
		q.offer(new int[] {sr, sc});
		
		int depth = 1;
		int home = map[sr][sc] == 1 ? 1 : 0;
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			if(depth == k) break;
			
			while(size-- > 0) {
				int[] curr = q.poll();
				
				for(int i = 0; i < 4; i++) {
					int nx = curr[0] + dx[i], ny = curr[1] + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
					if(visited[nx][ny]) continue;
					
					// 집이 속해있다.
					if(map[nx][ny] == 1) home++;
					
					q.offer(new int[] {nx, ny});
					visited[nx][ny] = true;
				}
				
			}
			depth++;
		}
		
		return home;
	}
}