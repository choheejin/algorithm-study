import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.List;

public class Main {
    static HashSet<String> zero = new HashSet<>(Arrays.asList("Mugunghwa", "ITX-Saemaeul", "ITX-Cheongchun"));

    static HashSet<String> half = new HashSet<>(Arrays.asList("S-Train", "V-Train"));
    static class Node {
        int to;
        double pay;
        String type;

        public Node(int to, double pay, String type) {
            this.to = to;
            this.pay = pay;
            this.type = type;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        List<Node>[] list = new ArrayList[N];

        HashMap<String, Integer> idxs = new HashMap<>();
        st = new StringTokenizer(bf.readLine());

        for(int i = 0; i < N; i++) {
            idxs.put(st.nextToken(), i);
            list[i] = new ArrayList<>();
        }

        int M = Integer.parseInt(bf.readLine());
        String[] cities = bf.readLine().split(" ");

        int K = Integer.parseInt(bf.readLine());

        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(bf.readLine());

            String type = st.nextToken();

            String s = st.nextToken();
            String e = st.nextToken();

            int pay = Integer.parseInt(st.nextToken());
            int sIdx = idxs.get(s);
            int eIdx = idxs.get(e);

            list[sIdx].add(new Node(eIdx, pay, type));
            list[eIdx].add(new Node(sIdx, pay, type));
        }

        double result1 = R;
        double result2 = 0;
        for(int i = 0; i < M - 1; i ++) {
            int start = idxs.get(cities[i]);
            int end = idxs.get(cities[i+1]);
            result1 += dijkstra1(N, list, start, end);
            result2 += dijkstra2(N, list, start, end);
        }

        if(result1 < result2) {
            System.out.println("Yes");
        } else {
            System.out.println("No");
        }

    }

    public static double dijkstra2(int N, List<Node>[] list, int start, int end) {
        double[] dist = new double[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N];
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));

        dist[start] = 0;
        pq.offer(new double[] {start, dist[start]});

        while(!pq.isEmpty()) {
            double[] curr = pq.poll();
            int currIdx = (int) curr[0];
            double currDist = curr[1];

            if(currIdx == end) break;
            if(visited[currIdx]) continue;
            visited[currIdx] = true;

            for(Node n : list[currIdx]) {
                double pay = n.pay;

                if(pay + currDist < dist[n.to]) {
                    dist[n.to] = pay + currDist;
                    pq.offer(new double[] {n.to, dist[n.to]});
                }
            }
        }

        return dist[end];
    }

    public static double dijkstra1(int N, List<Node>[] list, int start, int end) {
        double[] dist = new double[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] visited = new boolean[N];
        PriorityQueue<double[]> pq = new PriorityQueue<>((a, b) -> Double.compare(a[1], b[1]));

        dist[start] = 0;
        pq.offer(new double[] {start, dist[start]});

        while(!pq.isEmpty()) {
            double[] curr = pq.poll();
            int currIdx = (int) curr[0];
            double currDist = curr[1];

            if(currIdx == end) break;
            if(visited[currIdx]) continue;
            visited[currIdx] = true;

            for(Node n : list[currIdx]) {
                double pay = n.pay;

                if(zero.contains(n.type)) {
                    pay = 0;
                }

                if(half.contains(n.type)) {
                    pay = pay * 0.5;
                }

                if(pay + currDist < dist[n.to]) {
                    dist[n.to] = pay + currDist;
                    pq.offer(new double[] {n.to, dist[n.to]});
                }
            }
        }

        return dist[end];
    }
}
