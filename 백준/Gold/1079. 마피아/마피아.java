import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 1. 밤인지, 낮인지 구분한다.
 * 2. 밤의 경우, 사람 선택,
 * 3. 죽인다.
 */
public class Main {
    static int N, mafia;
    static int[][] R;
    static int[] dangers;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        StringTokenizer st = new StringTokenizer(bf.readLine());

        dangers = new int[N];
        for (int i = 0; i < N; i++) {
            dangers[i] = Integer.parseInt(st.nextToken());
        }

        R = new int[N][N];
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < N; j++) {
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mafia = Integer.parseInt(bf.readLine());

        visited = new boolean[N];
        answer = 0;
        process(N, 0);
        System.out.println(answer);
    }

    public static void process (int num, int cnt) {
        if(visited[mafia] || num == 1) {
            answer = Math.max(answer, cnt);
            return;
        }

        // 밤
        if(num % 2 == 0) {
            for(int i = 0; i< N; i++) {
                if(visited[i] || i == mafia) continue;

                for(int j = 0; j < N; j++) {
                    dangers[j] = dangers[j] + R[i][j];
                }

                visited[i] = true;

                process(num - 1, cnt + 1);

                for(int j = 0; j < N; j++) {
                    dangers[j] = dangers[j] - R[i][j];
                }

                visited[i] = false;
            }
        }

        // 낮
        else {
            int max = 0, idx = N - 1;

            for(int i = 0; i < N; i++) {
                if(visited[i]) continue;
                if(max < dangers[i]) {
                    max = dangers[i];
                    idx = i;
                } else if (max == dangers[i]) {
                    max = dangers[i];
                    idx = Math.min(i, idx);
                }
            }

            visited[idx] = true;
            process(num - 1, cnt);
            visited[idx] = false;
        }
    }

}
