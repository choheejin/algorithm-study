import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        int max = Integer.MIN_VALUE;
        st = new StringTokenizer(bf.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
        }

        int start = 1, end = max;
        while(start <= end) {
            long count = 0;

            int mid = (start + end) / 2;

            for(int num: arr) {
                if(num > mid) count += (num - mid);
            }

            if(count < M) end = mid - 1;
            else start = mid + 1;
        }
        System.out.println(end);
    }
}