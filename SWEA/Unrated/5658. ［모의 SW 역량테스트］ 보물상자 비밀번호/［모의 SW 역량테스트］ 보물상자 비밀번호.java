import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Solution {
	
	static int N, K;
	
	static TreeSet<Integer> set;
	
	static char[] rotate, origin;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(bf.readLine());
		
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			int len = N/4;
			set = new TreeSet<>((o1, o2) -> Integer.compare(o2, o1));

			// 원본배열 받아옴
			origin = bf.readLine().toCharArray();			
			
			for(int l = 0; l < len; l++) {
				rotate = new char[N]; // 회전시키기
				System.arraycopy(origin, 0, rotate, 1, N-1);
				rotate[0] = origin[N-1];
				for(int i = 0; i < N; i+=len) {
					String hex = "";
					for(int j = 0; j < len; j++) {
						hex += String.valueOf(rotate[i+j]);
					}
					
					set.add(Integer.parseInt(hex, 16));
				}
				System.arraycopy(rotate, 0, origin, 0, N);
			}

			Iterator<Integer> iter = set.iterator();
			
			int cnt = 1;
			while(iter.hasNext()) {
				int result = iter.next();
				if(cnt == K) {
					sb.append("#").append(tc).append(" ").append(result).append("\n");
					break;
				}
				cnt++;
			}
		}

		System.out.println(sb);
	}
}