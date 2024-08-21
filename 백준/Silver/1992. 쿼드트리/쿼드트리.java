import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N, graph[][];
	static String result;
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(bf.readLine());
		graph = new int[N][N];
		for(int i = 0; i < N; i++) {
			String[] inputs = bf.readLine().split("");
			for(int j = 0; j < N; j++) {
				graph[i][j] = Integer.parseInt(inputs[j]);
			}
		}
		result = "";
		devide(0, 0, N);
		System.out.println(result);
	}
	
	static void devide(int x, int y, int key) {
		int sum = 0;
		for(int i = x, endX = x + key; i < endX; i++) {
			for(int j = y, endY = y + key; j < endY; j++) {
				sum += graph[i][j];
			}
		}
		if(sum == 0) {
			result += "0";
		}
		else if(sum == key * key) {
			result += "1";
		} 
		else {
			int half = key / 2;
			result += "(";
			devide(x, y, half);
			devide(x, y + half, half);
			devide(x + half, y, half);
			devide(x + half, y + half, half);
			result += ")";
		}
	}
}