import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/***
 * 메모리: 93,396 KB, 시간: 2,045 ms, 코드길이: 2,403 Bytes
 * @author SSAFY
 *
 */
public class Solution {
	static int cnt;
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
            
            int result = 0;
            for(int i = 1; i <= N; i++) {
            	cnt = 0;
                getSmallPeople(i, new boolean[N + 1]);
                getTallPeople(i, new boolean[N + 1]);
                result += cnt-1 == N ? 1 : 0;
            }

            sb.append("#").append(tc).append(" ").append(result).append("\n");
        }

        System.out.println(sb);
    }
    
    static void getTallPeople(int from, boolean[] visited) {   	
    	visited[from] = true;    	
    	cnt++;
    	for(int i = 1; i <= N; i++) {
    		if(visited[i]) continue;
        	if(adjMatrix[from][i] != 1) continue;
        	getTallPeople(i, visited);
        }
    	
    }
    
    static void getSmallPeople(int from, boolean[] visited) {
    	visited[from] = true;    	
    	cnt++;
    	for(int i = 1; i <= N; i++) {
    		if(visited[i]) continue;
        	if(adjMatrix[i][from] != 1) continue;
        	getSmallPeople(i, visited);
        }
    	
    }
}