import java.io.BufferedReader;
import java.util.Scanner;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[] h = new int[N];
			for(int i = 0; i < N; i++) {
				h[i] = sc.nextInt();
			}
			
			boolean isUp = true; // 오르막인지
			int asc = 0, dsc = 0, result = 0;
			
			for(int i = 1; i < N; i++) {
				// 오르막
				if(h[i-1] < h[i]) { 
					// 오르막 -> 오르막
					if(isUp) {
						asc++;
					}
					// 내리막 -> 오르막
					else {
						result += asc * dsc;
						isUp = true;
						asc = 1;
						dsc = 0;
					}
				} 
				// 내리막
				else if(h[i-1] > h[i]) {
					// 오르막 -> 내리막
					if(isUp) {
						isUp = false;
						dsc++;
					}
					// 내리막 -> 내리막
					else {
						dsc++;
					}
				}
			}
			if(!isUp) {
            	result += asc * dsc;
            }
			System.out.printf("#%d %d\n", tc, result);
		}
	}
}