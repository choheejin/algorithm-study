import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(bf.readLine());
        HashSet<Long> set = new HashSet<>();

        Long[] nums = new Long[N];
        for(int i = 0; i < N; i++) {
            nums[i] = Long.parseLong(bf.readLine());
            set.add(nums[i]);
        }

        Arrays.sort(nums, Collections.reverseOrder());

        for(int k = 0; k < N; k++) {
            long maxK = nums[k];
            for(int x = k; x < N; x++) {
                for(int y = x; y < N; y++) {
                    long z = maxK - nums[x] - nums[y];
                    if(set.contains(z)) {
                        System.out.println(maxK);
                        return;
                    }
                }
            }
        }
    }
}
