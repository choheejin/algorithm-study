import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.stream.Stream;

public class Main {
	static int n;
	static long[] nums, sums;

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		int q = Integer.parseInt(st.nextToken());

		nums = new long[n + 1];
		sums = new long[n + 1]; // 누적합

		st = new StringTokenizer(bf.readLine());

		for (int i = 1; i <= n; i++) {
			long num = Long.parseLong(st.nextToken());
			nums[i] = num;
			sums[i] = sums[i - 1] + num;
		}

		while (q-- > 0) {
			st = new StringTokenizer(bf.readLine());
			long l = Long.parseLong(st.nextToken());
			long r = Long.parseLong(st.nextToken());

			// 좌표의 범위를 구한다.
			int L = Arrays.binarySearch(nums, l);
			L = L < 0 ? -L-1 : L;
			
			int R = Arrays.binarySearch(nums, r);
			R = R < 0 ? -R-2 : R;

			if (L >= R) {
				sb.append("0\n");
				continue;
			} else {
				// 최대 회의 비용 => 감소/증가 방향 둘 중 한곳에서 최대 회의 비용이 발생한다.
				long maxCost = Math.max(getCost(L, L, R), getCost(R, L, R));

				// 최소 회의 비용 => 중간값에서 최소 회의 비용이 발생한다.
				long minCost = getCost((L + R) / 2, L, R);

				sb.append(maxCost - minCost).append("\n");
			}
		}
		System.out.println(sb);
	}

	private static long getCost(int target, int l, int r) {
		// 우측 / 좌측을 나눠서 생각해야하는 이유 : 절대값이기 때문에

		long right = (sums[r] - sums[target - 1]) - (long) (nums[target] * (r - target + 1));
		long left = (long) (nums[target] * (target - l + 1)) - (sums[target] - sums[l - 1]);

		return right + left;
	}
}