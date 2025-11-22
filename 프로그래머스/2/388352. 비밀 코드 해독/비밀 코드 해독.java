import java.util.*;

// 비트마스킹 + 부분집합
class Solution {
    static int N, M;
    static int[] bits;
    
    public int solution(int n, int[][] q, int[] ans) {
        int answer = 0;
        N = n;
        M = q.length;
        
        bits = new int[M];
        
        for(int i = 0; i < M; i++) {
            for(int num : q[i]) {
                bits[i] = bits[i] | (1 << num);
            }
            // System.out.println(Integer.toBinaryString(bits[i]));
        }

        
        
        return guessCode(1, 0, 0, ans);
    }
    
    public boolean check (int status, int[] ans) {
        for(int i = 0; i < M; i++) {
            // 둘이 같이 있는 부분만 추출하기
            int code = status & bits[i];
            if(Integer.bitCount(code) != ans[i]) {
                return false; // 다른 경우, 코드 잘못 추출함
            }
        }
        
        return true;
    }
    
    public int guessCode (int i, int status, int cnt, int[] ans) {
        // 비밀코드 완성 완료
        if(cnt == 5) {
            if(check(status, ans)) return 1;
            return 0;
        }

        // 가지치기 - 만들 수 없는 코드, 혹은 앞으로 가능성이 없는 경우
        if (i > N || cnt + (N - i + 1) < 5) {
            return 0;
        }
        
        // 선택 / 선택 안함
        return guessCode(i + 1, status | (1 << i), cnt + 1, ans) + guessCode(i + 1, status, cnt, ans);
    }
}