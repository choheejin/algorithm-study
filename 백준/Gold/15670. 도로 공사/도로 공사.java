import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] nums = new int[N + 1];
		int[] up = new int[N + 1]; // 오르막 누적합 계산
		int[] down = new int[N + 1]; // 내리막 누적합 계산

		st = new StringTokenizer(bf.readLine());

		nums[1] = Integer.parseInt(st.nextToken());
		up[1] = down[1] = 1;

		// 누적합 계산
		for (int i = 2; i <= N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
			up[i] = up[i - 1];
			down[i] = down[i - 1];
			if (nums[i] > nums[i - 1]) {
				down[i] = down[i - 1] + 1;
			} else {
				up[i] = up[i - 1] + 1;
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(bf.readLine());

			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());

			// 배열 왼 쪽 부분 오르막 갯수 구하기 up[s-1]
			// 배열 오른쪽 부분 오르막 갯수 구하기 up[n+1] - up[e]
			// 뒤바꾼 배열의 오르막 갯수 구하기 -> 내리막 개수로 구하면 됨: down[e] - down[s]가 아닌 이유는? 누적합이니까 해당 구간을
			// 구하기 위해선 down[e] - down[s-1]

			int cnt = up[s - 1] + (down[e] - down[s - 1]) + (up[N] - up[e]);

			// 뒤바뀐 배열 즉, 새로운 배열에 대해서 새로운 오르막 개수가 존재할 경우의 수를 생각해보자
			if (s > 1 && nums[e] > nums[s - 1]) {
				cnt--;
			}
			if (e < N && nums[s] < nums[e + 1]) {
				cnt--;
			}

			// 원래 하나였던 오르막길이 서로 다른 오르막 길이 되므로~~
			if (e < N && up[e + 1] == up[e]) {
				cnt++;
			}
			if (s > 1 && down[s] == down[s - 1]) {
				cnt++;
			}

			sb.append(cnt).append("\n");
		}
		System.out.println(sb);
	}
}