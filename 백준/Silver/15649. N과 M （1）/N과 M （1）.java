import java.util.Scanner;

public class Main {
	static int N, M;
	static int[] numbers;
	static boolean[] isSelected;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		numbers = new int[M];
		isSelected = new boolean[N+1];
		perm(0);
	}
	public static void perm(int cnt) {
		if(cnt == M) {
			print(numbers);
			return;
		}
		for(int i = 1; i <= N; i++) {
			if(isSelected[i]) continue;
			numbers[cnt] = i;
			isSelected[i] = true;
			perm(cnt + 1);
			isSelected[i] = false;
		}
	}
	public static void print(int[] numbers) {
		for(int i = 0; i < M; i++) {
			System.out.print(numbers[i] + " ");
		}
		System.out.println();
	}
}