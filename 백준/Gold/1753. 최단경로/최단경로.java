import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int vertex;
        int weight;
        Node next;

        public Node(int vertex, int weight, Node next) {
            this.vertex = vertex;
            this.weight = weight;
            this.next = next;
        }
    }

    static int V, E, K;
    static Node[] adjList;
    static boolean[] visited;
    static int[] minDistacne;

    public static void main(String[] args) throws Exception {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(bf.readLine());

        adjList = new Node[V+1];
        visited = new boolean[V+1];
        minDistacne = new int[V+1];

        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, weight, adjList[from]);
        }

        Arrays.fill(minDistacne, Integer.MAX_VALUE);

        getMinDistance(K);

        for(int i = 1; i <= V; i++) {
            String result = minDistacne[i] == Integer.MAX_VALUE ? "INF" : String.valueOf(minDistacne[i]);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    static void getMinDistance(int start) {
        final int INF = Integer.MAX_VALUE;

        // 시작점
        minDistacne[start] = 0;

        for(int i = 1; i <= V; i++) {
            int min = INF;
            int stopOver = -1;

            // 미방문 정점 중에서 최소의 비용을 가진 정점을 선택한다.
            for(int j = 1; j <= V; j++) {
                if(visited[j]) continue;
                if(minDistacne[j] < min) {
                    stopOver = j;
                    min = minDistacne[j];
                }
            }

            // 갈 수 없음
            if(stopOver == -1) break;
            visited[stopOver] = true;

            // 선택한 정점들 중 인접한 정점까지의 최소 비용 갱신하거나 넘어가거나
            for(Node curr = adjList[stopOver]; curr != null; curr = curr.next) {
                // 방문한 정점이면 패스
                if(visited[curr.vertex]) continue;
                if(minDistacne[curr.vertex] > min + curr.weight) {
                    minDistacne[curr.vertex] = min + curr.weight;
                }
            }
        }
    }
}