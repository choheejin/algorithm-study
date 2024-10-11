import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static List<int[]>[] adjList;
	static int limit;
	
	static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		// 각 섬에 대한 유니크 아이디 부여
		
		int idx = 2;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] != 1) continue;
				bfs(i, j, idx++);
			}
		}
		
		limit = idx;
		adjList = new ArrayList[limit];
		
		for(int i = 2; i < idx; i++) {
			adjList[i] = new ArrayList<>();
		}
		
		// 설치 가능한 모든 다리를 건설한다.
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) continue;
				constructBridge(i, j, map[i][j]);
			}
		}
	
		// 최소 신장 트리
		
		System.out.println(kruskal());
	}
	
	public static int kruskal() {
		// 섬 아이디: 2 에서 시작한다.
		PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
		boolean[] visited = new boolean[limit];
		int result = 0;
		
		// 첫 등록 비용은 0
		q.offer(new int[] {2, 0});
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			if(visited[curr[0]]) continue;
			
			visited[curr[0]] = true;
			result += curr[1];
			
			// 방문하지 않은 인접한 노드의 최소 비용 고르기
			for(int j = 0; j < adjList[curr[0]].size(); j++) {
				if(visited[adjList[curr[0]].get(j)[0]]) continue;
				
				q.offer(adjList[curr[0]].get(j));
			}
		}
		
		for(int i = 2; i < limit; i++) {
			if(!visited[i]) return -1;
		}
		
		return result;
	}
	
	
	public static void constructBridge(int x, int y, int id) {
		int nx = x, ny = y;
		int cnt = 0;
		// 상
		while(nx > 0) {
			nx--;
			
			if(map[nx][y] == 0) {
				cnt++;
			}			
			else {
				if(map[nx][y] != id && map[nx][y] >= 2) {
					if(cnt <= 1) break;
					adjList[id].add(new int[] {map[nx][y], cnt});
				}				
				break;
			}
		}
		
		nx = x;
		cnt = 0;

		// 하
		while(nx < N - 1) {
			nx++;
			
			if(map[nx][y] == 0) {
				cnt++;
			}			
			
			else {
				if(map[nx][y] != id && map[nx][y] >= 2) {
					if(cnt <= 1) break;
					adjList[id].add(new int[] {map[nx][y], cnt});
				}				
				break;
			}
		}

		nx = x;
		cnt = 0;
		
		// 좌
		while(ny > 0) {
			ny--;
			
			if(map[x][ny] == 0) {
				cnt++;
			}			
			
			else {
				if(map[x][ny] != id && map[x][ny] >= 2) {
					if(cnt <= 1) break;
					adjList[id].add(new int[] {map[x][ny], cnt});
				}				
				break;
			}
		}
		
		ny = y;
		cnt = 0;

		// 우
		while(ny < M - 1) {
			ny++;
			
			if(map[x][ny] == 0) {
				cnt++;
			}			
			
			else {
				if(map[x][ny] != id && map[x][ny] >= 2) {
					if(cnt <= 1) break;
					adjList[id].add(new int[] {map[x][ny], cnt});
				}				
				break;
			}
		}
	}
	

	public static void bfs(int x, int y, int id) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {x, y});
		
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			map[curr[0]][curr[1]] = id;
			
			for(int i = 0; i < 4; i++) {
				int nx = dx[i] + curr[0], ny = dy[i] + curr[1];
				
				if(nx < 0 || nx >= N || ny < 0 || ny >= M) continue;
				if(map[nx][ny] == 1) q.offer(new int[] {nx, ny});
			}
		}
	}
	
	private static void printArray() {
		for(int i = 0; i < N; i++) {
			System.out.println(Arrays.toString(map[i]));
		}
	}
	
	private static void printList() {
		for(int i = 2; i < adjList.length; i++) {
			for(int j = 0; j < adjList[i].size(); j++) {
				System.out.println(i + ":" + Arrays.toString(adjList[i].get(j)));
			}
		}
	}
}