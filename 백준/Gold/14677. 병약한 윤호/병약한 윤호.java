import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    static char[] medicines;
    static int[][][] dp;
    static int N;
    static final char[] ORD = {'B','L','D'};

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bf.readLine());
        N = n * 3;
        medicines = bf.readLine().toCharArray();

        dp = new int[N][N][3];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                Arrays.fill(dp[i][j], -1);

        System.out.println(dfs(0, N - 1, 0));
    }

    static int dfs(int front, int rear, int t) {
        if (front > rear) return 0;
        if (dp[front][rear][t] != -1) return dp[front][rear][t];

        int best = 0;

        if (medicines[front] == ORD[t]) {
            best = Math.max(best, 1 + dfs(front + 1, rear, (t + 1) % 3));
        }
        if (medicines[rear] == ORD[t]) {
            best = Math.max(best, 1 + dfs(front, rear - 1, (t + 1) % 3));
        }

        return dp[front][rear][t] = best;
    }
    
    // 원래 풀이...
//	public static void dfs (int currIdx, int cnt, int front, int rear) {
//		if(cnt == N) {
//			maxLen = Math.max(maxLen, cnt);
//			return;
//		}
//		if(currIdx < 0 || currIdx >= N) return;
//		if(isVisited[currIdx]) return;
//		isVisited[currIdx] = true;
//
//		// front
//		if(!(front < 0 || front >= N) && !isVisited[front] && routes[currIdx][front] == -1) {
//			if(check(medicines[currIdx], medicines[front])) {
//				routes[currIdx][front] = cnt + 1;
//				dfs(front, cnt + 1, front + 1, rear);
//			}
//		}
//		
//		// rear
//		if(!(rear < 0 || rear >= N) && !isVisited[rear] && routes[currIdx][rear] == -1) {
//			if(check(medicines[currIdx], medicines[rear])) {
//				routes[currIdx][rear] = cnt + 1;
//				dfs(rear, cnt + 1, front, rear - 1);
//			}
//		}		
//		isVisited[currIdx] = false;
//		maxLen = Math.max(maxLen, cnt);
//	}
//	
//	public static boolean check(char curr, char nxt) {
//		if(curr == 'B' && nxt == 'L') return true;
//		if(curr == 'L' && nxt == 'D') return true;
//		if(curr == 'D' && nxt == 'B') return true;
//		return false;
//	}
}