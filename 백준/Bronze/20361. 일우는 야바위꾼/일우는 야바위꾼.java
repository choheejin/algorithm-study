import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 1. 입력을 받는다.
	// 2. 스왑을 계속 해준다.
	// 3. 마지막엔 탐색으로 현재 사탕의 위치를 찾는다.
	// 테케 하나당 제한시간 2초 
	// N + K의 시간 복잡도를 가진다. => 300,000 
	static int N, X, K, result, cups[];
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		result = 0;
		cups = new int[N+1];
		cups[X] = 1;
		for(int k = 0; k < K; k++) {
			st = new StringTokenizer(bf.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			
			int tmp = cups[A];
			cups[A] = cups[B];
			cups[B] = tmp;
		}
		
		for(int n = 1; n <= N; n++) {
			if(cups[n] == 1) {
				result = n;
				break;
			}
		}

		System.out.println(result);
	}
}