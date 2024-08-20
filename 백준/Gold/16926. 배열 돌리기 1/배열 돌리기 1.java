import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    // 제자리 원 위치는 (N-1) * 4 번 회전
    static int[] dx = {1, 0, -1, 0}, dy ={0, -1, 0, 1};
    static int N, M, R;
    static int[][] graph;
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        graph = new int[N][M];
        for(int i = 0 ; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < M; j++) {
                graph[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = Math.min(N,M)/2;
        for(int i = 0; i < cnt; i++) {
//            System.out.printf("***** %d %d\n", N - i*2, M- i*2);
            rotate(i, i, N - i*2, M- i*2, R % ((N- i*2+ M- i*2) * 2 - 4));
//            print();
        }

//        rotateI(1,1, N-2, M-2, R % ((N-2+ M-2) * 2));
//        rotateO(0 , 0, N, M , R % ((N+ M) * 2 - 4));
        print();
    }

    private static void rotate(int s, int e, int n, int m, int r) {
        int x = s, y = e;
        while(r > 0) {
            int idx = 0;
            int tmp = graph[x][y];
            // 한바퀴 돌려지는 수는 (N + M) * 2 - 4;
            for(int i = 0; i < (n + m) * 2 - 4; i++) {
//                System.out.printf("%d %d\n", x, y);
                if(x == s && y == e) idx = 0;
                if(x == s && y == e + m - 1) idx = 1;
                if(x == s + n - 1 && y == e + m-1) idx = 2;
                if(x == s + n - 1 && y == e) idx = 3;
                int nx = dx[idx] + x, ny = dy[idx] + y;
                int a = graph[nx][ny];
                graph[nx][ny] = tmp;
                tmp = a;
                x = nx;
                y = ny;
//                print();
            }
            r--;
        }
    }


    private static void rotateO(int x, int y, int n, int m, int r) {
        while(r > 0) {
            int idx = 0;
            int tmp = graph[x][y];
            // 한바퀴 돌려지는 수는 (N + M) * 2 - 4;
            for(int i = 0; i < (n + m) * 2 - 4; i++) {
                if(x == 0 && y == 0) idx = 0;
                if(x == 0 && y == m-1) idx = 1;
                if(x == n-1 && y == m-1) idx = 2;
                if(x == n-1 && y == 0) idx = 3;
                int nx = dx[idx] + x, ny = dy[idx] + y;
                int a = graph[nx][ny];
                graph[nx][ny] = tmp;
                tmp = a;
                x = nx;
                y = ny;
            }
            r--;
        }
    }

    private static void rotateI(int x, int y, int n, int m, int r) {
        while(r > 0) {
            int idx = 0;
            int tmp = graph[x][y];
            // 한바퀴 돌려지는 수는 (N + M) * 2 - 4;
            for(int i = 0; i < (n + m) * 2 - 4; i++) {
                if(x == 1 && y == 1) idx = 0;
                if(x == 1 && y == m) idx = 1;
                if(x == n && y == m) idx = 2;
                if(x == n && y == 1) idx = 3;
                int nx = dx[idx] + x, ny = dy[idx] + y;
                int a = graph[nx][ny];
                graph[nx][ny] = tmp;
                tmp = a;
                x = nx;
                y = ny;
            }
            r--;
        }
    }


    private static void print() {
        for(int i = 0; i < N; i++) {
            for(int j=0; j< M; j++) {
                System.out.printf("%d ", graph[i][j]);
            }
            System.out.println();
        }
//        System.out.println("=======");
    }
}