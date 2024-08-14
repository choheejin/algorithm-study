import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 조합으로 푸는 문제 -> 수의 조합에만 신경 쓰며, 순서에는 관심이 없기 때문.
	// 조합이 완성되기 전, 이미 딜러의 숫자를 넘으면 return
	// 조합이 완성되었을 때, 현재 조합의 총 합이 기존에 저장된 총합보다 크면 교체.
	
	static int N, M, result;
	static int[] cards;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		cards = new int[N];
		st = new StringTokenizer(bf.readLine());
		for(int i = 0; i < N; i++) {
			cards[i] = Integer.parseInt(st.nextToken());
		}
		
		combi(0, 0, 0);
		System.out.println(result);
	}
	
	private static void combi(int cnt, int start, int total) {
		if(total > M) return;
		
		if(cnt == 3) {
			if(result < total) result = total;
			return;
		}
		
		for(int i = start; i < N; i++) {
			combi(cnt + 1, i + 1, total + cards[i]);
		}
	}
}