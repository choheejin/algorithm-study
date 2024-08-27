import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	// 조합
	static int L, C;
	static char[] vowels = {'a', 'e', 'i', 'o', 'u'};
	static char[] alpha, selectedAlpha;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		L = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(bf.readLine());
		alpha = new char[C];
		selectedAlpha = new char[L];
		
		for(int c = 0; c < C; c++) {
			alpha[c] = st.nextToken().charAt(0);
		}
		
		Arrays.sort(alpha);
		combi(0, 0, 0, 0);
		System.out.println(sb);
	}
	
	static void combi(int start, int cnt, int vowelCnt, int otherCnt) {
		if(cnt == L) {
			if(vowelCnt > 0 && otherCnt > 1) print();
			return;
		}
		
		for(int i = start; i < C; i++) {
			selectedAlpha[cnt] = alpha[i];
			if(selectedAlpha[cnt] == 'a' || selectedAlpha[cnt] == 'e' || selectedAlpha[cnt] == 'i' || selectedAlpha[cnt] == 'o' || selectedAlpha[cnt] == 'u') {
				combi(i + 1, cnt + 1, vowelCnt + 1, otherCnt);
			} else {
				combi(i + 1, cnt + 1, vowelCnt, otherCnt + 1);
			}
		}
	}
	
	static void print() {
		for(int i = 0; i < L; i++) {
			sb.append(selectedAlpha[i]);
		}
		sb.append("\n");
	}
}