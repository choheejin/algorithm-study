import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    static int D, W, K, minValue;
    static int[][] graph;
    static int[] layerA, layerB;

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for(int tc = 1; tc <= T; tc++) {
            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();
            minValue = Integer.MAX_VALUE;
            layerA = new int[W];
            layerB = new int[W];
            graph = new int[D][W];

            for(int d = 0; d < D; d++) {
                for(int w = 0; w < W; w++) {
                    graph[d][w] = sc.nextInt();
                }
            }
            
            for(int w = 0; w < W; w++) {
                layerB[w] = 1;
            }

            subset(0, 0);
            sb.append("#").append(tc).append(" ").append(minValue).append("\n");
        }
        System.out.println(sb);
    }
    
    static void subset(int cnt, int total) {
        // 최소값 더 큰 경우, 더이상 부분집합을 생성할 필요가 없어짐.
        if(total > minValue) {
            return;
        }
        
        if(cnt == D) {
            if(check()) minValue = Math.min(minValue, total);
            return;
        }
        
        // 약품 주입 하지 않음
        subset(cnt+1, total);
        
        // 약품 A 주입
        int[] tmp = graph[cnt];

        graph[cnt] = layerA;
        subset(cnt+1, total+1);

        // 약품 B 주입
        graph[cnt] = layerB;
        subset(cnt+1, total+1);
        graph[cnt] = tmp;
    }

    static boolean check() {
        int result = 0;
        for(int w = 0; w < W; w++) {
            int cnt = 1;
            boolean flag = false;
            for(int d = 1; d < D; d++) {
                if(graph[d-1][w] == graph[d][w]) {
                    cnt++;
                } else {
                    cnt = 1;
                }
                if (cnt >= K) {
                    flag = true;
                    break;
                }
            }
            if(!flag) return false;
        }
        return true;
    }
}