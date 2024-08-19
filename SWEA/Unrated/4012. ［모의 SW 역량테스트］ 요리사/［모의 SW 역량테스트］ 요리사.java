import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, minValue;
	static int[][] s;
	static int[] foodA;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			s = new int[N][N];
			minValue = Integer.MAX_VALUE;
			foodA = new int[N/2];
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
		int[] foodB = new int[N/2];
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
		for(int i = 0; i < N/2; i++) {
			for(int j = 0; j < N/2; j++) {
				if(i == j) continue;
				// 음식 A 더하기
				A += s[foodA[i]][foodA[j]];
				// 음식 B 더하기
				B += s[foodB[i]][foodB[j]];
			}
		}
		minValue = Math.min(minValue, Math.abs(A - B));
	}
}