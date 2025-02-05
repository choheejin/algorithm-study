import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		int t = Integer.parseInt(bf.readLine());
		StringBuilder sb = new StringBuilder();
	
		
		for(int tc = 0; tc < t; tc++) {
			List<Integer> list = new LinkedList<>();
			int n = Integer.parseInt(bf.readLine());
			int flag = 0;
			int result = n / 2 + 1;
			sb.append(result + "\n");
			
			int cnt = n / 10;
			
			if(n % 10 != 0) {
				cnt += 1;
			}
						
			for(int j = 0; j < cnt; j++) {
				StringTokenizer st = new StringTokenizer(bf.readLine(), " ");
				
				int len = st.countTokens();
				for(int i = 1 ; i <= len; i++) {
					int num = Integer.parseInt(st.nextToken());
					int idx = Collections.binarySearch(list, num);
					
					if(idx < 0) {
						idx = -idx - 1;
					}

					list.add(idx, num);
					
					if((10 * j + i) % 2 != 0) {
						int mid = list.get((10 * j + i)/2);
						sb.append(mid).append(" ");
						flag++;
					}
				}
				if(flag == 10) {					
					sb.append("\n");
					flag = 0;
				}
			}
			sb.append("\n");
		}		
		System.out.println(sb);
	}
}