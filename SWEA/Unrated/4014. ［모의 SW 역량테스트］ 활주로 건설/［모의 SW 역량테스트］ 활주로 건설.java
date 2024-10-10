import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static int N, X;
	static int[][] map;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			int N = Integer.parseInt(st.nextToken());
			int X = Integer.parseInt(st.nextToken());
			
			map = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int resultCnt = 0;
			
			// 1. 가로에 대해서 타당성 검증
			A:for(int i = 0; i < N; i++) {
				int prev = map[i][0];
				int cnt = 1;
				boolean flag = false;
				boolean result = true;
				
				for(int j = 1; j < N; j++) {
					if(Math.abs(prev - map[i][j]) > 1) {
						continue A;
					}
					if(prev - map[i][j] == 0) {
						cnt++;
					}
					else {
						// 그 전에 상승하는 부분이 존재했다면
						if(flag) {
							if(cnt < X) result = false;
							cnt -= X;
							flag = false;
						}
						if(prev - map[i][j] == -1) {
							if(cnt < X) result = false;
							cnt = 1;
						}
						if(prev - map[i][j] == 1) {
							flag = true;
							cnt = 1;
						}
					}
					prev = map[i][j];
				}
				if(flag && cnt < X) continue;
				if(result) resultCnt++;
			}
			
			// 세로에 대해서 검증
			A : for(int j = 0; j < N; j++) {
				int prev = map[0][j];
				int cnt = 1;
				boolean flag = false;
				boolean result = true;

				for(int i = 1; i < N; i++) {
					if(Math.abs(prev - map[i][j]) > 1) {
						continue A;
					}
					
					if(prev - map[i][j] == 0) {
						cnt++;
					}
					
					else {
						// 그 전에 상승하는 부분이 존재했다면
						if(flag) {
							if(cnt < X) result = false;
							cnt -= X;
							flag = false;
						}
						if(prev - map[i][j] == -1) {
							if(cnt < X) result = false;
							cnt = 1;
						}
						if(prev - map[i][j] == 1) {
							flag = true;
							cnt = 1;
						}
					}
					prev = map[i][j];
				}
				if(flag && cnt < X) continue;
				if(result) resultCnt++;
			}
			
			sb.append("#").append(tc).append(" ").append(resultCnt).append("\n");
		}
		
		System.out.println(sb);
	}
}