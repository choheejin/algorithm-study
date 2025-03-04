import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	static List<Integer>[] tree;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(bf.readLine());
			tree = new ArrayList[V + 1];
			
			for(int i = 1; i <= V; i++) {
				tree[i] = new ArrayList();
			}
			
			
			for(int i = 0; i < E; i++) {
				int parent = Integer.parseInt(st.nextToken());
				int child = Integer.parseInt(st.nextToken());
				
				tree[parent].add(child);
			}
			
			int resultIdx = -1;
			int result = Integer.MAX_VALUE;
			for(int i = 1; i <= V; i++) {
				if(findSubtreeSize(i, A) && findSubtreeSize(i, B)) {
					int tmp = countNodes(i);
					
					if(result > tmp) {
						resultIdx = i;
						result = tmp;
					}
				}
			}
			sb.append("#").append(tc).append(" ").append(resultIdx).append(" ").append(result).append("\n");
		}
		System.out.println(sb);
	}
	
	public static boolean findSubtreeSize(int node, int target) {
	    if (node == target) {
	        return true;
	    }

	    for (int child : tree[node]) {
	        if(findSubtreeSize(child, target)) {
	        	return true;
	        }
	    }

	    return false;
	}

	public static int countNodes(int node) {
	    int cnt = 1; // 현재 노드 포함
	    for (int child : tree[node]) {
	        cnt += countNodes(child); // 서브트리 크기 누적
	    }
	    return cnt;
	}
	
}