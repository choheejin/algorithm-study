import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int[] dp = new int[N+1];
        int end = 0;
        for(int i = 1; i <= N; i++) {
            int target = Integer.parseInt(st.nextToken());
            if(dp[end] < target) {
                dp[++end] = target;
            }
            else {
                int idx = binary_search(dp, end, target);
                dp[idx] = target;
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
