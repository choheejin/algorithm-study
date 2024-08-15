import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Stack;

public class Solution {
    static int T;

    public static void main(String[] args) throws Exception{
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(bf.readLine());
        for(int tc = 1; tc <= T; tc ++) {
            String[] inputs = bf.readLine().split("");
            Stack<String> stack = new Stack<>();
            for(String input: inputs) {
                if(!stack.contains(input)) {
                    stack.push(input);
                } else {
                    stack.remove(stack.indexOf(input));
                }
            }
            if(stack.isEmpty()) {
                sb.append("#").append(tc).append(" Good\n");
            } else {
                Collections.sort(stack);
                sb.append("#").append(tc).append(" ");
                for(String input: stack) {
                    sb.append(input);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }
}