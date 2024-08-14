import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    // (2**9) * (9!) = 약 1.8억 인데
    // 심지어 조건문도 있는데 왜..? 시간초과가 뜨는거지..??

    static int T, N, result;
    static int[] nums;
    static boolean[] isSelected;
    static int[] selectedNums;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(bf.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(bf.readLine());

            StringTokenizer st = new StringTokenizer(bf.readLine());

            nums = new int[N];
            isSelected = new boolean[N];
            result = 0;
            selectedNums = new int[N];
            for (int i = 0; i < N; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            perm(0);
            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb);
    }

    public static void perm(int cnt) {
        if(cnt == N) {
            check(0, 0, 0);
            return;
        }

        for(int i = 0; i < N; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            selectedNums[cnt] = nums[i];
            perm(cnt+1);
            isSelected[i] = false;
        }
    }
    
    public static void check(int cnt, int totalR, int totalL) {
    	if(totalR > totalL) return;
    	if(cnt == N) {
    		result++;
    		return;
    	}
    	check(cnt+1, totalR + selectedNums[cnt], totalL);
    	check(cnt+1, totalR, totalL + selectedNums[cnt]);
    }
}