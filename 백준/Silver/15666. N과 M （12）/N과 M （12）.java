import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;
    static HashSet<Integer> nums;
    static ArrayList<Integer> sortedNums;
    static int[] selectedNums;
    static StringBuilder sb;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(bf.readLine());
        nums = new HashSet<>();
        selectedNums = new int[M];
        for(int i = 0; i < N; i++) {
            nums.add(Integer.parseInt(st.nextToken()));
        }
        sortedNums = new ArrayList<Integer>(nums);
        Collections.sort(sortedNums);
        combi(0, 0);
        System.out.println(sb);
    }

    public static void combi(int cnt, int start) {
        if(cnt == M) {
            for(int i = 0; i < M; i++) {
                sb.append(selectedNums[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = start; i < sortedNums.size(); i++) {
            selectedNums[cnt] = sortedNums.get(i);
            combi(cnt + 1, i);
        }
    }
}