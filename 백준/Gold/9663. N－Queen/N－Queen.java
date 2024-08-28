import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	static int N, size, result;
	static int[] chess;
	static boolean[] colIdx;
	static boolean[] R; // 우측 대각선
	static boolean[] L; // 좌측 대각선
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		size = N+1;
		chess = new int[size];
		colIdx = new boolean[size];
		R = new boolean[size * 2 + 1];
		L = new boolean[size * 2];
		perm(1);
		System.out.println(result);
	}
	
	static void perm(int row) {
		if(row > N) {
			result++;
			return;
		}
		
		for(int col = 1; col <= N; col++) {
			if(!check(row, col)) continue;
			
			chess[row] = col;
			
			colIdx[col] = true;
			R[row - col + N] = true;
			L[row + col] = true;
			
			perm(row + 1);
			
			colIdx[col] = false;
			R[row - col + N] = false;
			L[row + col] = false;
		}
	}
	
	// 우측 대각선은 차가 일정하다. (배열의 크기 2N+1)
	// 좌측 대각선은 합이 일정하다. (배열의 크기 2N)
	static boolean check(int row, int col) {
		return !colIdx[col] && !R[row - col + N] && !L[row + col];
	}
}