import java.util.*;

public class Main {

    public static void dfs(List<List<Integer>> graph, int v, boolean[] visited) {
        visited[v] = true;
        System.out.print(v + " ");
        for (int i : graph.get(v)) {
            if (!visited[i]) {
                dfs(graph, i, visited);
            }
        }
    }

    public static void bfs(List<List<Integer>> graph, int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited[start] = true;
        
        while (!queue.isEmpty()) {
            int v = queue.poll();
            System.out.print(v + " ");
            for (int i : graph.get(v)) {
                if (!visited[i]) {
                    queue.add(i);
                    visited[i] = true;
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // Number of nodes
        int m = sc.nextInt(); // Number of edges
        int v = sc.nextInt(); // Starting node
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        
        boolean[] visitedDfs = new boolean[n + 1];
        boolean[] visitedBfs = new boolean[n + 1];
        
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph.get(x).add(y);
            graph.get(y).add(x);
        }
        
        for (int i = 1; i <= n; i++) {
            Collections.sort(graph.get(i));
        }
        
        dfs(graph, v, visitedDfs);
        System.out.println();
        bfs(graph, v, visitedBfs);
    }
}