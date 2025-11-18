import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;


// 유니온파인드
public class Main {
	static int N, M, T, unf[], start;
	static ArrayList<ArrayList<Integer>> lists = new ArrayList<>();;
	
	public static int Find (int v) {
		if(unf[v] == v) return v;
		else return unf[v] = Find(unf[v]);
	}
	
	public static void Union (int a, int b) {
		int fa = Find(a);
		int fb = Find(b);
		
		if(fa != fb) unf[fa] = unf[fb];
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		unf = new int[N+1];
		
		for(int i = 1; i <= N; i++) unf[i] = i;

		st = new StringTokenizer(bf.readLine());
		
		T = Integer.parseInt(st.nextToken());
		
		if(T != 0) {
			start = Integer.parseInt(st.nextToken());
			
			for(int t = 1; t < T; t++) {
				int v = Integer.parseInt(st.nextToken());
				
				Union(start, v);
			}
		}

		for(int m = 0; m < M; m++) {
			st = new StringTokenizer(bf.readLine());
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			int L = Integer.parseInt(st.nextToken());

			if(L != 0) {
				int front = Integer.parseInt(st.nextToken());
				
				list.add(front);
				
				for(int i = 1; i < L; i++) {
					int v = Integer.parseInt(st.nextToken());

					Union(front, v);
					list.add(v);
				}
			}
			lists.add(list);
		}
		
		int result = 0;
		
		for(int i = 0; i < M; i++) {
			if(check(lists.get(i))) result++;
		}
		if(T == 0) {
			System.out.println(M);
		} else {
			System.out.println(result);
		}
//		System.out.println(Arrays.toString(unf));
	}
	
	public static boolean check(ArrayList<Integer> list) {
		for(int v: list) {
			int fv = Find(v);
			int fStart = Find(start);
			
			if(fv == fStart) return false; // 거짓 전파 X
		}
		return true; // 거짓 전파 O
	}
	
	public static void unionAll (ArrayList<Integer> list) {
		for(int v : list) {
			Union(start, v);
		}
	}
}
