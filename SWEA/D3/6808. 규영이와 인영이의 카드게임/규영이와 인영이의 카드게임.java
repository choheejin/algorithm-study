import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static int T, winCnt, loseCnt;
	static List<Integer> player1; // 규영이 - 고정값
	static List<Integer> player2; // 인영이
	static boolean[] isSelected;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			// 카드 준비
			player1 = new ArrayList<Integer>();
			player2 = new ArrayList<Integer>();

			isSelected = new boolean[9];
			winCnt = 0;
			loseCnt = 0;

			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < 9; i++) {
				player1.add(Integer.parseInt(st.nextToken()));
			}
			for (int i = 1; i <= 18; i++) {
				if (player1.contains(i))
					continue;
				player2.add(i);
			}
			startGame(0, 0, 0);
			System.out.printf("#%d %d %d\n", tc, winCnt, loseCnt);
		}
	}

	// cnt: 현재 라운드
	public static void startGame(int cnt, int totalP1, int totalP2) {
		// 순열 생성의 종료
		if (cnt == 9) {
			if(totalP1 > totalP2) {
				winCnt++;
			}
			if(totalP1 < totalP2) {
				loseCnt++;
			}
			return;
		}

		// p2의 카드에서 순열 뽑기
		for (int i = 0; i < player2.size(); i++) {
			// 이미 뽑힌 수 일 때는 지나친다.
			if (isSelected[i])
				continue;

			isSelected[i] = true;

			int p1 = player1.get(cnt);
			int p2 = player2.get(i);
			if(p1 > p2) {
				startGame(cnt + 1, totalP1 + p1 + p2 , totalP2);
			}
			if(p1 < p2) {
				startGame(cnt + 1, totalP1 , totalP2 + p1 + p2);
			}

			isSelected[i] = false;
		}
	}
}