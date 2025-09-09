import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static ArrayList<Node>[] list;

    static class Node {
        boolean isSheep;
        int to, cost;

        public Node(int to, int cost, boolean isSheep) {
            this.to = to;
            this.cost = cost;
            this.isSheep = isSheep;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        list = new ArrayList[N+1];
        int[] indegree = new int[N+1];
        long[] sum = new long[N+1];

        for (int i = 0; i <= N; i++) list[i] = new ArrayList<>();

        for (int i = 2; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());

            boolean isSheep = st.nextToken().equals("S");
            int cost = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            list[i].add(new Node(to, cost, isSheep));
            indegree[to]++;
            sum[i] = isSheep ? cost : -cost;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) q.add(i);
        }

        while (!q.isEmpty()) {
            int cur = q.poll();
            if (cur == 1) continue;

            Node n = list[cur].get(0);
            int parent = n.to;

            long give = Math.max(0L, sum[cur]);
            sum[parent] += give;

            if (--indegree[parent] == 0) q.add(parent);
        }

        System.out.println(Math.max(0L, sum[1]));
    }
}
