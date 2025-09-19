import java.util.*;

class Solution {
    // 수포자들의 답안지 생성 후, 비교하기
    public int[] solution(int[] answers) {
        List<Integer> answer = new ArrayList();
        
        int n = answers.length;
        
        int[] p1 = p1(n);
        int[] p2 = p2(n);
        int[] p3 = p3(n);
        
        int s1 = 0;
        int s2 = 0;
        int s3 = 0;
        for(int i = 0; i < n; i++) {
            if(answers[i] == p1[i]) s1++;
            if(answers[i] == p2[i]) s2++;
            if(answers[i] == p3[i]) s3++;
        }
        
        int maxValue = Math.max(s1, Math.max(s2, s3));
        
        if(s1 == maxValue) answer.add(1);
        if(s2 == maxValue) answer.add(2);
        if(s3 == maxValue) answer.add(3);
        
        // System.out.println(Arrays.toString(p1(n)));
        // System.out.println(Arrays.toString(p2(n)));
        // System.out.println(Arrays.toString(p3(n)));
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int[] p3(int n) {
        int[] dx = new int[] {3, 1, 2, 4, 5};
        int[] p3 = new int[n];

        int idx = 0;
        for(int i = 0; i < n - 1; i+=2) {
            if(idx >= 5) {
                idx = idx % 5;
            }
            
            p3[i] = dx[idx];
            p3[i+1] = dx[idx];
            idx++;
        }
        
        if(n % 2 != 0) {
            if(idx >= 5) {
                idx = idx % 5;
            }
            
            p3[n-1] = dx[idx];
        }

        return p3;
    }

    
    
    public int[] p2(int n) {
        int[] p2 = new int[n];
        // 짝수 일 때,
        int f = 1;
        for(int i = 0; i < n - 1; i+=2) {
            p2[i] = 2;
            f = f == 2 ? 3 : f;
            p2[i+1] = f % 5 == 0 ? 5 : f % 5;
            if(f % 5 == 0) {
                f = 1;
            } else {
                f++;
            }
        }

        if(n % 2 != 0) {
            p2[n-1] = 2;
        }
        
        return p2;
    }
    
    public int[] p1 (int n) {
        int[] p1 = new int[n];
        int f = 1;
        for(int i = 0; i < n; i++) {
            if(f >= 6) {
                f = f % 6 + 1;
            }
            
            p1[i] = f++;
        }
        
        return p1;
    }
    
}