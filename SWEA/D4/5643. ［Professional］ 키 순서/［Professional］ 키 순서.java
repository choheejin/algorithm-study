import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
    // bfs는? 노드의 수만큼 돈다
    // 500개의 노드에 대해 각각 dfs를 두번 돈다.
    // 500*500*2 => 충분한 시간 복잡도

    // 1. 진입차수와 진출차수를 거꾸로 그래프를 하나 더 만들어서
    // 2. 각각의 노드에 대해 해당 그래프를 bfs 돈다.
    //    => 두개의 그래프에 대해 방문한 노드의 개수가 전체 노드와 동일하지 않으면 자신의 키의 순서를 모른다는 뜻

    static int N, M;
    static Node[] adjList, reverseAdjList;


    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }


    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(bf.readLine());
            M = Integer.parseInt(bf.readLine());

            adjList = new Node[N+1];
            reverseAdjList = new Node[N+1];

            for(int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjList[from] = new Node(to, adjList[from]);
                reverseAdjList[to] = new Node(from, reverseAdjList[to]);
            }

            int cnt = 0;

            for(int i = 1; i <= N; i++) {
                cnt += bfs(i, adjList) + bfs(i, reverseAdjList) - 1 == N ? 1 : 0;
            }

            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }


    static int bfs(int start, Node[] adjList) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int from = q.poll();
            cnt++;
            for(Node n = adjList[from]; n != null; n = n.next) {
                if(visited[n.to]) continue;
                q.offer(n.to);
                visited[n.to] = true;
            }
        }
        return cnt;
    }

}