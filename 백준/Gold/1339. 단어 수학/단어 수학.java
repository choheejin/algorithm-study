import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    // 순서에 상관하는가? - 0 : 알파벳에 대입해줘야하기 때문
    // 알파벳의 최대 길이는 10이므로, 10!의 시간복잡도를 가진다. -> 10! 까지는 괜찮은 시간복잡도
    // 아 좀 터질수도,,;;;
    
    static int N, len, maxValue, minNum;
    static char[][] inputs;
    static int[] alpha, useIdx, selectedNums;
    static boolean isSelected[];
    
    public static void main(String[] args) throws Exception {
        // 1. 알파벳 인덱스 배열을 만든다. (조합 생성 시 들어가는 값 넣어줄 수 있을 것)
        // 2. 사용 인덱스 배열을 만든다. => 순열 구하기 쉽게
        // 3. 선택 인덱스 배열 만든다. => 사용 인덱스를 이용하여 순열 생성
        // 4. 수를 만들어 준다.
        
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bf.readLine());
        alpha = new int[26];
        useIdx = new int[10];
        inputs = new char[N][];
                
        // 사용하는 알파벳 받아준다.
        for(int n = 0; n < N; n++) {
            inputs[n] = bf.readLine().toCharArray();
            for(int i = 0; i < inputs[n].length; i++) {
                alpha[inputs[n][i] - 65] = 1;
            }
        }
        
        len = 0;
        for(int i = 0; i < 26; i++) {
            if(alpha[i] == 1) useIdx[len++] = i;
        }
        
        isSelected = new boolean[10];
        selectedNums = new int[len];
        minNum = 10 - len;
        perm(0);
        System.out.println(maxValue);
    }

    static void perm(int cnt) {
        if(cnt == len) {
        	for(int i = 0; i < len; i++) {
                alpha[useIdx[i]] = selectedNums[i];
            }
            
            int result = 0;
            for(int n = 0; n < N; n++) {
                int numsLen = 0;
                int num = 0;
                int length = inputs[n].length;
                for(int i = 0; i < length ; i++) {
                	num = num*10 + alpha[inputs[n][i] - 65];
//                    num += alpha[inputs[n][i] - 65] * (int) Math.pow(10, numsLen++);
                }
                result += num;
            }
            maxValue = Math.max(result, maxValue);
            return;
        }
        
        for(int i = 0; i <= 9; i++) {
            if(isSelected[i]) continue;
            isSelected[i] = true;
            selectedNums[cnt] = i;
            perm(cnt + 1);
            isSelected[i] = false;
        }
    }
}