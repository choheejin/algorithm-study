import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, cmdTotal, cmds[], nums[], selectedCmds[];

	static boolean isSelected[];
	static int minValue, maxValue;
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
	
		for(int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			
			nums = new int[N]; // 피연산자
			cmdTotal = N-1; // 연산자의 수는 피연산자 - 1;
			cmds = new int[4]; // 연산자들
			selectedCmds = new int[cmdTotal]; // 연산자 순열
			isSelected = new boolean[cmdTotal]; // 중복확인
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < 4; i++) {
				cmds[i] = Integer.parseInt(st.nextToken());
			}

			st = new StringTokenizer(bf.readLine());
			for(int i = 0; i < N; i++) {
				nums[i] = Integer.parseInt(st.nextToken());
			}
			
			minValue = Integer.MAX_VALUE;
			maxValue = Integer.MIN_VALUE;
			perm(0);
			sb.append("#").append(tc).append(" ").append(maxValue - minValue).append("\n");
		}
		System.out.println(sb);
	}
	
	static void perm(int cnt) {
		if(cnt == cmdTotal) {
			int result = calculate();
			minValue = Math.min(minValue, result);
			maxValue = Math.max(maxValue, result);
			return;
		}
		
		for(int i = 0; i < 4; i++) {
			if(cmds[i] == 0) continue;
			cmds[i]--;
			selectedCmds[cnt] = i;
			perm(cnt + 1); // 더 뽑기
			cmds[i]++;
		}
	}
	
	static int calculate() {
		int result = nums[0];
		for(int i = 0; i < cmdTotal; i++) {
			switch (selectedCmds[i]) {
			case 0:
				result += nums[i+1];
				break;
			case 1:
				result -= nums[i+1];
				break;
			case 2:
				result *= nums[i+1];
				break;
			case 3:
				result /= nums[i+1];
				break;
			}
		}
		return result; 
	}
}