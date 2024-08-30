import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {

	// 1. 코어를 놓을 수도 있고 안 놓을 수도 있다.
	//    => 각 코어에 대한 경우의 수: X, 상, 하, 좌, 우
	// 2. 각 코어의 경우의 수에 대해, 가능/불가능 여부를 판단한다.
	//	  => dx, dy를 활용해서 해당 방향에 1이 존재하면 return false;
	
	static int N;
	
	static char[][] map;
	
	static int[][] cores;
	static int coreIdx, minValue, coreMax;
	
	static int[] dx = {0, -1, 0, 1, 0}, dy = {0, 0, 1, 0, -1};
	
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			map = new char[N][N];
			cores = new int [12][2];
			coreIdx = 0;
			for(int i =0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = st.nextToken().charAt(0);
					if(i != 0 && i != N -1 && j != 0 && j != N - 1) {
						if(map[i][j] == '1') {
							cores[coreIdx][0] = i;
							cores[coreIdx++][1] = j;
						}
					}
				}
			}
			
			coreMax = Integer.MIN_VALUE;
			minValue = Integer.MAX_VALUE;

			perm(0, 0, 0);
			
			sb.append("#").append(tc).append(" ").append(minValue).append("\n");
		}
		System.out.println(sb);
	}
	
	static int getDistance(int idx, int dir) {		
		int cnt = 0;
		int x = cores[idx][0];
		int y = cores[idx][1];
		
		while(true) {
			x += dx[dir];
			y += dy[dir]; 
			
			// 경계를 벗어난다면
			if(x < 0 || y < 0 || x >= N || y >= N) return cnt;
			
			// 전선을 놓을 수 없다면
			if(map[x][y] != '0') {
				return 0;
			}
			
			cnt++;
		}
		
	}
	
	static void setLines(int idx, int dir, int distance, char line) {
		
		int x = cores[idx][0];
		int y = cores[idx][1];

		for(int i=0; i<distance; i++) {
			x += dx[dir];
			y += dy[dir]; 
			
			map[x][y] = line;
			
		}
	}
	
	
	static void perm(int cnt, int coreCnt, int min) {
		if(cnt == coreIdx) {
			if(coreCnt == coreMax) {
				minValue = Math.min(min, minValue);
			}
			
			if(coreCnt > coreMax) {
				coreMax = coreCnt;
				minValue = min;
			}
			return;
		}
		
		for(int i = 0; i < 5; i++) {
			if(i == 0) {
				perm(cnt + 1, coreCnt, min);
				continue;
			}
			
			int distance = getDistance(cnt, i);
			
			if(distance == 0) continue;
						
			
			// 해당 위치에서 해당 방향으로 distacne 만큼
			setLines(cnt, i, distance, '1');
			
			perm(cnt + 1, coreCnt + 1, min + distance);

			// 해제해준다.
			setLines(cnt, i, distance, '0');
			
		}
	}
}
