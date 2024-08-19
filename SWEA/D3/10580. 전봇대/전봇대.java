import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    static int N, result;
    static int[][] d;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(bf.readLine());
            d = new int[N][2];
            result = 0;
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                d[i][0] = x;
                d[i][1] = y;
            }

            for(int i = 0; i < N-1; i++) {
                for(int j = i+1; j < N; j++) {
                    if((d[i][0] > d[j][0] && d[i][1] < d[j][1]) || (d[i][0] < d[j][0] && d[i][1] > d[j][1])) result++;
                }
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }
}