import java.io.*;
import java.util.*;

public class Main {
    static final long INF = Long.MAX_VALUE / 4;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int limit = Integer.parseInt(br.readLine().trim());
        int N = Integer.parseInt(br.readLine().trim());

        // 구간 거리: 0->1, 1->2, ..., N->N+1 총 N+1개
        int[] seg = new int[N + 2]; // seg[1..N+1] 사용
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N + 1; i++) seg[i] = Integer.parseInt(st.nextToken());

        // 정비 시간: 정점 1..N, 출발(0), 도착(N+1)은 0
        long[] time = new long[N + 2];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) time[i] = Integer.parseInt(st.nextToken());
        time[0] = 0; time[N + 1] = 0;

        // prefix sum으로 거리 누계
        int[] pref = new int[N + 2]; // pref[0]=0
        for (int i = 1; i <= N + 1; i++) pref[i] = pref[i - 1] + seg[i];

        // 다익스트라 (정점 0..N+1)
        long[] dist = new long[N + 2];
        int[] prev = new int[N + 2];
        Arrays.fill(dist, INF);
        Arrays.fill(prev, -1);

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        dist[0] = 0;
        pq.offer(new long[]{0, 0}); // {cost, node}

        boolean[] done = new boolean[N + 2];

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (done[u]) continue;
            done[u] = true;
            if (u == N + 1) break;
            if (d != dist[u]) continue;

            // u에서 갈 수 있는 v(u< v <= N+1) 중 거리 제한 만족만 완화
            for (int v = u + 1; v <= N + 1; v++) {
                int segDist = pref[v] - pref[u];
                if (segDist > limit) break;
                long w = time[v]; // 도착 정점의 정비시간 (도착=0)
                long nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    prev[v] = u;
                    pq.offer(new long[]{nd, v});
                }
            }
        }

        // 결과 출력
        System.out.println(dist[N + 1]);

        // 경로 복원 (정비소만 추출: 1..N)
        List<Integer> path = new ArrayList<>();
        int cur = N + 1;
        while (cur != -1 && cur != 0) {
            if (1 <= cur && cur <= N) path.add(cur);
            cur = prev[cur];
        }
        Collections.reverse(path);

        System.out.println(path.size());
        if (!path.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < path.size(); i++) {
                if (i > 0) sb.append(' ');
                sb.append(path.get(i));
            }
            System.out.println(sb);
        } else {
            System.out.println();
        }
    }
}
