import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int[] arr = Arrays.stream(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int[] dp = new int[N+1];
        int end = 0;

        for(int i = 1; i <= N; i++) {
            if(dp[end] < arr[i - 1]) {
                dp[++end] = arr[i - 1];
            }
            else {
                int idx = binary_search(dp, end, arr[i - 1]);
                dp[idx] = arr[i-1];
            }
        }

        System.out.println(end);
    }

    private static int binary_search(int[] arr, int end, int target) {
        int start = 1;
        while(start < end) {
            int mid = (start + end) / 2;
            if(arr[mid] >= target) {
                 end = mid;
                 continue;
            }

            start = mid + 1;
        }
        return end;
    }
}
