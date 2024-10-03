import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 메모리: 40,264 KB, 시간: 1,794 ms, 코드길이: 3,527 Bytes
 */
public class Solution {
	static int[] dx = { -1, 1, 0, 0 }, dy = { 0, 0, -1, 1};
	static int[][] blocks = {{}, {3, 1, 2, 0}, {0, 3, 2, 1}, {0, 2, 1, 3}, {2, 1, 0, 3}, { 0, 1, 2, 3 } };
	static List<int[]> wormholes;

	static int N;
	static int[][] map;

	static int startPointX, startPointY;
	static int maxValue;

	public static void main(String[] args) throws NumberFormatException, IOException {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine().trim());

			map = new int[N][N];
			wormholes = new ArrayList<>(5);
			maxValue = Integer.MIN_VALUE;
			
			for (int i = 0; i < 5; i++) {
				wormholes.add(new int[] { -1, -1, -1, -1 });
			}

			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine().trim());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					// 웜홀 지정
					if (map[i][j] >= 6) {
						int idx = map[i][j] - 6;
						// 이미 짝 웜홀이 들어가 있는 상태
						if (wormholes.get(idx)[0] != -1) {
							wormholes.get(idx)[2] = i;
							wormholes.get(idx)[3] = j;
						} else {
							wormholes.get(idx)[0] = i;
							wormholes.get(idx)[1] = j;
						}
					}
				}
			}

			// 완전탐색
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(map[i][j] != 0) continue;
					
					startPointX = i;
					startPointY = j;
					
					for(int d = 0; d < 4; d++) {
						maxValue = Math.max(maxValue, startGame(d));
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(maxValue).append("\n");
		}
		System.out.println(sb);
	}


	public static int startGame(int startDir) {
		int d = startDir;
		
		int x = startPointX, y = startPointY;
		int cnt = 0;
		
		while(true) {
			int px = x, py = y;
			x += dx[d];
			y += dy[d];
			
//			System.out.println(x +"," + y);
			
			// 벽인가?
			if(x >= N || y >= N || x < 0 || y < 0) {
				if(x < 0) d = 1;
				
				// 아래로 빠져 나감 
				if(x >= N) d= 0;
				
				// 왼쪽으로 빠져 나감 
				if(y < 0) d = 3;
				
				if(y >= N)d = 2;

				cnt++;
				continue;
			}
			
			if(map[x][y] == -1 || (x == startPointX && y == startPointY)) return cnt;

			// 블록인가?
			else if(1 <= map[x][y] && map[x][y] <= 5) {
				int idx = 1;
				int calX = px - x, calY = py - y;
				if(calX == 0 && calY < 0) idx = 2;
				if(calX == 0 && calY > 0) idx = 3;
				if(calY == 0 && calX < 0) idx = 0;

				d = blocks[map[x][y]][idx];
				cnt++;
				continue;
			}
			
			// 웜홀인가? 
			else if(map[x][y] >= 6) {
				int idx = map[x][y] - 6;
				if(wormholes.get(idx)[0] == x && wormholes.get(idx)[1] == y) {
					x = wormholes.get(idx)[2];
					y = wormholes.get(idx)[3];
				}
				
				else {
					x = wormholes.get(idx)[0];
					y = wormholes.get(idx)[1];
				}
				continue;
			}
		}
		
	}
	
}