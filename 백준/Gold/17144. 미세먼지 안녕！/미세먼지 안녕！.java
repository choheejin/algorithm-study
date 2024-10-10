import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, T;
	
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	static int[][] map;
	static Queue<int[]> dusts;
	
	static boolean flag;
	static int[] machineA, machineB;
	
	public static void main(String[] args) throws IOException {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		dusts = new ArrayDeque<>();
		map = new int[R][C];
		
		for(int r = 0; r < R; r++) {
			String[] inputs = bf.readLine().split(" ");
			for(int c = 0; c < C; c++) {
				map[r][c] = Integer.parseInt(inputs[c]);
				if(map[r][c] > 0) dusts.offer(new int[] {r, c});
				if(map[r][c] == -1 && !flag) {
					machineA = new int[] {r, c};
					flag = true;
				}
				if(map[r][c] == -1 && flag) machineB = new int[] {r, c};
			}
		}
		
		int result = 0;
		
		while(T-- > 0) {
			int[][] newMap = new int[R][C];
			
			// 2. 미세먼지 확산
		    // 큐에서 하나씩 꺼내서 새로운 맵에 나눈 값들 더하면서 넣어준다.
			int size = dusts.size();
			
			while(size-- > 0) {
				int[] curr = dusts.poll();
				int cnt = 0;
				int diffusion = map[curr[0]][curr[1]] / 5;
				
				for(int i = 0; i < 4; i++) {
					int nx = curr[0] + dx[i], ny = curr[1] + dy[i];
					
					if(nx < 0 || ny < 0 || nx >= R || ny >= C) continue;
					if(map[nx][ny] < 0) continue;
					
					newMap[nx][ny] += diffusion;
					
					cnt++;
				}
				
				int remain = map[curr[0]][curr[1]] - (diffusion * cnt);
				newMap[curr[0]][curr[1]] += remain;				
			}
			
			newMap[machineA[0]][machineA[1]] = -1;
			newMap[machineB[0]][machineB[1]] = -1;
			
			// 3. 공기 회전
			rotateByA(newMap);
			rotateByB(newMap);
			
			map = newMap;
			
			result = 0;
			// 4. 미세먼지 풀 재생성
			for(int r = 0; r < R; r++) {
				for(int c = 0; c < C; c++) {
					if(map[r][c] <= 0) continue;
					dusts.offer(new int[] {r, c});
					result += map[r][c];
				}
			}
			
//			print();
		}
		
		System.out.println(result);
		
	}
	
	static void rotateByA(int[][] newMap) {
		int prev = newMap[machineA[0]][machineA[1] + 1];
		
		// 우
		for(int c = 1; c < C; c++) {
			int tmp = newMap[machineA[0]][c];
			newMap[machineA[0]][c] = prev;
			prev = tmp;
		}
		newMap[machineA[0]][machineA[1] + 1] = 0;
		
		// 상
		for(int r = machineA[0] - 1; r >= 0; r--) {
			int tmp = newMap[r][machineA[1] + C - 1];
			newMap[r][machineA[1] + C - 1] = prev;
			prev = tmp;
		}
		
		// 좌
		for(int c = C - 2; c >= 0; c--) {
			int tmp = newMap[0][c];
			newMap[0][c] = prev;
			prev = tmp;
		}

		// 하
		for(int r = 1; r < machineA[0]; r++) {
			int tmp = newMap[r][machineA[1]];
			newMap[r][machineA[1]] = prev;
			prev = tmp;
		}
	}
	
	static void rotateByB(int[][] newMap) {
		int prev = newMap[machineB[0]][machineB[1] + 1];
		
		// 우
		for(int c = 1; c < C; c++) {
			int tmp = newMap[machineB[0]][c];
			newMap[machineB[0]][c] = prev;
			prev = tmp;
		}
		newMap[machineB[0]][machineB[1] + 1] = 0;

		// 하
		for(int r = machineB[0] + 1; r < R; r++) {
			int tmp = newMap[r][machineB[1] + C - 1];
			newMap[r][machineB[1] + C - 1] = prev;
			prev = tmp;
		}
		
		// 좌
		for(int c = C - 2; c >= 0; c--) {
			int tmp = newMap[R - 1][c];
			newMap[R - 1][c] = prev;
			prev = tmp;
		}

		// 상
		for(int r = R - 2; r > machineB[0]; r--) {
			int tmp = newMap[r][machineB[1]];
			newMap[r][machineB[1]] = prev;
			prev = tmp;
		}
	}
	
	
	static void print() {
		for(int r = 0; r < R; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
	
}