import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	// 1. 자신의 부모를 저장하는 인덱스 배열 생성
	// 2. findSet() 을 통해 집합 합치기를 할 때, 루트 노드(대표자)를 저장할 수 있도록 한다.
	
	static int N, M;
	static int[] parents;
	
	
	public static void main(String[] args) throws Exception {
		StringBuilder sb = new StringBuilder();
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		for(int tc =1; tc <= T; tc ++) {
			sb.append("#").append(tc).append(" ");
			
			StringTokenizer st = new StringTokenizer(bf.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			parents = new int[N+1];
			for(int i = 0; i < M; i++) {
				st = new StringTokenizer(bf.readLine());
				String cmd = st.nextToken();
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				switch (cmd) {
					case "0":
						union(a, b);
						break;
					case "1":
						if(isSameSet(a, b)) sb.append("1");
						else sb.append("0");
						break;
				}
			}
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	static boolean isSameSet(int a, int b) {
		return findSet(a) == findSet(b);
	}
	
	static int findSet(int a) {
		if(0 == parents[a]) return a;
		return parents[a] = findSet(parents[a]);
	}
	
	static void union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA == rootB) return;
		parents[rootB] = rootA;
	}
}