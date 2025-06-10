import java.util.*;

class Solution {
    static class Stage implements Comparable<Stage> {
        int idx;
        int cnt;
        double rate;
        
        public Stage (int idx, int cnt) {
            this.idx = idx;
            this.cnt = cnt;
            this.rate = 0.0;
        }
        
        @Override
        public int compareTo(Stage o) {
            if (Double.compare(o.rate, this.rate) == 0) {
                return this.idx - o.idx;
            }

            return Double.compare(o.rate, this.rate);
        }
        
        @Override
        public String toString() {
            return "[" + this.idx + "," + this.cnt + "," + this.rate + "]";
        }
    }
    
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int stageLen = stages.length;
        
        Stage[] list = new Stage[N];
    
        for(int i = 0; i < stageLen; i++) {
            int num = stages[i] - 1;
            if(num == N) continue;
            if(list[num] == null) {
                list[num] = new Stage(num + 1, 1);
            } else {
                list[num].cnt++;
            }
        }
        
        int people = stageLen;
        for(int i = 0; i < N; i++) {
            if(list[i] == null) {
                list[i] = new Stage(i + 1, 0);
                continue;
            }
            list[i].rate = (double) list[i].cnt / people;
            people -= list[i].cnt;
        }
        
        Arrays.sort(list);
        
        
        for(int i = 0; i < N; i++) {
            // System.out.println(list[i]);
            answer[i] = list[i].idx;
        }
        
        return answer;
    }
}