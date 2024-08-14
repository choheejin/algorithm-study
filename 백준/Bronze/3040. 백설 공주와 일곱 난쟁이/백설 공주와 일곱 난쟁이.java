import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	// 조합 문제 -> 난쟁이의 순서에 대해선 관심이 없고, 최종 합에만 관심이 있기 때문
	// 9개중 2개를 뽑는다. 만약 전체 합에서 2개 합을 뺀게 100이라면 출력.
	
	static int[] arr, fake;
	static int total;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		arr = new int[9];
		fake = new int[2];
		total = 0;
		for(int i = 0; i < 9; i++) {
			arr[i] = Integer.parseInt(bf.readLine());
			total += arr[i];
		}
		
		combi(0, 0, 0);
	}
	
	private static void combi(int cnt, int start, int totalFake) {
		if(cnt == 2) {
			if(total - totalFake == 100) print();
			return;
		}
		
		for(int i = start; i < 9; i++) {
			fake[cnt] = arr[i];
			combi(cnt+1, i+1, totalFake + fake[cnt]);
		}
	}
	
	private static void print() {
		int cnt = 0;
		for(int i = 0; i < 9; i++) {
			if(cnt < 2 && arr[i] == fake[cnt]) {
				cnt++;
				continue;
			} 
			sb.append(arr[i]).append("\n");
		}
		System.out.println(sb);
	}
}