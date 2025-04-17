import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 *  O(N log N) -> 그래서 이진탐색인 줄 알았는데 정렬 문제였다..!
 *  좀 더 시야를 넓게 가지자. 아주 좋은 문제인듯~~
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[] T = new long[N];
        for (int i = 0; i < N; i++) {
            T[i] = Long.parseLong(br.readLine());
        }

        if (K >= N) {
            System.out.println(N);
            return;
        }

        long[] gaps = new long[N - 1];
        for (int i = 0; i < N - 1; i++) {
            gaps[i] = T[i + 1] - T[i] - 1;
        }

        Arrays.sort(gaps);

        long idle = 0;
        for (int i = 0; i < N - K; i++) {
            idle += gaps[i];
        }

        System.out.println(idle + N);
    }
}
