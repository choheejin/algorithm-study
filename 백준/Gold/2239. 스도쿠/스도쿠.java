import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
	static int[][] map;
	static List<int[]> zeros;
	static boolean[] visited;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		map = new int[9][9];
		zeros = new ArrayList<int[]>();
		
		
		for(int i = 0; i < 9; i++) {
			char[] inputs = bf.readLine().toCharArray();
			for(int j = 0; j < 9; j++) {
				map[i][j] = Integer.parseInt(String.valueOf(inputs[j]));
				
				if(map[i][j] == 0) {
					zeros.add(new int[] {i, j});
				}
				
			}
		}
		
		
		make(map, 0);
	}
	
	
	public static void make(int[][] map, int idx) {
		// 다 뽑았다.
		if(idx == zeros.size()) {
			
//			System.out.println(Arrays.toString(map[0]));
			for(int i = 0; i < 9; i++) {
				for(int j = 0; j < 9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			
			System.exit(0);
		}
		
		int i = zeros.get(idx)[0], j = zeros.get(idx)[1];
		
		// 해당 좌표가 속해있는 3*3 배열에서 사용하는 수 방문처리
		// 해당 좌표가 속해있는 세로 사용 수 방문처리
		// 해당 좌표가 속해있는 가로 사용 수 방문처리
		
		for(int num = 1; num < 10; num++) {
			visited = new boolean[10];
			
			nearArr(i, j); 
			nearColNum(i); 
			nearRowNum(j);
			
			if(!visited[num]) {
				map[i][j] = num;
				make(map, idx+1);
				map[i][j] = 0;
			}
		}
	}
	
	public static void nearRowNum(int col) {
		for(int i = 0; i < 9; i++) {
			
			visited[map[i][col]] = true;
		}
	}
	
	public static void nearColNum(int row) {
		for(int i = 0; i < 9; i++) {
			
			visited[map[row][i]] = true;
		}
		
	}
	
	public static void nearArr(int x, int y) {
		int rangeX = 3 * (x / 3);
		int rangeY = 3 * (y / 3);
		
//		System.out.println(rangeX + ": " + rangeY);
		
		for(int i = rangeX; i < rangeX + 3; i++) {
			for(int j = rangeY; j < rangeY + 3; j++) {
				
				visited[map[i][j]] = true;
			}
		}
	}
	
	public static void print() {
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
	}
}