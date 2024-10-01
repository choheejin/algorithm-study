import java.util.*;

class Stage implements Comparable<Stage> {
    int N;
    float notClearPeople; // 스테이지에 도달하였으나 클리어 못한 선수
    float arrivalPeople; // 스테이지에 도달한 선수
    float ratio;
    
    public Stage(int n, int[] stages) {
        this.N = n;
        init(n, stages);
        this.ratio = calculate();
    }
    
    public float calculate() {
        if(this.arrivalPeople == 0) return 0;
        return this.notClearPeople / this.arrivalPeople;
    }
    
    public void init(int n, int[] stages) {
        for(int stage: stages) {
            // 스테이지에 도달하였으나 클리어는 못한 선수
            if(n == stage) {
                this.notClearPeople++;
                this.arrivalPeople++;
            }
            else if(stage > n) {
                this.arrivalPeople++;
            }
        }
    }
    
    public int compareTo(Stage o) {
        if(this.ratio == o.ratio) {
            // 실패율이 같다면, 작은번호의 스테이지가 먼저
            if(this.N < o.N) {
                return -1;
            }
            else {
                return 1;
            }
        }
        return Float.compare(o.ratio, this.ratio);
    }
    
}

class Solution {
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        List<Stage> list = new ArrayList<>();
        
        for(int n = 1; n <= N; n++){
            Stage stage = new Stage(n, stages);
            list.add(stage);
        }
        
        Collections.sort(list);
                
        for(int i = 0; i < N; i++) {
            answer[i] = list.get(i).N;
        }
        
        return answer;
    }
}