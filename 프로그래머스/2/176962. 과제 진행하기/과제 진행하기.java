// Stack 임

import java.util.*;

class Solution {
    class Subject {
        String name;
        int time;
        
        public Subject (String name, int time) {
            this.name = name;
            this.time = time;
        }
        
        @Override
        public String toString() {
            return "name: " + name;
        }
    }
    
    // A : 이전, B: 현재
    public int calculate (String A, String B) {
        // ;; 환장 
        int a = (A.charAt(0) - '0') * 10 + (A.charAt(1) - '0');
        int b = (A.charAt(3) - '0') * 10 + (A.charAt(4) - '0');
        
        int c = (B.charAt(0) - '0') * 10 + (B.charAt(1) - '0');
        int d = (B.charAt(3) - '0') * 10 + (B.charAt(4) - '0');

        
        int total_a_minutes = a * 60 + b;
        int total_b_minutes = c * 60 + d;

        return total_b_minutes - total_a_minutes;
    }
    
    public String[] solution(String[][] plans) {
        ArrayList<String> answer = new ArrayList<>();
        ArrayList<String[]> sorted = new ArrayList<>();
        Stack<Subject> stack = new Stack();
            
        int N = plans.length;
        
        for(int i = 0; i < N; i++) {
            sorted.add(plans[i]);
        }
        
        Collections.sort(sorted, new Comparator<String[]>() {
            @Override
            public int compare (String[] a, String[] b) {
                return a[1].compareTo(b[1]);
            }            
        });
        

        for(int i = 1; i < N; i++) {
            String[] pre = sorted.get(i-1);
            String[] curr = sorted.get(i);
            // System.out.println(calculate(pre[1], curr[1]));
            
            int gap = calculate(pre[1], curr[1]);
            int running = Integer.parseInt(pre[2]);

            if(gap == running) {
                answer.add(pre[0]);
            }
            else if(running > gap) {
                stack.push(new Subject(pre[0], running - gap));
            }
            else {
                // gap 이 크면, (과제 하나 끝남)
                gap = gap - running;
                answer.add(pre[0]);
                
                while(gap > 0 && !stack.isEmpty()) {
                    Subject first = stack.pop();
                    
                    // 멈춘 과제의 남은 시간이 gap 보다 작다
                    if(first.time <= gap) {
                        // 멈춘 과제 하나 끝남
                        gap = gap - first.time;
                        answer.add(first.name);
                        continue;
                    }
                    // 크면, 빼고 다시 넣고
                    else {
                        stack.push(new Subject(first.name, first.time - gap));
                        gap = 0;
                    }

                    
                    System.out.println(stack);

                }
            }
        }
        // 마지막 남은 것들 넣어주기

        answer.add(sorted.get(N-1)[0]);

        while(!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        
        return answer.toArray(String[]::new);
    }
}