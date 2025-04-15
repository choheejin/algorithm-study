import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] teamHI = new int[N];
        int[] teamARC = new int[M];
        st = new StringTokenizer(bf.readLine());

        for(int i = 0; i < N; i++) {
            teamHI[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < M; i++) {
            teamARC[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(teamHI);
        Arrays.sort(teamARC);

        long draw = 0;
        long win = 0;
        long lose = 0;

//        System.out.println(Arrays.toString(teamARC));

        for(int i = 0; i < N; i++) {
            int lb = lowerBound(teamARC, 0, M, teamHI[i]);  // 자신보다 작은 수 개수
            int ub = upperBound(teamARC, 0, M, teamHI[i]);  // 자신 이하 수 개수

            int winCnt = lb;
            int drawCnt = ub - lb;

            win += winCnt;
            draw += drawCnt;
        }

        lose = ((long) N * M) - draw - win;

        System.out.println(win + " " + lose + " " + draw);

    }

    // target 보다 크거나 같은 수가 처음 나오는 위치 반환
    public static int upperBound(int[] arr, int start , int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] <= target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }

    // target 보다 작거나 같은 수가 처음 나오는 위치 반환
    public static int lowerBound(int[] arr, int start , int end, int target) {
        while (start < end) {
            int mid = (start + end) / 2;
            if (arr[mid] < target) {
                start = mid + 1;
            } else {
                end = mid;
            }
        }
        return start;
    }
}
