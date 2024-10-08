import java.util.*;

// 1. Enter 인 경우, in에 넣어준다. uid가 키
// 2. Leave 인 경우, out에 넣어준다. uid가 키
// 3. Change인 경우, 

class Solution {
    static String IN = "님이 들어왔습니다.";
    static String OUT = "님이 나갔습니다.";
    
    public String[] solution(String[] record) {
        
        HashMap<String, String> in = new HashMap<>();
        Queue<String> output = new ArrayDeque<>();
        
        for(int i = 0; i < record.length; i++) {
            String[] cmds = record[i].split(" ");
            if(cmds[0].equals("Enter")) {
                in.put(cmds[1], cmds[2]);
                output.offer(cmds[1] + ":" + IN);
            }
            else if(cmds[0].equals("Leave")) {
                output.offer(cmds[1] + ":" + OUT);
            }
            else if(cmds[0].equals("Change")) {
                in.put(cmds[1], cmds[2]);           
            }
        }
        

        String[] answer = new String[output.size()];
        int cnt = 0;
        while(!output.isEmpty()) {
            String[] cmds = output.poll().split(":");
            String nick = in.get(cmds[0]);
            answer[cnt++] = nick + cmds[1];
        }
                
        return answer;
    }
}