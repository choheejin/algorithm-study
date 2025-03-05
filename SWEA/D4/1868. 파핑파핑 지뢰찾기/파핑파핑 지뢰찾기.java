import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * 문제 분석하기
 * 1. 입력을 받는다.
 * 2. 8방의 지뢰 존재 여부 숫자를 초기화 한다. 
 * 3. 0인 좌표를 Queue에 저장한다.
 * 4. 3번의 큐를 기준으로 BFS 돌린다.
 * 5. 남은 칸만큼 더해준다. 
 * 
 * 시간 복잡도
 * => 최대 O(N^2)일 것이다.
 */
public class Solution {
	static int N;
	static char[][] map;
	static int[][] landMine;
	static boolean[][] visited;
	
	static int[] dx = {0, 0, 1, -1, 1, -1, 1, -1}, 
				dy = {1, -1, 0, 0, 1, -1, -1, 1};
	
	static Queue<int[]> q;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			
			map = new char[N][];
			landMine = new int[N][N];
			visited = new boolean[N][N];
			
			q = new ArrayDeque<>();
			
			for(int i = 0; i < N; i++) {
				map[i] = bf.readLine().toCharArray();
			}
			
			
			for(int r = 0; r < N; r++) {
				for(int c = 0; c < N; c++) {
					// 현재 위치가 지뢰면 통과 
					if(map[r][c] == '*') {
						visited[r][c] = true;
						landMine[r][c] = -1;
						continue;
					}
					
					// (r, c)의 8방에 있는 지뢰 개수 찾아 저장하기
					for(int i = 0; i < 8; i++) {
						int nx = r + dx[i];
						int ny = c + dy[i];
						
						// 범위에 없다.
						if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
						
						
						if(map[nx][ny] == '*') {
							landMine[r][c] += 1;
						}
					}
				}
			}
			
			int cnt = 0;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(landMine[i][j] == 0 && !visited[i][j]) {
						dfs(i, j);
						cnt++;
					}
				}
			}
//			System.out.println(cnt);
//			print();
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!visited[i][j]) {
						cnt++;
					}
				}
			}
			
			sb.append("#").append(tc).append(" ").append(cnt).append("\n");			
		}
		System.out.println(sb);
	}
	
	
	public static void dfs(int x, int y) {
		if(x < 0 || y < 0 || x >= N || y >= N) return;
		visited[x][y] = true;
		
		for(int i = 0; i < 8; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			
			if(nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
			if(visited[nx][ny]) continue;
			visited[nx][ny] = true;
			if(landMine[nx][ny] == 0) {				
				dfs(nx, ny);
			}
		}
	}
	
		
	public static void print() {
		for(int i = 0 ; i < N; i++) {
			System.out.println(Arrays.toString(visited[i]));
		}
	}
}