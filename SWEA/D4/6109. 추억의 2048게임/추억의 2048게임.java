import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, graph[][];
	static String input;
	static StringBuilder sb;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(bf.readLine());
		sb = new StringBuilder();
		for(int tc = 1; tc <= T; tc++) {
			String[] inputs = bf.readLine().split(" ");
			N = Integer.parseInt(inputs[0]);
			input = inputs[1];
			graph = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(bf.readLine());
				for(int j = 0; j < N; j++) {
					graph[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			if(input.equals("up")) {
				gravityU();
			} else if(input.equals("down")) {
				gravityD();
			} else if(input.equals("right")) {
				gravityR();
			} else if(input.equals("left")) {
				gravityL();
			}
			sb.append("#").append(tc).append("\n");
			print();
		}
		System.out.println(sb);
	}
	
	// left;
	static void gravityL() {
		for(int i = 0; i < N; i++) {
			int idx = 0;
			int[] tmp = new int[N];
			int last = graph[i][0]; // 합쳐지는 부분
			for(int j = 1; j < N; j++) {
				if(graph[i][j] == 0) continue;
				if(last != 0 && last == graph[i][j]) {
					tmp[idx] = last + graph[i][j];
					last = 0;
					idx++;
				}
				else if(last == 0) {
					last = graph[i][j];
				}
				else {
					tmp[idx] = last;
					last = graph[i][j];
					idx++;
				}
			}
			tmp[idx] = last;
			graph[i] = tmp;
		}
	}

	
	// right;
	static void gravityR() {
		for(int i = 0; i < N; i++) {
			int idx = N-1;
			int[] tmp = new int[N];
			int last = graph[i][N-1]; // 합쳐지는 부분
			for(int j = N-2; j >= 0; j--) {
				if(graph[i][j] == 0) continue;
				if(last != 0 && last == graph[i][j]) {
					tmp[idx] = last + graph[i][j];
					last = 0;
					idx--;
				}
				else if(last == 0) {
					last = graph[i][j];
				}
				else {
					tmp[idx] = last;
					last = graph[i][j];
					idx--;
				}
			}
			tmp[idx] = last;
			graph[i] = tmp;
		}
	}
	
	// down;
	static void gravityD() {
		for(int i = 0; i < N; i++) {
			int idx = N-1;
			int[] tmp = new int[N];
			int last = graph[N-1][i]; // 합쳐지는 부분
			for(int j = N-2; j >= 0; j--) {
				if(graph[j][i] == 0) continue;
				if(last != 0 && last == graph[j][i]) {
					tmp[idx] = last + graph[j][i];
					last = 0;
					idx--;
				}
				else if(last == 0) {
					last = graph[j][i];
				}
				else {
					tmp[idx] = last;
					last = graph[j][i];
					idx--;
				}
			}
			tmp[idx] = last;
			for(int j = 0; j < N; j++) {
				graph[j][i] = tmp[j];
			}
		}
	}	
	
	// up;
	static void gravityU() {
		for(int i = 0; i < N; i++) {
			int idx = 0;
			int[] tmp = new int[N];
			int last = graph[0][i]; // 합쳐지는 부분
			for(int j = 1; j < N; j++) {
				if(graph[j][i] == 0) continue;
				if(last != 0 && last == graph[j][i]) {
					tmp[idx] = last + graph[j][i];
					last = 0;
					idx++;
				}
				else if(last == 0) {
					last = graph[j][i];
				}
				else {
					tmp[idx] = last;
					last = graph[j][i];
					idx++;
				}
			}
			tmp[idx] = last;
			for(int j = 0; j < N; j++) {
				graph[j][i] = tmp[j];
			}
		}
	}	

	
	static void print() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(graph[i][j]).append(" ");
			}
			sb.append("\n");
		}
	}
}