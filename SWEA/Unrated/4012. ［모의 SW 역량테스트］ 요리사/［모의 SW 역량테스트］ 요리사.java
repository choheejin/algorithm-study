import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/***
 * 메모리: 29,756 KB, 시간: 184 ms, 코드길이: 1,612 Bytes
 */

public class Solution {
	// 시간 복잡도: (nCn/2 * n/2^2) 인데, n이 최대 16이므로 최악의 경우  12,870 * 64 = 823,680 일 것이다.

	static int N, minValue;
	static int[][] s;
	static int[] foodA;
	static int[] foodB;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			s = new int[N][N];
			minValue = Integer.MAX_VALUE;
			foodA = new int[N/2];
			foodB = new int[N/2];
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) {
					s[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			combi(0, 0);
			sb.append("#").append(tc).append(" ").append(minValue).append("\n");
		}
		System.out.println(sb);
	}

	// N/2개 고르기 (중복 없이)
	private static void combi(int cnt, int start) {
		if(cnt == N/2) {
			choose();
			return;
		}

		for(int i = start; i < N; i++) {
			foodA[cnt] = i;
			combi(cnt+1, i+1);
		}
	}

	private static void choose() {
		int idxA = 0, idxB = 0, A = 0, B = 0;
//		int[] foodB = new int[N/2];
		// 나머지 식재료 확인하기
		for(int i = 0; i < N; i++) {
			if(idxA < N/2 && foodA[idxA] == i) {
				idxA++;
				continue;	
			}
			if(idxB < N/2) {
				foodB[idxB] = i;
				idxB++;
			}
		}
		// 자기 자신을 제외한 시너지 더해주기
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < N/2; j++) {
				if(i == j) continue;
				// 음식 A 더하기
				A += s[foodA[i]][foodA[j]];
				// 음식 B 더하기
				B += s[foodB[i]][foodB[j]];
			}
		}
		// 최소값 갱신
		minValue = Math.min(minValue, Math.abs(A - B));
	}
}