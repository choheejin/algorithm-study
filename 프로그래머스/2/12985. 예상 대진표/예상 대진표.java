import java.util.*;

class Solution
{
    public int solution(int n, int a, int b)
    {
        int answer = 0;
        
        int[] arr = new int[n];

        for(int i = 1; i <= n; i++) {
            arr[i-1] = i;
        }
        
        A : while(true) {
            // 깊이 하나 더 들어간다.
            if(arr.length / 2 == 1) break;
            
            int[] newArr = new int[arr.length / 2];
            int idx = 0;

            for(int i = 0; i < arr.length; i += 2) {
                if(arr[i] == a && arr[i+1] == b) break A;
                if(arr[i] == b && arr[i+1] == a) break A;
                
                if(arr[i] == a || arr[i+1] == a) newArr[idx++] = a;
                else if(arr[i] == b || arr[i+1] == b) newArr[idx++] = b;
                else newArr[idx++] = arr[i];
            }
            
            arr = newArr;
            answer++;
        }
        
        return answer + 1;
    }
}