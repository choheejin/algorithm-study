import java.lang.*;
import java.util.*;

class Solution {
    public int solution(int[][] sizes) {
        int maxW = 0, maxH = 0;

        for (int[] s : sizes) {
            int w = Math.max(s[0], s[1]); // 긴 변을 가로
            int h = Math.min(s[0], s[1]); // 짧은 변을 세로
            maxW = Math.max(maxW, w);
            maxH = Math.max(maxH, h);
        }
        return maxW * maxH;
    }

}