import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        
        HashMap<String, String> set = new HashMap();
        
        for(String phone : phone_book) {
            set.put(phone, phone);
        }

        
        for(String phone : phone_book) {
            String part = "";
            
            for(int i = 0; i < phone.length() - 1; i++) {
                part += String.valueOf(phone.charAt(i));
                String exist = set.getOrDefault(part,"");
                if(!exist.isBlank() && !exist.equals(phone)) {
                    return false;
                }
            }
            
            set.put(phone, phone);
        }
        
        return answer;
    }
}