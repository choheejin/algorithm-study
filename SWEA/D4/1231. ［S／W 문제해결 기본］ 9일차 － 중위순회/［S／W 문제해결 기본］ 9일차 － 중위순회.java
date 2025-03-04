import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {	
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(bf.readLine());

			char[] dict = new char[N + 1];
			List<Integer>[] list = new ArrayList[N + 1];
			
			for(int i = 1; i <= N; i++) {
				list[i] = new ArrayList<>();
			}
			
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());	
			
				
				int num = Integer.parseInt(st.nextToken());
				dict[num] = st.nextToken().charAt(0);
				
				while (st.hasMoreTokens()) {
					list[num].add(Integer.parseInt(st.nextToken()));
				}
			}
			
			sb.append("#").append(tc).append(" ");
			inOrderVisit(1, list, dict);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void inOrderVisit(int node, List[] list, char[] dict) {
		if(list[node].size() >= 1) {
			inOrderVisit((int) list[node].get(0), list, dict);
		}
		
		sb.append(dict[node]);
		
		if(list[node].size() >= 2) {
			inOrderVisit((int) list[node].get(1), list, dict);
		}
	}
}