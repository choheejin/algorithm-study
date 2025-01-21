import java.util.*;
import java.io.*;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		
		char[] stmt = bf.readLine().toCharArray();
		
		Stack<Character> stack = new Stack<>();
		
		String result = "";
		
		for(char c : stmt) {
			// 숫자
			if(c >= 'A' && c <= 'Z') {
				result += c;
			}
			else {
				if(c == '(') {
					stack.push(c);
				}
				else if(c == ')') {
					while(!stack.isEmpty() && stack.peek() != '(') {
						result += stack.pop();
					}
					if(!stack.isEmpty()) {						
						stack.pop(); // 괄호 버리기
					}
				}
				else {
					while(!stack.isEmpty() && change(stack.peek()) >= change(c)) {
						result += stack.pop();
					}
					stack.push(c);
				}
			}
		}
		
		while(!stack.isEmpty()) {
			result += stack.pop();
		}

		System.out.println(result);
		
	}

	
	public static int change(char c) {
		if(c == '+' || c == '-') return 1;
		if(c == '/' || c == '*') return 2;
		return 0; // stack에 '('가 들어올 수 있다는 것에 유의해야 한다.
	}
	
}