import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    static int N, W, H;
    static int[] selectedX;
    static int[][] graph;
    static int[] dx = new int[] {-1, 1, 0, 0}, dy = new int[] {0, 0, -1, 1};
    static int maxValue, total, brick;

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(bf.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());
            graph = new int[W][H];
            selectedX = new int[4];
            maxValue = 0;
            total = 0;
            brick = 0;
            for(int i = 0; i < H; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < W; j++) {
                    graph[j][i] = Integer.parseInt(st.nextToken());
                    if(graph[j][i] != 0) brick++;
                }
            }
            perm(0);
            sb.append("#").append(tc).append(" ").append(brick - maxValue).append("\n");
        }
        System.out.println(sb);
    }


    private static void deleteRock(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[] {x, y, graph[x][y]});
        graph[x][y] = 0;
        total++;
        while(!q.isEmpty()) {
            int[] cur = q.poll();
            int power = cur[2];
            for(int j = 1; j < power; j++) {
                for(int i = 0; i < 4; i++) {
                    int nx = cur[0] + (dx[i] * j), ny = cur[1] + (dy[i] * j);
                    if(nx >= W || ny >= H || nx < 0 || ny < 0 || graph[nx][ny] == 0) {
                        continue;
                    }
                    if(graph[nx][ny] != 0) {
                        q.add(new int[] {nx, ny, graph[nx][ny]});
                        graph[nx][ny] = 0;
                        total++;
                    }
                }
            }
        }
        maxValue = Math.max(maxValue, total);
    }

    private static void perm(int cnt) {
        if(cnt == N) {
            total = 0;
            int[][] tmp = new int[W][H];

            for(int i = 0; i < W; i++) {
                System.arraycopy(graph[i], 0, tmp[i], 0, H);
            }
            for(int i = 0; i < N; i++) {
                int x = selectedX[i];
                int y = findY(x);
                if(y == H) break;
                deleteRock(x, y);
                gravity();
//                print();
            }
            graph = tmp;
            return;
        }

        for(int i = 0; i < W; i++) {
            selectedX[cnt] = i;
            perm(cnt+1);
        }
    }

    private static int findY(int x) {
        for(int i = 0; i < H; i++) {
            if(graph[x][i] != 0) {
                return i;
            }
        }
        return H;
    }

    private static void gravity() {
        for(int i = 0; i < W; i++) {
            int[] idx = new int[H];
            int cnt = H-1;
            for(int j = H-1; j >=0 ; j--) {
                if(graph[i][j] != 0) {
                    idx[cnt--] = graph[i][j];
                }
            }
            graph[i] = idx;
        }
    }
    private static void print() {
        for(int i = 0; i < W; i++) {
            for(int j = 0; j < H; j++) {
                System.out.printf("%d ", graph[j][i]);
            }
            System.out.println();
        }
        System.out.println("=========");
    }

}