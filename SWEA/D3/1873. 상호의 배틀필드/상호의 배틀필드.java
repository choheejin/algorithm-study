import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	// 입력값
	static int H, W, N;
	static char map[][], cmds[];
	
	// 탱크 위치
	static int tankX, tankY, currDir;
	
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	static char[] tankShape = {'^', 'v', '<', '>'};
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][];
			boolean isFound = false;
			for (int h = 0; h < H; h++) {
				map[h] = bf.readLine().toCharArray();
				if(!isFound) {
					for (int w = 0; w < W; w++) {
						if(map[h][w] == '^' || map[h][w] == 'v' || map[h][w] == '<' || map[h][w] == '>') {
							tankX = h;
							tankY = w;
							if(map[h][w] == '^') currDir = 0;
							else if(map[h][w] == 'v') currDir = 1;
							else if(map[h][w] == '<') currDir = 2;
							else if(map[h][w] == '>') currDir = 3;
							isFound = true;
							break;
						}
					}
				}
			}
			
			N = Integer.parseInt(bf.readLine());
			cmds = bf.readLine().toCharArray();
			for(char cmd: cmds) {
//				System.out.println(cmd);
				switch (cmd) {
				case 'U':
					moveTank(0);
					break;
				case 'D':
					moveTank(1);
					break;
				case 'L':
					moveTank(2);
					break;
				case 'R':
					moveTank(3);
					break;
				case 'S':
//					System.out.printf("%d %d\n",tankX, tankY);
					shoot(tankX + dx[currDir], tankY + dy[currDir], currDir);
					break;
				}
//				System.out.println(printMap());
			}
			
			sb.append("#").append(tc).append(" ");
			
			for(int h = 0; h < H; h++) {
				for(int w = 0; w < W; w++) {
					sb.append(map[h][w]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}
	
	// 1. 이동
	// 현재 전차의 방향을 변경해준다.
	static void moveTank(int idx) {
//		System.out.printf("%d %d %d\n", tankX, tankY, idx);
		map[tankX][tankY] = tankShape[idx];
		int moveX = tankX + dx[idx], moveY = tankY + dy[idx];
		currDir = idx;
		if(moveX < 0 || moveY < 0 || moveX >= H || moveY >= W) return;

		if(isFlat(moveX, moveY)) {
			makeFlat(tankX, tankY);
			tankX = moveX;
			tankY = moveY;
			map[tankX][tankY] = tankShape[idx];
		}
	}
	// 현재 전차가 바라보는 방향의 바로 상/하/좌/우에 평지가 존재하는지 확인한다.
	static boolean isFlat(int x, int y) {
		return map[x][y] == '.';
	}
	
	static void makeFlat(int x, int y) {
		map[x][y] = '.';
	}
	
	// 2. 포탄 발사
	// 전차의 방향의 상/하/좌/우 를 직진방향으로 탐색을 진행
	// 밖으로 나갈때까지 계속해서 진행해야 함.
	static void shoot(int x, int y, int idx) {
		// 포탄이 맵 밖으로 벗어났다.
		if(x < 0 || y < 0 || x >= H || y >= W) return;
//		System.out.printf("%d %d %d\n", x, y, idx);
		if(!isFlat(x, y) && getObstacleType(x, y) == 2) {
			return;
		}
		
		else if(!isFlat(x, y) && getObstacleType(x, y) == 1) {
			// 평지로 전환
			makeFlat(x, y);
			return;
		}
		
		shoot(x + dx[idx], y + dy[idx], idx);
	}
	
	// 방해물 - 강철(2): return;
	// 방해물 - 벽돌(1): 평지로 전환;
	static int getObstacleType(int x, int y) {
		if(map[x][y] == '*') {
			return 1;
		}
		else if(map[x][y] == '#') {
			return 2;
		} 
		return 0;
	}

}