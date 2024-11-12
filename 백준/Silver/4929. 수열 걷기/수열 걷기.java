import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    static int[] nums1, nums2;
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String cmd = bf.readLine();

            if(cmd.trim().equals("0")) break;

            nums1 = Stream.of(cmd.split(" ")).mapToInt(Integer::parseInt).toArray();
            nums2 = Stream.of(bf.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            List<Integer> points1 = new ArrayList<>();
            List<Integer> points2 = new ArrayList<>();

            // 1. 교차점 찾기
            for(int i = 1; i < nums1.length; i++) {
                int target = nums1[i];
                int idx = binary_search(target);
                if(idx == -1) continue;
                points1.add(i);
                points2.add(idx);
            }


            // 수열 1에 대한 구간 합
            int[] sum1 = new int[points1.size() + 1];
            int sum = 0, idx = 0;
            for(int i = 1; i < nums1.length; i++) {
                sum += nums1[i];
                if(idx < points1.size() && i == points1.get(idx)) {
                    sum1[idx++] = sum;
                    sum = 0;
                }
            }
            sum1[idx++] = sum;

            // 수열 2에 대한 구간 합
            int[] sum2 = new int[points2.size() + 1];
            sum = 0;
            idx = 0;
            for(int i = 1; i < nums2.length; i++) {
                sum += nums2[i];
                if(idx < points2.size() && i == points2.get(idx)) {
                    sum2[idx++] = sum;
                    sum = 0;
                }
            }
            sum2[idx++] = sum;

            int total = 0;
            for(int i = 0; i < sum2.length; i++) {
                total += Math.max(sum1[i], sum2[i]);
            }

            sb.append(total).append("\n");

        }
        System.out.println(sb);
    }
    
    private static int binary_search(int target) {
        int start = 1, end = nums2[0];
        
        while(start <= end) {
            int mid = (start + end) / 2;

            if(nums2[mid] == target) return mid;
            
            if(nums2[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        
        return -1;
    }
}