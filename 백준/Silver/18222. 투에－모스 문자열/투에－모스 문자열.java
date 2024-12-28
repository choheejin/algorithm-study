import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		long num = Long.parseLong(bf.readLine());
		
		System.out.println(find(num - 1));
	}
	
	public static long find(long x) {
		if(x == 0) {
			return 0;
		}
		else if(x == 1) {
			return 1;
		}
		else if(x % 2 == 0) {
			return find(x/2);
		}
		else {
			return 1 - find(x / 2);
		}
	}
}