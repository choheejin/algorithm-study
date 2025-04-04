import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        Integer[] nums = new Integer[N];

        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums, Collections.reverseOrder());
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> Integer.compare(a, b));

        for(int i = 0; i < N; i++) {
            if(q.size() == M) {
                int earliest = q.poll();
                q.offer(earliest + nums[i]);
            }
            else {
                q.offer(nums[i]);
            }
        }

        int max = Integer.MIN_VALUE;
        while(!q.isEmpty()) {
            max = Math.max(max, q.poll());
        }
        System.out.println(max);
    }
}
