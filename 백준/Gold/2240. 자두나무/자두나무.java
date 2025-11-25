
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 시간초과 흠,,, 
public class Main {
	static int T, W, len;
	static int result;
	static final int NEG = -1_000_000_000;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		T = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());


		int[][][] dp = new int[T+1][2][W+1]; // T초에 (0, 1)번 나무에 W 체력을 사용했는가? 

		// 이동할 것인가?, 이동안할 것인가?
		// w 사용했는가? <- 여기서 막히는데,,,
		
        for (int pos=0; pos<2; pos++){
            for (int w=0; w<=W; w++){
                dp[0][pos][w] = NEG;
            }
        }

        dp[0][0][0] = 0;  // 1번 나무(pos=0), 이동 0회

		
		// 씁,, 이상한
		for(int i = 1; i <= T; i++) {
			int num = Integer.parseInt(bf.readLine()) - 1;
		
			for(int w = W; w >= 0; w--) {
		        // pos = 0 (1번 나무)
		        dp[i][0][w] = dp[i-1][0][w];  // 이동 안 한 경우
		        if(w > 0) dp[i][0][w] = Math.max(dp[i][0][w], dp[i-1][1][w-1]);  // 이동해서 온 경우
		        if(num == 0) dp[i][0][w]++;


		        // pos = 1 (2번 나무)
		        dp[i][1][w] = dp[i-1][1][w];  // 이동 안 한 경우
		        if(w > 0) dp[i][1][w] = Math.max(dp[i][1][w], dp[i-1][0][w-1]);  // 이동해서 온 경우
		        if(num == 1) dp[i][1][w]++;
			}
		}

		for(int i = 0; i <= W; i++) {
			result = Math.max(result, Math.max(dp[T][0][i], dp[T][1][i]));
		}
		
//		System.out.println(list);
		System.out.println(result);
		
	}
	
	
	
	// 생각해보니 당연히 안되는거긴 함 ㅇㅇ;; ㅋㅋㅋㅋㅋ;;;
//	public static void combi (int start, int cnt, int value) {
//		if(cnt == W + 1) {
//			result = Math.max(result, value);
//			return;
//		}
//		
//		for(int i = start; i < len; i++) {
//			combi(i + 1, cnt + 1, value + list.get(i));
//			}
//	}
	
	
}
