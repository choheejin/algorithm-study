import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.*;

public class Main {
    static int T, N, cnt;
    static boolean[] visited;
    static boolean[][] arr;
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(bf.readLine());

        for(int t = 0; t < T; t++) {
            N = Integer.parseInt(bf.readLine());

            arr = new boolean[N+1][N+1];
            visited = new boolean[N+1];
            StringTokenizer st = new StringTokenizer(bf.readLine());
            for(int i = 1; i <= N; i++) {
                int to = Integer.parseInt(st.nextToken());
                arr[i][to] = true;
            }

            cnt = 0;
            for(int i = 1; i <= N; i++) {
                if(visited[i]) continue;
                bfs(i);
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }

    public static void bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);

        while (!q.isEmpty()) {
            int from = q.poll();
            visited[from] = true;

            for(int to = 1; to <= N; to++) {
                if(visited[to]) continue;
                if(arr[from][to]) {
                    q.offer(to);
                }
            }
        }

        cnt++;
    }
}