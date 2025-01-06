import java.util.*;

class Solution {
    public static int posM, posS;
    public static int lenM, lenS;
    public static int startM, startS;
    public static int endM, endS;
    
    // 초기화 함수
    public static void init(String video_len, String op_start, String op_end) {
        int[] lenTime = split(video_len);
        int[] startTime = split(op_start);
        int[] endTime = split(op_end);
        
        lenM = lenTime[0];
        lenS = lenTime[1];

        startM = startTime[0];
        startS = startTime[1];
        
        endM = endTime[0];
        endS = endTime[1];
    }
    
    // 문자열 -> {분,초} 자름
    public static int[] split(String start) {
        String[] op = start.split(":");
        
        int[] nums = new int[2];
        
        nums[0] = Integer.valueOf(op[0]);
        nums[1] = Integer.valueOf(op[1]);
     
        return nums;
    }    
    
    // 분, 초 맞게 변환
    public static int[] convert(int[] times) {        
        if(times[1] < 0) {
            times[0] = times[0] - 1;
            times[1] = times[1] + 60;
        }
        
        int m = times[1] / 60 + times[0];
        int s = times[1] % 60;              
        
        // 남은 시간
        int remain = (lenM * 60 + lenS) - (m * 60 + s);
        int curr = m * 60 + s;
        
        if(remain < 10) {
            m = lenM;
            s = lenS;
        }
        
        if(curr < 10) {
            m = 0;
            s = 0;
        }
        
        return new int[] {m, s};
    }
    
    
    public static int[] jumpOrStay(int[] times) {
        int curr = times[0] * 60 + times[1];
        
        int rangeStart = startM * 60 + startS;
        int rangeEnd = endM * 60 + endS;
        
        if(rangeStart <= curr && curr <= rangeEnd) {
            times[0] = endM;
            times[1] = endS;
        }
        
        return times;
    }
    
    public static String format(int[] times) {
        String result = "";
        
        result += times[0] < 10 ? "0" + String.valueOf(times[0]) : String.valueOf(times[0]);
        result += ":";
        result += times[1] < 10 ? "0" + String.valueOf(times[1]) : String.valueOf(times[1]);
        
        return result;
    }
    
    
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";
        init(video_len, op_start, op_end);
        
        // System.out.println(lenM + ":" + lenS);
        
        // System.out.println(Arrays.toString(split(op_start)));
        
        // System.out.println(format(new int[] {59, 9}));
        
        // System.out.println(Arrays.toString(convert(new int[] {34, 66})));
        
        int idx = 0;
        int cnt = commands.length;
        
        int[] times = split(pos);
        
        while(idx < cnt) {        
            times = jumpOrStay(times);
            
            // 10초 전으로 이동
            if(commands[idx].equals("prev")) {
                times = convert(new int[] {times[0], times[1] - 10});
            }
            
            // 10초 뒤로 이동
            else if(commands[idx].equals("next")) {
                times = convert(new int[] {times[0], times[1] + 10});
            }
            
            times = jumpOrStay(times);
            idx++;
        }
        

        answer = format(times);
        
        return answer;
    }
}