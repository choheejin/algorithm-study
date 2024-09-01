import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static int N, M, V;
    static boolean[] visited;
    static List<Integer>[] adjList;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        V = Integer.parseInt(st.nextToken());
        adjList = new List[N+1];

        for(int i = 0; i <= N; i++) adjList[i] = new ArrayList<>();

        for(int i = 0; i < M ; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from].add(to);
            adjList[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(adjList[i]);
        }

        visited = new boolean[N+1];
        dfs(V);
        sb.append("\n");
        visited = new boolean[N+1];
        bfs(V);
        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int from = q.poll();
            sb.append(from).append(" ");

            for(int to : adjList[from]) {
                if(visited[to]) continue;
                visited[to] = true;
                q.offer(to);
            }
        }
    }

    static void dfs(int from) {
        sb.append(from).append(" ");
        visited[from] = true;

        for(int to: adjList[from]) {
            if(visited[to]) continue;
            dfs(to);
        }
    }
}