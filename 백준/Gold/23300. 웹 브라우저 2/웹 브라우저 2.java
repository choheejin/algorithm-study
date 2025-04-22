import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static Stack<Integer> backward = new Stack<>();
	static Stack<Integer> frontward = new Stack<>();
	static int curr = -1, pre = -1;
	
	public static void main(String[] args) throws Exception {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(bf.readLine());
		
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		
			
		for(int i = 0; i < Q; i++) {
			st = new StringTokenizer(bf.readLine());
			
			while(st.hasMoreTokens()) {
				String op = st.nextToken();
				if(op.equals("B")) {
					goBackward();
				} else if(op.equals("F")) {
					goFrontward();
				} else if(op.equals("A")) {
					int num = Integer.parseInt(st.nextToken());
					access(num);
				} else if(op.equals("C")) {
					compressBackward();
				}
			}
		}
		
		sb.append(curr).append("\n");
		
		if(backward.isEmpty()) {
			sb.append("-1\n");
		} else {
			for(int i = backward.size() - 1; i >= 0 ; i--) {
				sb.append(backward.get(i)).append(" ");
			}
			sb.append("\n");
		}
		
		if(frontward.isEmpty()) {
			sb.append("-1\n");
		} else {
			for(int i = frontward.size() - 1; i >= 0 ; i--) {
				sb.append(frontward.get(i)).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
	
	
	public static void compressBackward() {
		if(backward.size() < 2) return;
		
		Stack<Integer> newStack = new Stack<>();
		
		int len = backward.size() - 1;
		int target = backward.get(0);
		int num = 1;
		for(int i = 1; i <= len; i++) {
			if(target == backward.get(i)) {
				num++;
			}
			else {
				newStack.push(backward.get(i-1));
				target = backward.get(i);
				num = 1;
			}
		}
		
		newStack.push(backward.get(len));
		
		backward = newStack;
	}
	
	public static void goBackward() {
		if(backward.isEmpty()) {
			return;
		}
		
		frontward.push(curr);
		curr = backward.pop();
	}
	
	public static void goFrontward() {
		if(frontward.isEmpty()) {
			return;
		}
		
		backward.push(curr);
		curr = frontward.pop();
	}
	
	public static void access(int num) {
		frontward = new Stack<>();
		
		pre = curr;
		curr = num;
		
		if(pre == -1) {
			return;
		}
		
		backward.push(pre);
	}
}
