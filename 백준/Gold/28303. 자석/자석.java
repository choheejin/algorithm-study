import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        // N, K 입력받기
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 에너지 상수 입력받기
        int[] tables = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            tables[i] = Integer.parseInt(st.nextToken());
        }
        
        // N-S와 S-N 배열 생성
        int[] arr_NS = new int[N - 1];
        int[] arr_SN = new int[N - 1];
        
        for (int i = 1; i < N; i++) { // N-S
            arr_NS[i - 1] = tables[i - 1] - tables[i] - K;
        }
        
        for (int i = 1; i < N; i++) { // S-N
            arr_SN[i - 1] = tables[i] - tables[i - 1] - K;
        }
        
        // 최대 연속 부분합 계산 (Kadane's algorithm)
        int[] temp_NS = new int[N - 1];
        int[] temp_SN = new int[N - 1];
        
        temp_NS[0] = arr_NS[0];
        for (int i = 1; i < N - 1; i++) {
            temp_NS[i] = Math.max(0, temp_NS[i - 1]) + arr_NS[i];
        }
        
        temp_SN[0] = arr_SN[0];
        for (int i = 1; i < N - 1; i++) {
            temp_SN[i] = Math.max(0, temp_SN[i - 1]) + arr_SN[i];
        }
        
        // 최댓값 계산
        int max_NS = Arrays.stream(temp_NS).max().getAsInt();
        int max_SN = Arrays.stream(temp_SN).max().getAsInt();
        
        System.out.println(Math.max(max_NS, max_SN));
    }
}