import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 메모리: 15720 KB, 시간: 112 ms
 */
public class Main {
    // 문제 파악: 부분집합 + BFS 문제

    static int N, minValue;
    static int[] distance;
    static Node[] arrList;
    static boolean[] choice;

    static class Node {
        int to;
        Node next;

        public Node(int to, Node next) {
            this.next = next;
            this.to = to;
        }
    }

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(bf.readLine());

        distance = new int[N + 1];
        choice = new boolean[N + 1];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            distance[i] = Integer.parseInt(st.nextToken());
        }

        arrList = new Node[N + 1];
        for (int from = 1; from <= N; from++) {
            st = new StringTokenizer(bf.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            for (int j = 0; j < cnt; j++) {
                arrList[from] = new Node(Integer.parseInt(st.nextToken()), arrList[from]);
            }
        }
        
        minValue = Integer.MAX_VALUE;
        make(1);
        System.out.println(minValue == Integer.MAX_VALUE ? -1 : minValue);
    }

    public static void make(int idx) {
        if (idx == N+1) {
        	
        	int startA = 0, startB = 0;
        	
        	int cnt = 0;
        	
            for(int v = 1; v <= N; v++) {
                if(choice[v]) {
                    startA = v;
                    cnt++;
                }
            }
            
            if(cnt == 0 || cnt == N) return;
            
            for(int v = 1; v <= N; v++) {
                if(!choice[v]) {
                    startB = v;
                    break;
                }
            }
        
            int resultA = bfs(startA, choice, true);
            if(resultA == -1) return;
            int resultB = bfs(startB, choice, false);
            if(resultB == -1) return;
                
//            System.out.println(resultA + ", " + resultB);
            minValue = Math.min(minValue, Math.abs(resultA - resultB));
            return;
        }

        choice[idx] = true;
        make(idx + 1);
        choice[idx] = false;
        make(idx + 1);
    }

    public static int bfs(int start, boolean[] choice, boolean flag) {
    	boolean[] visited = new boolean[N+1];
    	int cnt = 0;
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int from = q.poll();
            cnt += distance[from];
            for (Node n = arrList[from]; n != null; n = n.next) {
            	if(choice[n.to] != flag) continue;
                if (visited[n.to])
                    continue;
                visited[n.to] = true;
                q.offer(n.to);
            }
        }
        
        for(int i = 1; i <= N; i++) {
        	if(!visited[i] && choice[i] == flag) return -1;
        }
        
        return cnt;
    }
}