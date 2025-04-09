import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        int[] arr = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp1 = new int[N]; // 원소를 제거하지 않은 최대 연속합
        int[] dp2 = new int[N]; // 한 개 제거한 최대 연속합

        dp1[0] = arr[0];
        dp2[0] = 0;
        int result = arr[0];

        for (int i = 1; i < N; i++) {
            dp1[i] = Math.max(dp1[i - 1] + arr[i], arr[i]);
            dp2[i] = Math.max(dp2[i - 1] + arr[i], dp1[i - 1]);
            result = Math.max(result, Math.max(dp1[i], dp2[i]));
        }

        System.out.println(result);
    }
}
