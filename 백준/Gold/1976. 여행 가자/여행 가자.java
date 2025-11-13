import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	static int[] unf;
	
	public static int Find(int v) {
		if(unf[v] == v) return v;
		else return unf[v] = Find(unf[v]);
	}
	
	public static void Union(int a, int b) {
		int fa = Find(a);
		int fb = Find(b);
		
		if(fa != fb) unf[fa] = fb;
	}
	
	public static void main(String[] args) throws Exception {

		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(bf.readLine());
		int M = Integer.parseInt(bf.readLine());
	
		unf = new int[N];
		
		for(int i = 0; i < N; i++) unf[i] = i;
	
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(bf.readLine());
			for(int j = 0; j < N; j++) {
				int con = Integer.parseInt(st.nextToken());
				
				if(con == 1) Union(i, j);
			}
		}

		StringTokenizer st = new StringTokenizer(bf.readLine());

		int fa = Find(Integer.parseInt(st.nextToken()) - 1);
		for(int j = 1; j < M; j++) {
			int fb = Find(Integer.parseInt(st.nextToken()) - 1);
			
			if(fa != fb) {
				 System.out.println("NO");
				 return;
			}
		}
		
		 System.out.println("YES");

	}
}
