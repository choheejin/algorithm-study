import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        PriorityQueue<int[]> checkoutQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return Integer.compare(a[1], b[1]); // 대기 시간 같으면 계산대 번호 작은 순
            return Integer.compare(a[0], b[0]); // 대기 시간 짧은 순
        });

        PriorityQueue<int[]> exitQueue = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) return Integer.compare(b[1], a[1]); // 계산 완료 시간이 같으면 계산대 번호 높은 순
            return Integer.compare(a[0], b[0]); // 계산 완료 시간 기준 정렬
        });

        int[] checkoutTimes = new int[K + 1];

        for (int i = 0; i < K; i++) {
            checkoutQueue.offer(new int[]{0, i + 1}); // 초기 계산대 대기 시간 0으로 설정
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            int id = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            int[] current = checkoutQueue.poll(); // 가장 짧은 대기 시간의 계산대 선택
            int finishTime = current[0] + w;

            exitQueue.offer(new int[]{finishTime, current[1], id}); // 계산 완료 시간, 계산대 번호, 회원 번호
            checkoutQueue.offer(new int[]{finishTime, current[1]}); // 계산대 대기 시간 갱신
        }

        long result = 0;
        int order = 1;

        while (!exitQueue.isEmpty()) {
            int[] current = exitQueue.poll();
            result += (long) order * current[2];
            order++;
        }

        System.out.println(result);
    }
}