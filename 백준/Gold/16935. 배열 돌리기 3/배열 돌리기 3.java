import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 미완
public class Main {
    static StringBuilder sb;
    static int N, M, R;
    static int graph[][];
    public static void main(String[] args) throws Exception {
        sb = new StringBuilder();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        for(int i = 0; i < N; i ++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(bf.readLine());
        for(int i = 0; i < R; i++) {
            int input = Integer.parseInt(st.nextToken());
            switch (input) {
                case 1:
                    method1();
                    break;
                case 2:
                    method2();
                    break;
                case 3:
                    method3();
                    break;
                case 4:
                    method4();
                    break;
                case 5:
                    method5();
                    break;
                case 6:
                    method6();
                    break;
            }
//            print();
        }
        print();
    }

    static void method1() {
        int n = graph.length;
        for(int i = 0; i < n /2; i++) {
            int[] tmp = graph[i];
            graph[i] = graph[n-i-1];
            graph[n-i-1] = tmp;
        }
    }

    static void method2() {
        int n = graph.length, m = graph[0].length;
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m/2; j++) {
                int tmp = graph[i][j];
                graph[i][j] = graph[i][m - j - 1];
                graph[i][m - j - 1] = tmp;
            }
        }
    }

    static void method3() {
        int n = graph.length, m = graph[0].length;
        int[][] tmp = new int[m][n];
        int y = 0;
        for(int j = 0; j < m; j++) {
            int x = 0;
            for(int i = n-1; i >= 0; i--) {
                tmp[y][x] = graph[i][j];
                x++;
            }
            y++;
        }
        graph = tmp;
    }

    static void method4() {
        int n = graph.length, m = graph[0].length;
        int[][] tmp = new int[m][n];
        int x = 0;
        for(int j = m-1; j >= 0; j--) {
            int y = 0;
            for(int i = 0; i < n; i++) {
                tmp[x][y] = graph[i][j];
                y++;
            }
            x++;
        }
        graph = tmp;
    }

    static void method5() {
        int n = graph.length, m = graph[0].length;
        int newN = n/2, newM = m/2;
        for(int i = 0; i < newN; i ++) {
            for(int j = 0; j < newM; j++) {
                int a = graph[i][j];
                int b = graph[i][j + newM];
                int c = graph[i+newN][j];
                int d = graph[i+newN][j+newM];

                // 0, 0 -> 0, m/2
                graph[i][j + newM] = a;

                // 0, m/2 -> n/2,0
                graph[i+newN][j] = d;

                // n/2, 0 -> n/2, m/2
                graph[i+newN][j+newM] = b;

                // n/2, m/2 -> 0,0
                graph[i][j] = c;
            }
        }
    }

    static void method6() {
        int n = graph.length, m = graph[0].length;
        int newN = n/2, newM = m/2;
        for(int i = 0; i < newN; i ++) {
            for(int j = 0; j < newM; j++) {
                int a = graph[i][j];
                int b = graph[i][j + newM];
                int c = graph[i+newN][j];
                int d = graph[i+newN][j+newM];

                // 0, 0 -> 0, m/2
                graph[i][j + newM] = d;

                // 0, m/2 -> n/2,0
                graph[i+newN][j] = a;

                // n/2, 0 -> n/2, m/2
                graph[i+newN][j+newM] = c;

                // n/2, m/2 -> 0,0
                graph[i][j] = b;
            }
        }
    }


    static void print() {
        int n = graph.length, m = graph[0].length;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                sb.append(graph[i][j]).append(" ");
//                System.out.printf("%d ", graph[i][j]);
            }
            sb.append("\n");
//            System.out.println();
        }
//        System.out.println("===");
        System.out.println(sb);
    }
}