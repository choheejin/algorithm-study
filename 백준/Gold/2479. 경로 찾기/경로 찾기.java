import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node {
        int to, weight;
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "[ " + to + "," + weight + " ]";
        }
    }

    static HashMap<Integer, String> results = new HashMap<>();

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int N = Integer.parseInt(st.nextToken()); // 개수
        int K = Integer.parseInt(st.nextToken()); // 길이

        int[][] codes = new int[N + 1][K];
        List<Node>[] list = new ArrayList[N + 1];

        for(int i = 1; i <= N; i++) {
            String cmd = bf.readLine();
            for(int j = 0; j < K; j++) {
                codes[i][j] = Integer.parseInt(String.valueOf(cmd.charAt(j)));
            }
            list[i] = new ArrayList<>();
        }

        st = new StringTokenizer(bf.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        // 해밍 거리 초기화
        for(int i = 1; i <= N; i++) {
            for(int j = i + 1; j <= N; j++) {
                int[] from = codes[i];
                int[] to = codes[j];

                int dist = distance(K, from, to);
                list[i].add(new Node(j, dist));
                list[j].add(new Node(i, dist));
            }
        }

        dfs(new boolean[N + 1], list, start, end,  start+ " ");
        if(results.isEmpty()) {
            System.out.println(-1);
            return;
        }
        for(String result: results.values()) {
            System.out.println(result);
            return;
        }
    }

    public static int distance(int K, int[] codeA, int[] codeB) {
        int cnt = 0;
        for(int i = 0; i < K; i++) {
            if(codeA[i] != codeB[i]) {
                cnt++;
            }
        }
        return cnt;
    }

    public static void dfs(boolean[] visited, List<Node>[] list, int n, int end, String way) {
        if(n == end) {
            results.put(way.length(), way);
            return;
        }

        for(Node node: list[n]){
            if(node.weight != 1) continue;
            if(visited[node.to]) continue;
            visited[node.to] = true;
            dfs(visited, list, node.to, end, way + node.to + " ");
        }

    }
}
