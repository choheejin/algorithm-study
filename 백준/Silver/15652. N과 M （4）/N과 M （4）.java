import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] nums;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        nums = new int[M];
        combi(0, 1);
        System.out.println(sb);
    }

    public static void combi(int cnt, int start) {
        if(cnt == M) {
            for(int i = 0; i < M; i++) {
                sb.append(nums[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start; i <= N; i++) {
            nums[cnt] = i;
            combi(cnt + 1, i);
        }
    }
}