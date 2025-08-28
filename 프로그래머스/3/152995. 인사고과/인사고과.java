import java.util.*;

class Solution {
    static int len = 0;
    static int[] A;
    static int[] B;
    
    static class Emp implements Comparable<Emp> {
        int a, b, incentive;
        boolean isWan;
        
        public Emp (int a, int b, boolean isWan) {
            this.isWan = isWan;
            this.a = a;
            this.b = b;
            this.incentive = a + b;
        }
        
        public int compareTo(Emp o) {
            if(this.a == o.a) return Integer.compare(o.b, this.b);
            return Integer.compare(this.a, o.a);
        }
    }
    
    public int solution(int[][] scores) {
        int answer = 0;
        len = scores.length;
        
        ArrayList<Emp> list = new ArrayList();

        for(int i = 0; i < len; i++) list.add(new Emp(scores[i][0], scores[i][1], i==0));
        
        Collections.sort(list);

        ArrayList<Emp> possible = new ArrayList();
        int maxB = -1;
        for(int i = len - 1; i >= 0; i--) {
            if(list.get(i).b < maxB) {
                if(list.get(i).isWan) return -1;
                continue;
            }
            
            maxB = Math.max(maxB, list.get(i).b);
            possible.add(list.get(i));
        }
        
        
        // 석차 계산
        possible.sort((x, y) -> Integer.compare(y.incentive, x.incentive));
        
        int cnt = 1;        
        int preInc = possible.get(0).incentive;
        int preIdx = 0;
        
        if(possible.get(0).isWan) return 1;
        
        for(int i = 1; i < possible.size(); i++) {
            if(preInc != possible.get(i).incentive) {
                cnt = cnt + (i - preIdx);
                preInc = possible.get(i).incentive;
                preIdx = i;
            }
            
            if(possible.get(i).isWan) {
                return cnt;
            }
        }
        
        return -1;
    }    
}