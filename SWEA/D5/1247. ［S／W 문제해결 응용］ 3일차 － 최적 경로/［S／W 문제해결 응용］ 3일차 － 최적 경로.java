import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	// 1. 방문 순서 정함 (중복없는 순열)
	// 2.1. 방문 시작
	// => 바로 이전 방문 노드를 저장해두고, 현재 위치와의 거리를 구해서 매개변수로 현재까지의 이동거리를 넘겨준다.
	// 2.2. 이미 저장된 최소보다 더 큰 경우, 가지치기

	static class Node {
		int x, y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + "]";
		}
		
	}

	static int N, size, minValue;
	static Node nodes[];
	static boolean isVisited[];

	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(bf.readLine());
			size = N + 2;
			nodes = new Node[size];
			isVisited = new boolean[size];
			minValue = Integer.MAX_VALUE;
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for (int i = 0; i < size; i++) {
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				nodes[i] = new Node(x, y);
			}
			visitSomeWhere(0, nodes[0], 0);
			sb.append("#").append(tc).append(" ").append(minValue).append("\n");
		}
		System.out.println(sb);
	}

	static void visitSomeWhere(int cnt, Node pre, int total) {
		if(total > minValue) return;
		// 마지막 고객집을 뽑았을 때 => 즉, 집까지의 거리는 계산되지 않은 상태
		if(cnt == N) {
			int distance = Math.abs(nodes[1].x - pre.x) + Math.abs(nodes[1].y - pre.y);
			minValue = Math.min(minValue, total + distance);
			return;
		}
		
		// 회사와 집은 고정 좌표이므로 순서 뽑기에서 제외된다.
		for (int i = 2; i < size; i++) {
			if(isVisited[i]) continue;
			isVisited[i] = true;
			Node curr = nodes[i];
			int distance = Math.abs(curr.x - pre.x) + Math.abs(curr.y - pre.y);
			visitSomeWhere(cnt+1, curr, total + distance);
			isVisited[i] = false;
		}
	}
}