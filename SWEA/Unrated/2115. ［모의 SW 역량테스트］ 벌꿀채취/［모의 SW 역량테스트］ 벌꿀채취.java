import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    // 1. 가로로 연속되도록 선택한다
    // 2. 선택한 것에 대해 선택/비선택 부분집합 돌리기

    static int N, M, C;
    static int[][] map;

    static int max;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(bf.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            map = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(bf.readLine());
                for(int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            max = 0;
            select();

            sb.append("#").append(tc).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
    }

    static void select() {
        for(int i = 0; i < N * N; i ++) {
            int x1 = i / N;
            int y1 = i % N;
            if(y1 > N-M) continue;

            int profit1 = getMax(x1, y1, 0, 0, 0);

            for(int j = i + M; j < N*N; j++) {
                int x2 = j / N;
                int y2 = j % N;
                if(y2 > N-M) continue;

                int profit2 = getMax(x2, y2, 0,0,0);

                max = Math.max(max, profit2 + profit1);
            }
        }
    }

    static int getMax(int x, int y, int cnt, int sum, int profit) {
        if(sum > C) return 0;

        if(cnt == M) return profit;

        int result1 = getMax(x, y, cnt + 1, sum + map[x][y+cnt], profit + map[x][y+cnt] * map[x][y+cnt]);
        int result2 = getMax(x, y, cnt + 1, sum, profit);

        return Math.max(result1, result2);
    }
}