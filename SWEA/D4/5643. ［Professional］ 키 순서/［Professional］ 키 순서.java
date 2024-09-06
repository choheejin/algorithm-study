import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;


public class Solution {
    static int N, M;
    static int[][] adjMatrix;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());

        for(int tc = 1; tc <= T; tc++) {
            N = Integer.parseInt(bf.readLine());
            M = Integer.parseInt(bf.readLine());

            adjMatrix = new int[N+1][N+1];


            for(int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(bf.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adjMatrix[from][to] = 1;
            }

            int cnt = 0;

            for(int i = 1; i <= N; i++) {
                cnt += getTallPeople(i, adjMatrix) + getSmallPeople(i, adjMatrix) - 1 == N ? 1 : 0;
            }

            sb.append("#").append(tc).append(" ").append(cnt).append("\n");
        }

        System.out.println(sb);
    }
    
    static int getTallPeople(int start, int[][] matrix) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int from = q.poll();
            cnt++;
            for(int i = 1; i <= N; i++) {
                if(visited[i]) continue;
                if(matrix[from][i] != 1) continue;
                q.offer(i);
                visited[i] = true;
            }
        }
        return cnt;
    }
    
    static int getSmallPeople(int start, int[][] matrix) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new ArrayDeque<>();
        int cnt = 0;

        q.offer(start);
        visited[start] = true;

        while(!q.isEmpty()) {
            int to = q.poll();
            cnt++;
            for(int from = 1; from <= N; from++) {
                if(visited[from]) continue;
                if(matrix[from][to] != 1) continue;
                q.offer(from);
                visited[from] = true;
            }
        }
        return cnt;
    }
}