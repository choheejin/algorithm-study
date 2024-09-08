import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int MAX = 100_000;
    static int N, K;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int result = getMin();
        System.out.println(result);
    }

    static int getMin() {
        boolean[] visited = new boolean[MAX + 1];
        Queue<int[]> q = new ArrayDeque<>();
        
        q.offer(new int[] {N, 0});
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            int loc = curr[0], time = curr[1];

            if(loc == K) return time;

            if(loc * 2 <= MAX && !visited[loc * 2]) {
                q.offer(new int[] {loc * 2, time});
                visited[loc*2] = true;
            }
            if(loc - 1 >= 0 && !visited[loc - 1]) {
                q.offer(new int[] {loc - 1, time+1});
                visited[loc - 1] = true;
            }
            if(loc + 1 <= MAX && !visited[loc + 1]) {
                q.offer(new int[] {loc + 1, time+1});
                visited[loc + 1] = true;
            }

        }

        return -1;
    }

}