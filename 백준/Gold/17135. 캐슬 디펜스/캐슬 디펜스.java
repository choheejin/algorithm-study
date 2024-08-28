import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    //1. 조합을 통해서 궁수의 위치를 선정해준다.
    //2. N-1 행부터 0행까지 쏠 수 있는 적을 확인한다.
        //-1. 왼쪽 궁수부터 쏠 적 하나만 쏘기
    //3. 적들의 칸을 이동시켜준다.
    //4. N만큼 반복해준다 (남은 적들의 수로 최적화 가능할 듯)

    static int N, M, D;
    static int shootCnt, maxShootCnt, enemiesIdx;
    static int[][] adjMatrix, copyMatrix, enemies;
    static int[] archerLoc;
    static boolean[][] visited;
    static int[] dx = {0, -1, 0}, dy = {-1, 0, 1};
     
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        
        adjMatrix = new int[N][M];
        archerLoc = new int[3];
        
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < M; j++) {
                adjMatrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        setArcherLoc(0, 0);
        System.out.println(maxShootCnt);
    }
    
    static void setArcherLoc(int cnt, int start) {
        if(cnt == 3) {
            copyMatrix = copyArray();

            shootCnt = 0;

            for(int i = 0; i < N; i++) { // 최대 디펜스 시간
                enemies = new int[3][2]; // 각 궁수 마다 하나의 적을 죽일 수 있다.
                enemiesIdx = 0;

                for(int archer: archerLoc) { // 궁수의 위치
                    visited = new boolean[N][M];
                    bfs(N, archer); // 궁수의 위치 별로 최단 거리 적 쏘기
                }
                shoot();
                move(); // 끝나고 위치 이동
            }

            maxShootCnt = Math.max(shootCnt, maxShootCnt);
            return;
        }
        
        for(int i = start; i < M; i++) {
            archerLoc[cnt] = i;
            setArcherLoc(cnt + 1, i + 1);
        }
    }

    static int[][] copyArray() {
        int[][] tmp = new int[N][];
        for(int i = 0; i < N; i++) {
            tmp[i] = Arrays.copyOf(adjMatrix[i], M);
        }
        return tmp;
    }

    static void move() {
        int[] tmp = copyMatrix[0];
        for(int i = 1; i < N; i++) {
            int[] curr = copyMatrix[i];
            copyMatrix[i] = tmp;
            tmp = curr;
        }
        int[] newRow = new int[M];
        copyMatrix[0] = newRow;
    }

    static void bfs(int x, int y) {
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[] {x-1, y});
        visited[x-1][y] = true;
        
        while(!q.isEmpty()) {
            int[] curr = q.poll();

            // 쏠 적을 확인하면 더 이상의 탐색을 할 이유가 없어짐
            // 여기서 0 처리를 해주면 동시에 같은 적을 쏠 수 있다는 조건을 만족할 수 없게 됨
            if (copyMatrix[curr[0]][curr[1]] == 1) {
                enemies[enemiesIdx][0] = curr[0];
                enemies[enemiesIdx][1] = curr[1];
                enemiesIdx++;
                return;
            }

            for(int i = 0; i < 3; i++) {
                int nx = dx[i] + curr[0], ny = dy[i] + curr[1];

                if(nx < 0 || ny < 0 || nx >= N || ny >= M) continue; // 경계 밖
                if(visited[nx][ny]) continue; // 이미 방문한 적 있는 경우
                if(Math.abs(x - nx) + Math.abs(y - ny) > D) continue; // 궁수가 쏘는 범위 밖

                q.offer(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

    static void shoot() {
        for(int i = 0; i < enemiesIdx; i++) {
            int x = enemies[i][0];
            int y = enemies[i][1];
            if(copyMatrix[x][y] != 1) continue;
            copyMatrix[x][y] = 0;
            shootCnt++;
        }
    }

    static void print() {
        for(int i = 0; i < N; i++) {
            System.out.println(Arrays.toString(adjMatrix[i]));
        }
        System.out.println("=======");
    }
}