import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {	
	static StringBuilder sb = new StringBuilder();
	static int N;
	static char[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		for(int tc = 1; tc <= 10; tc++) {
			N = Integer.parseInt(bf.readLine());
			arr = new char[N + 1];
			
			for(int i = 1; i <= N; i++) {
				arr[i] = bf.readLine().split(" ")[1].charAt(0);
			}
			
			sb.append("#").append(tc).append(" ");
			inOrder(1);
			sb.append("\n");
		}
		System.out.println(sb);
	}
	
	public static void inOrder(int node) {
		if(node > N) return;
		
		inOrder(node * 2);
		sb.append(arr[node]);
		inOrder(node * 2 + 1);
	}
}