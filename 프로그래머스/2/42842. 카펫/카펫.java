class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = {};
                
        for(int i = 1; i * i <= yellow; i++) {
            if(yellow % i == 0) {
                int j = yellow / i;
                
                int total = (i + 2) * (j + 2);
                int outer = total - yellow;
                
                if(outer == brown) return new int[] {j + 2, i + 2};
            }
        }
        
        return new int[] {};
    }
}