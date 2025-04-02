import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int bIdx = 0;
        int[] nums = new int[N];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
            if(nums[i] == B) bIdx = i;
        }

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        int cal = 0;

        for(int idx = bIdx - 1; idx >= 0; idx--) {
            if(nums[idx] > B) {
                cal++;
            } else if(nums[idx] < B) {
                cal--;
            }

            map.put(cal, map.getOrDefault(cal, 0) + 1);
        }

        cal = 0;
        int answer = 0;
        for(int idx = bIdx; idx < N; idx++) {
            if(nums[idx] > B) {
                cal++;
            } else if(nums[idx] < B) {
                cal--;
            }

            answer += map.getOrDefault(-cal, 0); // 작(cal) - 큰(cal) = 0
        }
        System.out.println(answer);
    }
}
