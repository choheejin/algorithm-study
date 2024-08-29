import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 양방향 그래프 (위상정렬 아니다.)
    // 깊이가 5이상인 부분 트리..?가 존재하는지 확인하는 문제
    // DFS!! 그래프 타고 들어가는게 5 이상인 경우까지
    
    static class Node {
        int to;
        Node next;
        public Node(int to, Node next) {
            this.to = to;
            this.next = next;
        }
    }
    
    static int N, M;
    static Node[] adjList;
    static boolean[] visited;
    
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        adjList = new Node[N];

        for(int i = 0; i< M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList[from] = new Node(to, adjList[from]);
            adjList[to] = new Node(from, adjList[to]);
        }

        int result = 0;
        visited = new boolean[N];

        for(int from = 0; from < N; from++) {
            if(dfs(from, 1)) {
                result = 1;
                break;
            }
        }
        System.out.println(result);
    }
    
    static boolean dfs(int from, int cnt) {
        if(cnt == 5) return true;

        visited[from] = true;

        for(Node n=adjList[from]; n!=null; n=n.next) {
            if(visited[n.to]) continue;
            if(dfs(n.to, cnt + 1)) return true;
        }

        visited[from] = false;
        return false;
    }
}