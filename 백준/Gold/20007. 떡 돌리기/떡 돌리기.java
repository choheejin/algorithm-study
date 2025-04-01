import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int to, dist;
        public Node (int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        List<Node>[] adjList = new ArrayList[N];

        for(int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            adjList[A].add(new Node(B, C));
            adjList[B].add(new Node(A, C));
        }

        int[] dist = search(adjList, N, Y);

        Arrays.sort(dist);

        if(dist[N-1] * 2 > X || dist[N-1] == Integer.MAX_VALUE) {
            System.out.println(-1);
            return;
        }

        int day = 1;
        int acc = 0;

        for(int i = 0; i < N; i++) {
            if(acc + (dist[i] * 2) > X) {
                day++;
                acc = dist[i] * 2;
                continue;
            }
            acc += (dist[i] * 2);
        }
        System.out.println(day);
    }

    public static int[] search(List<Node>[] adjList, int N, int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));

        boolean[] visited = new boolean[N];
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);

        dist[start] = 0;
        pq.offer(new int[]{start, dist[start]});

        while(!pq.isEmpty()) {
            int[] node = pq.poll();
            int currentNode = node[0];
            int currentDist = node[1];


            if(visited[currentNode]) continue;
            visited[currentNode] = true;

            for(Node curr : adjList[currentNode]) {
                if(visited[curr.to]) continue;

                if(dist[curr.to] > currentDist + curr.dist) {
                    dist[curr.to] = currentDist + curr.dist;
                    pq.offer(new int[] {curr.to, dist[curr.to]});
                }
            }
        }

        return dist;
    }
}
