import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());

        int[][] nums = new int[N+1][3];

        for(int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            nums[i][0] = Integer.parseInt(st.nextToken());
            nums[i][1] = Integer.parseInt(st.nextToken());
            nums[i][2] = Integer.parseInt(st.nextToken());
        }

        long[][] dpMax = new long[N+1][3];
        long[][] dpMin = new long[N+1][3];

        for(int i = 1; i <= N; i++) {
            dpMax[i][0] = Math.max(dpMax[i-1][0], dpMax[i-1][1]) + nums[i][0];
            dpMax[i][1] = Math.max(dpMax[i-1][0], Math.max(dpMax[i-1][1], dpMax[i-1][2])) + nums[i][1];
            dpMax[i][2] = Math.max(dpMax[i-1][1], dpMax[i-1][2]) + nums[i][2];

            dpMin[i][0] = Math.min(dpMin[i-1][0], dpMin[i-1][1]) + nums[i][0];
            dpMin[i][1] = Math.min(dpMin[i-1][0], Math.min(dpMin[i-1][1], dpMin[i-1][2])) + nums[i][1];
            dpMin[i][2] = Math.min(dpMin[i-1][1], dpMin[i-1][2]) + nums[i][2];

        }


        long max = Math.max(dpMax[N][2], Math.max(dpMax[N][0], dpMax[N][1]));
        long min = Math.min(dpMin[N][2], Math.min(dpMin[N][0], dpMin[N][1]));


        System.out.println(max + " " + min);
    }
}
