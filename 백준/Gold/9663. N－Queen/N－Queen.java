import java.util.Arrays;
import java.util.Scanner;

// 같은 행에 퀸을 두지 않는 방식
public class Main {
	// 행에 열번호를 기억한다.
	// 대각선은? 행차이와 열차이가 동일하다.
	// 팔방탐색할 필요 없이, 행과 열의 차이를 통해 대각선 판단을 할 수 있다.
	static int N, col[], ans, call;
	static boolean[] flagR, flagL, flagCol;
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		ans = 0;
		N = sc.nextInt();
		
		col = new int[N+1]; // 1행부터 사용 -> 놓는 퀸의 위치를 파악하기 위함
		flagR = new boolean[2 * N];
		flagL = new boolean[2 * N + 1];
		flagCol = new boolean[N + 1];
		
		setQueens(1);
		
		System.out.println(ans);
	}
		
	// 퀸을 놓는다.
	static void setQueens(int rowNo) {
//		if(!isValid1(rowNo - 1)) return;

		// 1부터 놓기 때문에
		if(rowNo > N) {
			// 무조건 답
			ans++;
			return;
		}
		
		for(int c = 1; c <= N; c++) {
			if(!isValid2(rowNo, c)) continue;
			
			col[rowNo] = c;

			flagR[rowNo - c + N] = true;
			flagL[rowNo + c] = true;
			flagCol[c] = true;
			
			setQueens(rowNo+1);
			
			flagR[rowNo - c + N] = false;
			flagL[rowNo + c] = false;
			flagCol[c] = false;
		}
	}
	
	// 새로 놓은 퀸
	static boolean isValid1(int rowNo) {
		for(int k = 1; k < rowNo; k++) {
			// 행을 선택하기 때문에 - 같은 행에 놓여질 수 없다.
			// 현재 열의 위치와 이전 퀸의 열의 위치가 같다.
			// 현재 행의 위치와 이전 행의 위치 차이가 이전 퀸의 열의 위치와 현재 열의 위치 차이가 같다. - 대각선 파악
			if(col[rowNo] == col[k] || rowNo - k == Math.abs(col[rowNo] - col[k])) return false;
		}
		return true;
	}
	
	// 열마다 계속 놓게 된다.
	// 대각선 판단: 행과 열의 합 혹은 차가 일정하다. 
	// 			 => 양 대각선의 차(우측 대각선, 2N 크기 배열 필요)/합(좌측 대각선, 2N+1 크기의 배열 필요)의 배열을 생성하여 true,false 체크
	//			 퀸이 대각선 위치하는 게 있는지 확인할 수 있어진다.
	// 			 대신 초기화를 해줘야한다.
	static boolean isValid2(int rowNo, int c) {
		return !flagR[rowNo - c + N] && !flagL[rowNo + c] && !flagCol[c];
	}
}