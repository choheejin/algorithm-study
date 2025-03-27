import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static char[][] strs;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		strs = new char[R][];
		for(int i = 0; i < R; i++) {
			strs[i] = br.readLine().toCharArray();
		}
		
		
		int start = 0;
		int end = R - 1;
		int cnt = 0;
		
		while(start <= end) {
			int mid = (start + end) / 2;
			
			if(check(mid)) {
				cnt = mid;
				start = mid + 1;
                continue;
			}
			
			end = mid - 1;
		}
		
		System.out.println(cnt);
		
	}
	
	public static boolean check(int r) {
		Set<String> set = new HashSet<>();
		for(int i = 0; i < C; i++) {
			String str = "";
			for(int j = r; j < R; j++) {
				str += strs[j][i];
			}
			if(set.contains(str)) {
				return false;
			}
			set.add(str);
		}
		return true;
	}
}
