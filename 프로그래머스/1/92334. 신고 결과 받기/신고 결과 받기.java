import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        
        HashMap<String, Integer> idLog = new HashMap<>();
        
        // 총 신고처리 DB
        HashMap<String, Integer> reportLog = new HashMap<>();
        
        // 한 아이디가 신고한 아이디들
        HashMap<String, HashSet> reportResult = new HashMap<>();
        
        // 중복 제거
        for(int i = 0; i < report.length; i++) {
            reportLog.put(report[i], reportLog.getOrDefault(report[i], 0) + 1);
        }
        
        // 중복이 제거된 유저 신고 기록들
        for(String key: reportLog.keySet()) {
            String[] cmds = key.split(" ");
            
            idLog.put(cmds[1], idLog.getOrDefault(cmds[1], 0) + 1);
        }
        
        
        for(String key: reportLog.keySet()) {
            String[] cmds = key.split(" ");
            HashSet<String> tmp = new HashSet<>();
            
            // 이미 신고한 전적이 있다면 키가 존재할 것
            if(reportResult.containsKey(cmds[0])) {
                tmp = reportResult.get(cmds[0]);
            }
            
            if(idLog.getOrDefault(cmds[1], 0) >= k) tmp.add(cmds[1]);
            reportResult.put(cmds[0], tmp);
        }
        
        for(int i = 0; i < id_list.length; i++) {

            // 이미 신고한 전적이 있다면 키가 존재할 것
            if(reportResult.containsKey(id_list[i])) {
                answer[i] = reportResult.get(id_list[i]).size();
            }
        }
                
        return answer;
    }
}