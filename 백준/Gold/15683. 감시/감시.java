import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int total, maxValue, cctvIdx;
    static int[][] map, copyMap;
    static int[][] cctvs;
    static int[] selectedCctvs;
    static int[] dx = {-1, 0, 0, 1}, dy = {0, 1, -1, 0}; // 상, 우, 좌, 하

    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        cctvs = new int[8][3]; // 0: 시시티비 종류, 1: r, 2: c
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for(int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if(map[i][j] == 0) {
                    total++;
                    continue;
                }
                if(map[i][j] == 6) continue;

                cctvs[cctvIdx][0] = map[i][j];
                cctvs[cctvIdx][1] = i;
                cctvs[cctvIdx][2] = j;
                cctvIdx++;
            }
        }

        selectedCctvs = new int[cctvIdx];

        perm(0);

        System.out.println(total - maxValue);
    }

    static int getDistance(int idx, int dir) {
        int cnt = 0;
        int x = cctvs[idx][1], y = cctvs[idx][2];

        while(true) {
            x += dx[dir];
            y += dy[dir];
            if(x < 0 || y < 0 || x >= N || y >= M) break;
            if(copyMap[x][y] == 6) break;
            // cctv는 cctv를 통과할 수 있다.
            if(copyMap[x][y] == 0) {
                copyMap[x][y] = 9;
                cnt++;
            }
        }
        return cnt;
    }

    static int getDistanceType1(int idx, int dir) {
        // 0: 상
        // 1: 우
        // 2: 좌
        // 3: 하
        return getDistance(idx, dir);
    }

    static int getDistanceType2(int idx, int dir) {
        // 상0, 우1, 좌2, 하3
        // 0: 우 + 좌
        // 1: 상 + 하
        int cnt1 = 0;
        int cnt2 = 0;
        switch (dir) {
            case 0:
                cnt1 = getDistance(idx, 1);
                cnt2 = getDistance(idx, 2);
                break;
            case 1:
                cnt1 = getDistance(idx, 0);
                cnt2 = getDistance(idx, 3);
                break;
        }

        return cnt1 + cnt2;
    }

    static int getDistanceType3(int idx, int dir) {
        // 상0, 우1, 좌2, 하3
        // 0: 상 + 우
        // 1: 하 + 좌
        // 2: 상 + 좌
        // 3: 하 + 우

        int cnt1 = 0;
        int cnt2 = 0;
        switch (dir) {
            case 0:
                cnt1 = getDistance(idx, 0);
                cnt2 = getDistance(idx, 1);
                break;
            case 1:
                cnt1 = getDistance(idx, 3);
                cnt2 = getDistance(idx, 2);
                break;
            case 2:
                cnt1 = getDistance(idx, 0);
                cnt2 = getDistance(idx, 2);
                break;
            case 3:
                cnt1 = getDistance(idx, 1);
                cnt2 = getDistance(idx, 3);
                break;
        }
        return cnt1 + cnt2;
    }

    static int getDistanceType4(int idx, int dir) {
        // 상0, 우1, 좌2, 하3

        // 0: 상 + 우 + 좌
        // 1: 하 + 우 + 좌
        // 2: 상 + 하 + 우
        // 3: 상 + 하 + 좌

        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;
        switch (dir) {
            case 0:
                cnt1 = getDistance(idx, 0);
                cnt2 = getDistance(idx, 1);
                cnt3 = getDistance(idx, 2);
                break;
            case 1:
                cnt1 = getDistance(idx, 1);
                cnt2 = getDistance(idx, 2);
                cnt3 = getDistance(idx, 3);
                break;
            case 2:
                cnt1 = getDistance(idx, 0);
                cnt2 = getDistance(idx, 3);
                cnt3 = getDistance(idx, 1);
                break;
            case 3:
                cnt1 = getDistance(idx, 0);
                cnt2 = getDistance(idx, 3);
                cnt3 = getDistance(idx, 2);
                break;
        }
        return cnt1 + cnt2 + cnt3;
    }

    static int getDistanceType5(int idx) {
        // 0: 상 + 하 + 좌 + 우
        int cnt1 = getDistance(idx, 0);
        int cnt2 = getDistance(idx, 1);
        int cnt3 = getDistance(idx, 2);
        int cnt4 = getDistance(idx, 3);
        return cnt1 + cnt2 + cnt3 + cnt4;
    }

    static void perm (int cnt) {
        if(cnt == cctvIdx) {
//            System.out.println(Arrays.toString(selectedCctvs));
            int distance = 0;
            copyMap = new int[N][M];
            arrayCopy();
            for(int i = 0; i < cctvIdx; i++) {
                switch (cctvs[i][0]) {
                    case 1:
                        distance += getDistanceType1(i, selectedCctvs[i]);
//                        System.out.println(cctvs[i][1] + ", " + cctvs[i][2] + ".." + selectedCctvs[i]+ ":::" + distance);
                        break;
                    case 2:
                        distance += getDistanceType2(i, selectedCctvs[i]);
                        break;
                    case 3:
                        distance += getDistanceType3(i, selectedCctvs[i]);
                        break;
                    case 4:
                        distance += getDistanceType4(i, selectedCctvs[i]);
                        break;
                    case 5:
                        distance += getDistanceType5(i);
                        break;
                }
                maxValue = Math.max(maxValue, distance);
            }
            return;
        }

        if(cctvs[cnt][0] == 2) {
            for(int i = 0; i < 2; i++) {
                selectedCctvs[cnt] = i;
                perm(cnt+1);
            }
        }
        else if(cctvs[cnt][0] == 5) {
            selectedCctvs[cnt] = 0;
            perm(cnt+1);
        }
        else {
            for(int i = 0; i < 4; i++) {
                selectedCctvs[cnt] = i;
                perm(cnt+1);
            }
        }
    }

    private static void arrayCopy() {
        for(int i=0; i<N; i++) {
            System.arraycopy(map[i], 0, copyMap[i], 0, M);
        }
    }
}