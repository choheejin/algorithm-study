import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Solution {

	static int N;
	static int[][] map;
	
	static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
	
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(bf.readLine());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				char[] ch = bf.readLine().toCharArray();
				
				for(int j = 0; j < N; j++) {
					map[i][j] = ch[j] - '0';
				}
			
			
			}
			int result = getMinTime(0, 0, N-1, N-1);
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		
		System.out.println(sb);
	}

	
	static int getMinTime(int sr, int sc, int er, int ec) {
		final int INF = Integer.MAX_VALUE;
		
		boolean[][] visited = new boolean[N][N];
		int[][] minTime = new int[N][N]; // 최악의 경우여도 9만임
		
		PriorityQueue<int[]> pQueue = new PriorityQueue<>((a, b) -> Integer.compare(a[2], b[2]));
		
		for(int i = 0 ; i < N; i++) {
			Arrays.fill(minTime[i], INF);
		}
		
		minTime[sr][sc] = 0;
		pQueue.offer(new int[] {sr, sc, minTime[sr][sc]});
		
		while(!pQueue.isEmpty()) {
			// step1.
			int[] stopOver = pQueue.poll();
			
			int r = stopOver[0];
			int c = stopOver[1];
			int time = stopOver[2];
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			if(r == er && c == ec) return time;
			
			// step2.
			for(int i = 0; i < 4; i++) {
				int nr = dx[i] + r, nc = dy[i] + c;
				
				if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
				if(visited[nr][nc]) continue;
				
				if(minTime[nr][nc] > time + map[nr][nc]) {
					minTime[nr][nc] = time + map[nr][nc];
					pQueue.offer(new int[] {nr, nc, minTime[nr][nc]});
				}
			}
			
		}
		
		return -1;
	}
	
}