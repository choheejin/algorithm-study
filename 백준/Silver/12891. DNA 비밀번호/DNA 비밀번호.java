import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Main {
	static HashMap<Character, Integer> keyword;
	static int[] minCnt, cnts;
	static char[] inputs;
	static int S, P, result;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(bf.readLine());
		S = Integer.parseInt(st.nextToken());
		P = Integer.parseInt(st.nextToken());

		inputs = bf.readLine().toCharArray();
		
		st = new StringTokenizer(bf.readLine());
		minCnt = new int[4];
		for(int i = 0; i < 4; i ++) {
			minCnt[i] = Integer.parseInt(st.nextToken());
		}

		keyword = new HashMap<>();
		init();
		makePw();
		System.out.println(result);
	}
	
	private static void init() {
		keyword.put('A', 0);
		keyword.put('C', 1);
		keyword.put('G', 2);
		keyword.put('T', 3);
	}
	
	private static void makePw() {
		cnts = new int[4];
		int cnt = 0;
		for(int i = 0; i < S; i ++) {
			cnts[keyword.get(inputs[i])] += 1;
			cnt++;
			if(cnt == P) {
				if(check()) {
					result++;
				}
				cnts[keyword.get(inputs[i-P+1])] -= 1;
				cnt--;
			}
		}
	}
	
	private static boolean check() {
		for(int i = 0; i < 4; i++) {
			if(minCnt[i] > cnts[i]) return false;
		}
		return true;
	}
}